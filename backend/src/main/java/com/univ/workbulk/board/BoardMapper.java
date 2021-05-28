package com.univ.workbulk.board;

import com.univ.workbulk.board.dto.BoardDto;
import com.univ.workbulk.board.dto.CreateBoardDto;
import com.univ.workbulk.board.dto.FullBoardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardMapper {

    BoardMapper MAPPER = Mappers.getMapper(BoardMapper.class);

    BoardDto boardToBoardDto(Board board);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "columns", ignore = true)
    Board createBoardDtoToBoard(CreateBoardDto createBoardDto);

    @Mapping(target = "columns", ignore = true)
    FullBoardDto boardToFullBoardDto(Board board);

}
