package com.univ.workbulk.card;

import com.univ.workbulk.card.dto.CardDto;
import com.univ.workbulk.card.dto.CreateCardDto;
import com.univ.workbulk.column.ColumnService;
import com.univ.workbulk.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardService {

    private final CardRepository cardRepository;

    private final ColumnService columnService;

    @Autowired
    public CardService(CardRepository cardRepository,
                       ColumnService columnService) {
        this.cardRepository = cardRepository;
        this.columnService = columnService;
    }

    public CardDto createCard(UUID columnId, CreateCardDto cardDto) {
        var column = columnService.findColumnById(columnId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No column with id %s exists", columnId)
                ));
        var card = CardMapper.MAPPER.createCardDtoToCard(cardDto);
        column.getCards().add(card);
        var savedCard = cardRepository.save(card);
        columnService.save(column);
        return CardMapper.MAPPER.cardToCardDto(savedCard);
    }

}
