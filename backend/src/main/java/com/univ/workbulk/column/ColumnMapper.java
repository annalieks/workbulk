package com.univ.workbulk.column;

import com.univ.workbulk.column.dto.ColumnDto;
import com.univ.workbulk.column.dto.CreateColumnDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColumnMapper {

    ColumnMapper MAPPER = Mappers.getMapper(ColumnMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cards", ignore = true)
    BoardColumn createColumnDtoToColumn(CreateColumnDto columnDto);

    ColumnDto columnToColumnDto(BoardColumn column);

}
