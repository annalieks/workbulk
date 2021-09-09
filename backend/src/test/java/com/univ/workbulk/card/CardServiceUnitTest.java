package com.univ.workbulk.card;

import com.univ.workbulk.card.dto.CreateCardDto;
import com.univ.workbulk.column.BoardColumn;
import com.univ.workbulk.column.ColumnMapper;
import com.univ.workbulk.column.ColumnService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceUnitTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ColumnService columnService;

    @InjectMocks
    private CardService cardService;

    @Test
    void whenCreateCard_thenEntityIsCreated() {
        var cardDto = new CreateCardDto("text");
        var card = CardMapper.MAPPER.createCardDtoToCard(cardDto);
        var savedCard = CardMapper.MAPPER.createCardDtoToCard(cardDto);
        var columnId = UUID.randomUUID();
        var column = new BoardColumn();
        column.setId(columnId);
        column.setName("Column");
        savedCard.setId(UUID.randomUUID());
        when(cardRepository.save(card)).thenReturn(savedCard);
        when(columnService.findColumnById(columnId)).thenReturn(Optional.of(column));
        when(columnService.save(column)).thenReturn(column);
        var result = cardService.createCard(columnId, cardDto);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(savedCard.getId());
        assertThat(result.getText()).isEqualTo(cardDto.getText());

        verify(cardRepository, times(1)).save(any(Card.class));
    }

}
