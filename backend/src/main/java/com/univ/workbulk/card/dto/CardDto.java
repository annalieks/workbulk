package com.univ.workbulk.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CardDto {

    private final UUID id;

    private final String text;

}
