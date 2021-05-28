package com.univ.workbulk.card;

import com.univ.workbulk.card.dto.CardDto;
import com.univ.workbulk.card.dto.CreateCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/create")
    public CardDto createCard(@RequestParam("column-id") UUID columnId,
                              @RequestBody CreateCardDto cardDto) {
        return cardService.createCard(columnId, cardDto);
    }

}
