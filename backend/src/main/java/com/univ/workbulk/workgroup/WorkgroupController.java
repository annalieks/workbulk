package com.univ.workbulk.workgroup;

import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import com.univ.workbulk.workgroup.dto.WorkgroupDto;
import com.univ.workbulk.workgroup.dto.FullWorkgroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workgroup")
public class WorkgroupController {

    private final WorkgroupService workgroupService;

    @Autowired
    public WorkgroupController(WorkgroupService workgroupService) {
        this.workgroupService = workgroupService;
    }

    @GetMapping("/{id}")
    public FullWorkgroupDto get(@PathVariable UUID id) {
        return workgroupService.get(id);
    }

    @PostMapping("/create")
    public WorkgroupDto create(@RequestBody CreateWorkgroupDto workgroupDto) {
        return workgroupService.createWorkgroup(workgroupDto);
    }

    @GetMapping("/all")
    public List<WorkgroupDto> getAll() {
        return workgroupService.getAll();
    }

}
