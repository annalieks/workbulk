package com.univ.workbulk.workgroup;

import com.univ.workbulk.board.Board;
import com.univ.workbulk.board.BoardMapper;
import com.univ.workbulk.board.BoardRepository;
import com.univ.workbulk.board.dto.BoardDto;
import com.univ.workbulk.exception.EntityNotFoundException;
import com.univ.workbulk.user.UserRepository;
import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import com.univ.workbulk.workgroup.dto.FullWorkgroupDto;
import com.univ.workbulk.workgroup.dto.WorkgroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkgroupService {

    private final WorkgroupRepository workgroupRepository;

    private final BoardRepository boardRepository;

    @Autowired
    public WorkgroupService(WorkgroupRepository workgroupRepository,
                            BoardRepository boardRepository) {
        this.workgroupRepository = workgroupRepository;
        this.boardRepository = boardRepository;
    }

    public WorkgroupDto createWorkgroup(CreateWorkgroupDto workgroupDto) {
        var workgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(workgroupDto);
        var savedWorkgroup = workgroupRepository.save(workgroup);
        return WorkgroupMapper.MAPPER.workgroupToWorkgroupDto(savedWorkgroup);

    }

    public Optional<Workgroup> findById(UUID id) {
        return workgroupRepository.findById(id);
    }

    public void save(Workgroup workgroup) {
        workgroupRepository.save(workgroup);
    }

    public FullWorkgroupDto get(UUID id) {
        var workgroup = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No workgroup with id %s exists", id)
        ));
        return WorkgroupMapper.MAPPER.workgroupToFullWorkgroupDto(workgroup);
    }

    public BoardDto createBoard(UUID id, String name, String description) {
        var workgroup = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No workgroup with id %s exists", id)
        ));
        var board = new Board();
        board.setName(name);
        board.setDescription(description);
        board.setWorkgroup(workgroup);
        var savedBoard = boardRepository.save(board);
        workgroup.getBoards().add(board);
        workgroupRepository.save(workgroup);
        return BoardMapper.MAPPER.boardToBoardDto(savedBoard);
    }

    public List<WorkgroupDto> getAll() {
        return workgroupRepository.findAll()
                .stream()
                .map(WorkgroupMapper.MAPPER::workgroupToWorkgroupDto)
                .collect(Collectors.toList());
    }

    public void delete(UUID id) {
        var workgroup = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No workgroup with id %s exists", id)
        ));
        workgroupRepository.delete(workgroup);
    }

    public void deleteBoard(UUID id, UUID boardId) {
        var workgroup = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No workgroup with id %s exists", id)
        ));
        var board = workgroup.getBoards().stream().filter(b -> b.getId().equals(boardId)).findFirst();
        var foundBoard = board.orElseThrow(() -> new EntityNotFoundException(
                String.format("No board with id %s exists", boardId)
        ));
        workgroup.setBoards(workgroup.getBoards().stream().filter(b -> !b.getId().equals(boardId)).collect(Collectors.toList()));
        boardRepository.delete(foundBoard);
        workgroupRepository.save(workgroup);
    }

    public BoardDto editBoard(UUID id, UUID boardId, String name, String description) {
        var workgroup = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No workgroup with id %s exists", id)
        ));
        var board = workgroup.getBoards().stream().filter(b -> b.getId().equals(boardId)).findFirst();
        var foundBoard = board.orElseThrow(() -> new EntityNotFoundException(
                String.format("No board with id %s exists", boardId)
        ));
        foundBoard.setName(name);
        foundBoard.setDescription(description);
        return BoardMapper.MAPPER.boardToBoardDto(boardRepository.save(foundBoard));
    }

    public WorkgroupDto edit(UUID id, String name, String description) {
        var workgroup = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No workgroup with id %s exists", id)
        ));
        workgroup.setName(name);
        workgroup.setDescription(description);
        return WorkgroupMapper.MAPPER.workgroupToWorkgroupDto(workgroupRepository.save(workgroup));
    }

}
