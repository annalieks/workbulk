package com.univ.workbulk.column.dto;

import com.univ.workbulk.card.dto.CardDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ColumnDto {

    private final List<CardDto> cards;

    private UUID id;

    private String name;

}
