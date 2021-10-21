package com.univ.workbulk.board;

import com.univ.workbulk.board.dto.BoardDto;
import com.univ.workbulk.board.dto.CreateBoardDto;
import com.univ.workbulk.board.dto.FullBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    public BoardDto create(@RequestBody CreateBoardDto boardDto) {
        return boardService.createBoard(boardDto);
    }

    @PostMapping("/create/wg")
    public BoardDto createInWorkgroup(@RequestParam("workgroup-id") UUID workgroupId,
                           @RequestBody CreateBoardDto boardDto) {
        return boardService.createBoard(workgroupId, boardDto);
    }

    @GetMapping("/all")
    public List<BoardDto> getAll() {
        return boardService.getAll();
    }

    @GetMapping("/{id}")
    public FullBoardDto getInfo(@PathVariable UUID id) {
        return boardService.getInfo(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        boardService.delete(id);
    }

    @PutMapping("/{id}")
    public BoardDto edit(@PathVariable UUID id, @RequestBody CreateBoardDto boardDto) {
        return boardService.edit(id, boardDto.getName(), boardDto.getDescription());
    }

}
