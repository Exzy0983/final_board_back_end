package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    public String save(@RequestBody BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "success";
    }

    @GetMapping
    public List<BoardDTO> findAll() {
        return boardService.findAll();
    }

    @GetMapping("/{id}")
    public BoardDTO findById(@PathVariable Long id) {
        boardService.updateHits(id);
        return boardService.findById(id);
    }

    @GetMapping("/{id}/edit")
    public BoardDTO getForEdit(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PutMapping("/{id}")
    public BoardDTO update(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        boardDTO.setId(id);
        boardService.update(boardDTO);
        return boardService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "deleted";
    }

    // BoardController.java 맨 아래에 추가
    @GetMapping("/api/test")
    @ResponseBody
    public String apiTest() {
        return "API works!";
    }
}


