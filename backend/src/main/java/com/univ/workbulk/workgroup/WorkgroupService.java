package com.univ.workbulk.workgroup;

import com.univ.workbulk.exception.EntityNotFoundException;
import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import com.univ.workbulk.workgroup.dto.FullWorkgroupDto;
import com.univ.workbulk.workgroup.dto.WorkgroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkgroupService {

    private final WorkgroupRepository workgroupRepository;

    @Autowired
    public WorkgroupService(WorkgroupRepository workgroupRepository) {
        this.workgroupRepository = workgroupRepository;
    }

    public WorkgroupDto createWorkgroup(CreateWorkgroupDto workgroupDto) {
        var workgroup = WorkgroupMapper.MAPPER.createWorkgroupDtoToWorkgroup(workgroupDto);
        var savedWorkgroup = workgroupRepository.save(workgroup);
        return WorkgroupMapper.MAPPER.workgroupToWorkgroupDto(savedWorkgroup);

    }

    public Optional<Workgroup> findById(UUID id) {
        return workgroupRepository.findById(id);
    }

    public void save(Workgroup workgroup) {
        workgroupRepository.save(workgroup);
    }

    public FullWorkgroupDto get(UUID id) {
        var workgroup = findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("No workgroup with id %s exists", id)
        ));
        return WorkgroupMapper.MAPPER.workgroupToFullWorkgroupDto(workgroup);
    }

    public List<WorkgroupDto> getAll() {
        return workgroupRepository.findAll()
                .stream()
                .map(WorkgroupMapper.MAPPER::workgroupToWorkgroupDto)
                .collect(Collectors.toList());
    }

}
