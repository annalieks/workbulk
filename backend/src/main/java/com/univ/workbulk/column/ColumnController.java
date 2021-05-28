package com.univ.workbulk.column;

import com.univ.workbulk.column.dto.ColumnDto;
import com.univ.workbulk.column.dto.CreateColumnDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/column")
public class ColumnController {

    private final ColumnService columnService;

    @Autowired
    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @PostMapping("/create")
    public ColumnDto createColumn(@RequestParam("board-id") UUID id,
                                  @RequestBody CreateColumnDto columnDto) {
        return columnService.createColumn(id, columnDto);
    }


}
