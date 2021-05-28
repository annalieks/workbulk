package com.univ.workbulk.workgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class WorkgroupDto {

    private final UUID id;

    private final String name;

    private final String description;

    private final Date createdAt;

    private final Date updatedAt;

}
