package com.univ.workbulk.board;

import com.univ.workbulk.board.dto.CreateBoardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceUnitTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    void whenCreateBoard_thenEntityIsCreated() {
        var boardDto = new CreateBoardDto("name", "description");
        var board = BoardMapper.MAPPER.createBoardDtoToBoard(boardDto);
        var savedBoard = BoardMapper.MAPPER.createBoardDtoToBoard(boardDto);
        savedBoard.setId(UUID.randomUUID());
        when(boardRepository.save(board)).thenReturn(savedBoard);

        var result = boardService.createBoard(boardDto);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(boardDto.getName());
        assertThat(result.getDescription()).isEqualTo(boardDto.getDescription());

        verify(boardRepository, times(1)).save(any(Board.class));
    }

}
