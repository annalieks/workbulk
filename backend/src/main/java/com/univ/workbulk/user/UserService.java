package com.univ.workbulk.user;

import com.univ.workbulk.auth.AuthUser;
import com.univ.workbulk.exception.EntityNotFoundException;
import com.univ.workbulk.exception.ProcessingException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Value("${media.folder}")
    private String resourcesFolder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = getUserByEmail(email);
        return new AuthUser(user.getId(), user.getEmail(), user.getPassword());
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user with id %s exists", id)
                ));
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user with email %s exists", email)
                ));
    }

    public Optional<User> getOptionalUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public String uploadAvatar(UUID id, MultipartFile file) {
        var user = getUserById(id);
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            var dir = Path.of(resourcesFolder);
            Files.createDirectories(dir);
            var name = UUID.randomUUID().toString();
            var extension = FilenameUtils.getExtension(file.getOriginalFilename());
            var imageName = name + "." + extension;
            Path imagePath = dir.resolve(imageName);
            var imageFile = new File(imagePath.toString());
            ImageIO.write(image, extension == null ? "" : extension, imageFile);
            user.setAvatar(imageName);
            userRepository.save(user);
            return imageName;
        } catch (IOException e) {
            throw new ProcessingException("Could not convert image into bytes");
        }
    }

}
