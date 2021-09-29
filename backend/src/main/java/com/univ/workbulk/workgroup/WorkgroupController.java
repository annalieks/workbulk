package com.univ.workbulk.workgroup;

import com.univ.workbulk.auth.AuthUser;
import com.univ.workbulk.board.dto.BoardDto;
import com.univ.workbulk.board.dto.CreateBoardDto;
import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import com.univ.workbulk.workgroup.dto.FullWorkgroupDto;
import com.univ.workbulk.workgroup.dto.WorkgroupDto;
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

    @PostMapping("/{id}/board")
    public BoardDto createBoard(@PathVariable UUID id, @RequestBody CreateBoardDto boardDto) {
        return workgroupService.createBoard(id, boardDto.getName(), boardDto.getDescription());
    }

    @PostMapping("/create")
    public WorkgroupDto create(@RequestBody CreateWorkgroupDto workgroupDto) {
        return workgroupService.createWorkgroup(workgroupDto);
    }

    @GetMapping("/all")
    public List<WorkgroupDto> getAll() {
        return workgroupService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        workgroupService.delete(id);
    }

    @DeleteMapping("/{id}/board/{boardId}")
    public void deleteBoard(@PathVariable UUID id, @PathVariable UUID boardId) {
        workgroupService.deleteBoard(id, boardId);
    }

    @PutMapping("/{id}/board/{boardId}")
    public BoardDto editBoard(@PathVariable UUID id, @PathVariable UUID boardId, @RequestBody CreateBoardDto boardDto) {
        return workgroupService.editBoard(id, boardId, boardDto.getName(), boardDto.getDescription());
    }

    @PutMapping("/{id}")
    public WorkgroupDto edit(@PathVariable UUID id, @RequestBody CreateWorkgroupDto workgroupDto) {
        return workgroupService.edit(id, workgroupDto.getName(), workgroupDto.getDescription());
    }

}
