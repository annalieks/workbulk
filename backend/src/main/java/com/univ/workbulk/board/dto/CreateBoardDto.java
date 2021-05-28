package com.univ.workbulk.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBoardDto {

    private final String name;

    private final String description;

}
