package com.univ.workbulk.workgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateWorkgroupDto {

    private final String name;

    private final String description;

}
