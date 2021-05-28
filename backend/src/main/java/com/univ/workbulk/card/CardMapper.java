package com.univ.workbulk.card;

import com.univ.workbulk.card.dto.CardDto;
import com.univ.workbulk.card.dto.CreateCardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {

    CardMapper MAPPER = Mappers.getMapper(CardMapper.class);

    @Mapping(target = "id", ignore = true)
    Card createCardDtoToCard(CreateCardDto cardDto);

    CardDto cardToCardDto(Card card);

}
