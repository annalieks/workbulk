package com.univ.workbulk.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BoardDto {

    private final UUID id;

    private final Date createdAt;

    private final Date updatedAt;

    private final String name;

    private final String description;

}
