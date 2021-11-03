package com.univ.workbulk.workgroup;

import com.univ.workbulk.board.BoardMapper;
import com.univ.workbulk.board.BoardRepository;
import com.univ.workbulk.board.dto.CreateBoardDto;
import com.univ.workbulk.exception.EntityNotFoundException;
import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkgroupServiceUnitTest {
    @InjectMocks
    private WorkgroupService workgroupService;

    @Mock
    private WorkgroupRepository workgroupRepository;

    @Mock
    private BoardRepository boardRepository;

    private CreateWorkgroupDto workgroupDto;

    @BeforeAll
    public void setUp() {
        workgroupDto = new CreateWorkgroupDto("name", "description");
    }

    @Test
    void whenCreateWorkgroup_thenEntityIsCreated() {
        var savedWorkgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(workgroupDto);
        savedWorkgroup.setId(UUID.randomUUID());
        when(workgroupRepository.save(any())).thenReturn(savedWorkgroup);

        var result = workgroupService.createWorkgroup(workgroupDto);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(workgroupDto.getName());
        assertThat(result.getDescription()).isEqualTo(workgroupDto.getDescription());

        verify(workgroupRepository, times(1)).save(any(Workgroup.class));
    }

    @Test
    void whenEditBoard_thenEdited() {
        var workgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(workgroupDto);
        workgroup.setId(UUID.randomUUID());
        var board = BoardMapper.MAPPER.createBoardDtoToBoard(
                new CreateBoardDto("name", "description")
        );
        board.setId(UUID.randomUUID());
        workgroup.setBoards(Collections.singletonList(board));

        when(workgroupRepository.findById(workgroup.getId())).thenReturn(Optional.of(workgroup));
        when(boardRepository.save(board)).thenReturn(board);
        var boardDto = workgroupService.editBoard(workgroup.getId(),
                board.getId(), "new name", "new desc");

        Assertions.assertEquals("new name", boardDto.getName());
        Assertions.assertEquals("new desc", boardDto.getDescription());
        Assertions.assertEquals(boardDto.getCreatedAt(), board.getCreatedAt());
    }

    @Test
    void whenGetAbsentWorkgroup_thenExceptionThrown() {
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> workgroupService.get(UUID.randomUUID()));
    }

}
