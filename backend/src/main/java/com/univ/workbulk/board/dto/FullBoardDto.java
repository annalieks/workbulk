package com.univ.workbulk.board.dto;

import com.univ.workbulk.column.dto.ColumnDto;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class FullBoardDto {

    private final UUID id;

    private final Date createdAt;

    private final Date updatedAt;

    private final String name;

    private final String description;

    private List<ColumnDto> columns;

}
