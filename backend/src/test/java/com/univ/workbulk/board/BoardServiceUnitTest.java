package com.univ.workbulk.board;

import com.univ.workbulk.board.dto.CreateBoardDto;
import com.univ.workbulk.exception.EntityNotFoundException;
import com.univ.workbulk.workgroup.WorkgroupMapper;
import com.univ.workbulk.workgroup.WorkgroupService;
import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BoardServiceUnitTest {

    private BoardService boardService;

    private BoardRepository boardRepository;
    private WorkgroupService workgroupService;
    private CreateBoardDto createBoardDto;

    @DataProvider(name = "boardsData")
    public static Object[][] boardsData() {
        return new Object[][]{{" ", "\n"}, {"\t", ""}, {"  ", "    "}};
    }

    @BeforeTest
    @BeforeGroups(alwaysRun = true)
    public void setUp() {
        boardRepository = mock(BoardRepository.class);
        workgroupService = mock(WorkgroupService.class);
        boardService = new BoardService(boardRepository, workgroupService);
        createBoardDto = new CreateBoardDto("board-name", "board-description");
    }

    @Test(groups = {"entity"})
    public void whenCreateBoardWithWorkgroup_thenEntityIsCreated() {
        var board = BoardMapper.MAPPER.createBoardDtoToBoard(createBoardDto);
        var savedBoard = BoardMapper.MAPPER.createBoardDtoToBoard(createBoardDto);
        savedBoard.setId(UUID.randomUUID());

        var workgroupDto = new CreateWorkgroupDto("wg-name", "wg-description");
        var workgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(workgroupDto);
        workgroup.setId(UUID.randomUUID());
        board.setWorkgroup(workgroup);

        when(boardRepository.save(board)).thenReturn(savedBoard);
        when(workgroupService.findById(workgroup.getId())).thenReturn(Optional.of(workgroup));

        var result = boardService.createBoard(workgroup.getId(), createBoardDto);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertEquals(result.getName(), createBoardDto.getName());
        Assert.assertEquals(result.getDescription(), createBoardDto.getDescription());
    }

    @Test(groups = {"entity", "negative"})
    public void whenCreateBoardWithWorkgroupDoesNotExist_thenExceptionThrown() {
        when(workgroupService.findById(any())).thenReturn(Optional.empty());
        Assert.assertThrows(EntityNotFoundException.class,
                () -> boardService.createBoard(UUID.randomUUID(), createBoardDto));

    }

    @Test(groups = {"entity"})
    public void whenCreateIndependentBoard_thenEntityIsCreated() {
        var board = BoardMapper.MAPPER.createBoardDtoToBoard(createBoardDto);
        var savedBoard = BoardMapper.MAPPER.createBoardDtoToBoard(createBoardDto);
        savedBoard.setId(UUID.randomUUID());
        when(boardRepository.save(board)).thenReturn(savedBoard);

        var result = boardService.createBoard(createBoardDto);
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());
        Assert.assertEquals(result.getName(), createBoardDto.getName());
        Assert.assertEquals(result.getDescription(), createBoardDto.getDescription());

        verify(boardRepository, times(2)).save(any(Board.class));
    }

    @Test(groups = {"entity"})
    public void whenFindById_thenReturnBoard() {
        var board = new Board();
        var boardId = UUID.randomUUID();
        board.setId(boardId);
        board.setName("Board");
        when(boardService.findById(boardId)).thenReturn(Optional.of(board));

        var result = boardService.findById(boardId);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().getName(), board.getName());
    }

    @Test(dataProvider = "boardsData", groups = {"entity"})
    public void whenEditWithParameters_thenNoException(String name, String description) {
        var board = BoardMapper.MAPPER.createBoardDtoToBoard(
                new CreateBoardDto("name", "description")
        );
        when(boardRepository.findById(any())).thenReturn(Optional.of(board));
        boardService.edit(UUID.randomUUID(), name, description);
        Assert.assertEquals(board.getName(), name);
        Assert.assertEquals(board.getDescription(), description);
    }

    @Test(groups = {"entity", "negative"})
    public void whenBoardNotFound_thenExceptionThrown() {
        when(boardRepository.findById(any())).thenReturn(Optional.empty());
        Assert.assertThrows(EntityNotFoundException.class,
                () -> boardService.edit(UUID.randomUUID(), "", ""));
    }

}
