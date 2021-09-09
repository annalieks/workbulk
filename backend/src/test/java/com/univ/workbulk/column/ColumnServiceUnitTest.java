package com.univ.workbulk.column;

import com.univ.workbulk.board.Board;
import com.univ.workbulk.board.BoardService;
import com.univ.workbulk.column.dto.CreateColumnDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ColumnServiceUnitTest {

    @Mock
    private ColumnRepository columnRepository;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private ColumnService columnService;

    @Test
    void whenCreateColumn_thenEntityIsCreated() {
        var boardId = UUID.randomUUID();
        var board = new Board();
        board.setId(boardId);
        board.setName("Board");
        when(boardService.findById(boardId)).thenReturn(Optional.of(board));
        when(boardService.save(board)).thenReturn(board);

        var columnDto = new CreateColumnDto("Column");
        var column = ColumnMapper.MAPPER.createColumnDtoToColumn(columnDto);
        var savedColumn = ColumnMapper.MAPPER.createColumnDtoToColumn(columnDto);
        savedColumn.setId(UUID.randomUUID());

        when(columnRepository.save(column)).thenReturn(savedColumn);

        var result = columnService.createColumn(boardId, columnDto);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(columnDto.getName());

        verify(columnRepository, times(1)).save(any(BoardColumn.class));
    }

}
