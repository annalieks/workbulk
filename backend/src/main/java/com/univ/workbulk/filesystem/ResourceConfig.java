package com.univ.workbulk.filesystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;

@Configuration
@EnableWebMvc
public class ResourceConfig implements WebMvcConfigurer {

    @Value("${media.folder}")
    String mediaFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(String.valueOf(Path.of(mediaFolder).toAbsolutePath().toUri()));
    }

}