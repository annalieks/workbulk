package com.univ.workbulk.board;

import com.univ.workbulk.board.dto.CreateBoardDto;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void whenFindById_thenReturnBoard() {
        var board = new Board();
        var boardId = UUID.randomUUID();
        board.setId(boardId);
        board.setName("Board");
        when(boardService.findById(boardId)).thenReturn(Optional.of(board));

        var result = boardService.findById(boardId);
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(board.getName());
    }

}
