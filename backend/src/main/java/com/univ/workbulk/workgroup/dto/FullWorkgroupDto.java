package com.univ.workbulk.workgroup.dto;

import com.univ.workbulk.board.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FullWorkgroupDto {

    private final UUID id;

    private final String name;

    private final String description;

    private final Date createdAt;

    private final Date updatedAt;

    private final List<BoardDto> boards;

}
