package com.univ.workbulk.workgroup;

import com.univ.workbulk.board.BoardMapper;
import com.univ.workbulk.board.BoardRepository;
import com.univ.workbulk.board.dto.CreateBoardDto;
import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkgroupServiceUnitTest {
    private WorkgroupService workgroupService;

    private WorkgroupRepository workgroupRepository;
    private BoardRepository boardRepository;

    @BeforeEach
    public void setUp() {
        workgroupRepository = mock(WorkgroupRepository.class);
        boardRepository = mock(BoardRepository.class);
        workgroupService = new WorkgroupService(workgroupRepository, boardRepository);
    }

    @Test
    void whenCreateWorkgroup_thenEntityIsCreated() {
        var workgroupDto = new CreateWorkgroupDto("name", "description");
        var workgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(workgroupDto);
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
        var workgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(
                new CreateWorkgroupDto("name", "description")
        );
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

}
