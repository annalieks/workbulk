package com.univ.workbulk.column;

import com.univ.workbulk.board.BoardService;
import com.univ.workbulk.column.dto.ColumnDto;
import com.univ.workbulk.column.dto.CreateColumnDto;
import com.univ.workbulk.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ColumnService {

    private final ColumnRepository columnRepository;

    private final BoardService boardService;

    @Autowired
    public ColumnService(ColumnRepository columnRepository,
                         BoardService boardService) {
        this.columnRepository = columnRepository;
        this.boardService = boardService;
    }

    public ColumnDto createColumn(UUID boardId, CreateColumnDto columnDto) {
        var board = boardService.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No board with id %s exists", boardId)
                ));

        var column = ColumnMapper.MAPPER.createColumnDtoToColumn(columnDto);
        board.getColumns().add(column);

        var savedColumn = columnRepository.save(column);
        boardService.save(board);
        return ColumnMapper.MAPPER.columnToColumnDto(savedColumn);
    }

    public Optional<BoardColumn> findColumnById(UUID id) {
        return columnRepository.findById(id);
    }

    public BoardColumn save(BoardColumn column) {
        return columnRepository.save(column);
    }

}
