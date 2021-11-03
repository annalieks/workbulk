package com.univ.workbulk.card;

import com.univ.workbulk.card.dto.CreateCardDto;
import com.univ.workbulk.column.BoardColumn;
import com.univ.workbulk.column.ColumnService;
import com.univ.workbulk.exception.EntityNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CardServiceUnitTest {
    private CardService cardService;

    private CardRepository cardRepository;
    private ColumnService columnService;
    
    @BeforeTest
    public void setUp() {
        cardRepository = mock(CardRepository.class);
        columnService = mock(ColumnService.class);

        cardService = new CardService(cardRepository, columnService);
    }
    
    @DataProvider(name = "strings")
    public static Object[][] getStrings() {
        return new Object[][]{{" "}, {"\t"}, {"\n"}, {"test"}, {"test string"}};
    }

    @Test(dataProvider = "strings", groups = "entity")
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
        Assert.assertNotNull(result);
        Assert.assertEquals(savedCard.getId(), result.getId());
        Assert.assertEquals(cardDto.getText(), result.getText());
    }

    @Test(groups = "entity")
    void whenCardNotFound_thenExceptionThrown() {
        when(columnService.findColumnById(any())).thenReturn(Optional.empty());
        Assert.assertThrows(EntityNotFoundException.class,
                () -> cardService.createCard(UUID.randomUUID(), new CreateCardDto("card")));
    }

}
