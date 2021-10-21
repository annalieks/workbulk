package com.univ.workbulk.board;

import com.univ.workbulk.board.dto.CreateBoardDto;
import com.univ.workbulk.exception.EntityNotFoundException;
import com.univ.workbulk.workgroup.WorkgroupMapper;
import com.univ.workbulk.workgroup.WorkgroupService;
import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceUnitTest {
    private BoardService boardService;

    private BoardRepository boardRepository;
    private WorkgroupService workgroupService;

    @BeforeEach
    public void setUp() {
        boardRepository = mock(BoardRepository.class);
        workgroupService = mock(WorkgroupService.class);
        boardService = new BoardService(boardRepository, workgroupService);
    }

    @Test
    void whenCreateBoardWithWorkgroup_thenEntityIsCreated() {
        var createBoardDto = new CreateBoardDto("board-name", "board-description");
        var board = BoardMapper.MAPPER.createBoardDtoToBoard(createBoardDto);
        var boardDto = BoardMapper.MAPPER.boardToBoardDto(board);
        var savedBoard = BoardMapper.MAPPER.createBoardDtoToBoard(createBoardDto);
        savedBoard.setId(UUID.randomUUID());

        var workgroupDto = new CreateWorkgroupDto("wg-name", "wg-description");
        var workgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(workgroupDto);
        workgroup.setId(UUID.randomUUID());
        board.setWorkgroup(workgroup);

        when(boardRepository.save(board)).thenReturn(savedBoard);
        when(workgroupService.findById(workgroup.getId())).thenReturn(Optional.of(workgroup));

        var result = boardService.createBoard(workgroup.getId(), createBoardDto);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(result.getName(), createBoardDto.getName());
        Assertions.assertEquals(result.getDescription(), createBoardDto.getDescription());

        verify(boardRepository, times(1)).save(any(Board.class));
        verify(workgroupService, times(1)).findById(any(UUID.class));
    }

    @Test
    void whenCreateBoardWithWorkgroupDoesNotExist_thenExceptionThrown() {
        when(workgroupService.findById(any())).thenReturn(Optional.empty());
        var createBoardDto = new CreateBoardDto("board-name", "board-description");
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> boardService.createBoard(UUID.randomUUID(), createBoardDto));

    }

    @Test
    void whenCreateIndependentBoard_thenEntityIsCreated() {
        var boardDto = new CreateBoardDto("name", "description");
        var board = BoardMapper.MAPPER.createBoardDtoToBoard(boardDto);
        var savedBoard = BoardMapper.MAPPER.createBoardDtoToBoard(boardDto);
        savedBoard.setId(UUID.randomUUID());
        when(boardRepository.save(board)).thenReturn(savedBoard);

        var result = boardService.createBoard(boardDto);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(result.getName(), boardDto.getName());
        Assertions.assertEquals(result.getDescription(), boardDto.getDescription());

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
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(result.get().getName(), board.getName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void whenEditWithParameters_thenNoException(String input) {
        var board = BoardMapper.MAPPER.createBoardDtoToBoard(
                new CreateBoardDto("name", "description")
        );
        when(boardRepository.findById(any())).thenReturn(Optional.of(board));
        Assertions.assertDoesNotThrow(() -> boardService.edit(UUID.randomUUID(), input, input));
    }

    @Test
    void whenBoardNotFound_thenExceptionThrown() {
        when(boardRepository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> boardService.edit(UUID.randomUUID(), "", ""));
    }

}
