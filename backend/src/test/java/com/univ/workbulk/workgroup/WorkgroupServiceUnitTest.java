package com.univ.workbulk.workgroup;

import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkgroupServiceUnitTest {

    @Mock
    private WorkgroupRepository workgroupRepository;

    @InjectMocks
    private WorkgroupService workgroupService;

    @Test
    void whenCreateWorkgroup_thenEntityIsCreated() {
        var workgroupDto = new CreateWorkgroupDto("name", "description");
        var workgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(workgroupDto);
        var savedWorkgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(workgroupDto);
        savedWorkgroup.setId(UUID.randomUUID());
        when(workgroupRepository.save(workgroup)).thenReturn(savedWorkgroup);

        var result = workgroupService.createWorkgroup(workgroupDto);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(workgroupDto.getName());
        assertThat(result.getDescription()).isEqualTo(workgroupDto.getDescription());

        verify(workgroupRepository, times(1)).save(any(Workgroup.class));
    }

}
