package com.univ.workbulk.card;

import com.univ.workbulk.card.dto.CreateCardDto;
import com.univ.workbulk.column.BoardColumn;
import com.univ.workbulk.column.ColumnService;
import com.univ.workbulk.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceUnitTest {
    private CardService cardService;

    private CardRepository cardRepository;
    private ColumnService columnService;


    @BeforeEach
    public void setUp() {
        cardRepository = mock(CardRepository.class);
        columnService = mock(ColumnService.class);

        cardService = new CardService(cardRepository, columnService);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n", "test", "test string"})
    void whenCreateCard_thenEntityIsCreated(String input) {
        var cardDto = new CreateCardDto(input);
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
        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedCard.getId(), result.getId());
        Assertions.assertEquals(cardDto.getText(), result.getText());

        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void whenCardNotFound_thenExceptionThrown() {
        when(columnService.findColumnById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> cardService.createCard(UUID.randomUUID(), new CreateCardDto("card")));
    }

}
