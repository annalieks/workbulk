package com.univ.workbulk.workgroup;

import com.univ.workbulk.workgroup.dto.CreateWorkgroupDto;
import com.univ.workbulk.workgroup.dto.WorkgroupDto;
import com.univ.workbulk.workgroup.dto.FullWorkgroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkgroupMapper {

    WorkgroupMapper MAPPER = Mappers.getMapper(WorkgroupMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "boards", ignore = true)
    Workgroup createWorkgroupDtoToWorkgroup(CreateWorkgroupDto workgroupDto);

    WorkgroupDto workgroupToWorkgroupDto(Workgroup workgroup);

    FullWorkgroupDto workgroupToFullWorkgroupDto(Workgroup workgroup);

}
