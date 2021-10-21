package com.univ.workbulk.board;

import com.univ.workbulk.board.dto.BoardDto;
import com.univ.workbulk.board.dto.CreateBoardDto;
import com.univ.workbulk.board.dto.FullBoardDto;
import com.univ.workbulk.column.ColumnMapper;
import com.univ.workbulk.exception.EntityNotFoundException;
import com.univ.workbulk.user.UserRepository;
import com.univ.workbulk.workgroup.WorkgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    private final WorkgroupService workgroupService;

    @Autowired
    public BoardService(BoardRepository boardRepository,
                        WorkgroupService workgroupService) {
        this.boardRepository = boardRepository;
        this.workgroupService = workgroupService;
    }

    public BoardDto createBoard(UUID workgroupId, CreateBoardDto boardDto) {
        var workgroup = workgroupService.findById(workgroupId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No workgroup with id %s exist", workgroupId)
                ));

        var board = BoardMapper.MAPPER.createBoardDtoToBoard(boardDto);
        workgroup.getBoards().add(board);
        board.setWorkgroup(workgroup);
        workgroupService.save(workgroup);
        var savedBoard = boardRepository.save(board);
        return BoardMapper.MAPPER.boardToBoardDto(savedBoard);
    }

    public BoardDto createBoard(CreateBoardDto boardDto) {
        var board = BoardMapper.MAPPER.createBoardDtoToBoard(boardDto);
        var savedBoard = boardRepository.save(board);
        return BoardMapper.MAPPER.boardToBoardDto(savedBoard);
    }

    public List<BoardDto> getAll() {
        return boardRepository.findAll()
                .stream()
                .map(BoardMapper.MAPPER::boardToBoardDto)
                .collect(Collectors.toList());
    }

    public FullBoardDto getInfo(UUID id) {
        var board = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No board with id %s exist", id)
        ));

        var boardDto = BoardMapper.MAPPER.boardToFullBoardDto(board);
        var columns = board.getColumns().stream()
                .map(ColumnMapper.MAPPER::columnToColumnDto)
                .collect(Collectors.toList());
        boardDto.setColumns(columns);
        return boardDto;
    }

    public Optional<Board> findById(UUID id) {
        return boardRepository.findById(id);
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public void delete(UUID id) {
        var board = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No board with id %s exist", id)
        ));
        var workgroup = board.getWorkgroup();
        if (workgroup != null) {
            workgroupService.deleteBoard(workgroup.getId(), id);
        } else {
            boardRepository.delete(board);
        }
    }

    public BoardDto edit(UUID id, String name, String description) {
        var board = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No board with id %s exist", id)
        ));
        board.setName(name);
        board.setDescription(description);
        return BoardMapper.MAPPER.boardToBoardDto(boardRepository.save(board));
    }

}
