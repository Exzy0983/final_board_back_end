package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
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

        @GetMapping("/list")
        public List<BoardDTO> findAll(){
            return boardService.findAll();
        }

        @GetMapping("/{id}")
        public String findById(@PathVariable("id") Long id, Model model){
            // 조회수처리
            boardService.updateHits(id);
            // 상세내용 가져옴
            BoardDTO boardDTO = boardService.findById(id);
            model.addAttribute("board", boardDTO);
            System.out.println("boardDTO = " + boardDTO);
            return "detail";
        }

        @GetMapping("/update/{id}")
        public String update(@PathVariable("id") Long id, Model model){
            BoardDTO boardDTO = boardService.findById(id);
            model.addAttribute("board", boardDTO);
            return "update";
        }

        @PostMapping("/update/{id}")
        public String update(BoardDTO boardDTO, Model model){
            boardService.update(boardDTO);
            BoardDTO dto = boardService.findById(boardDTO.getId());
            model.addAttribute("board", dto);
            return "detail";
        }

        @GetMapping("/delete/{id}")
        public String delete(@PathVariable("id") Long id){
            boardService.delete(id);
            return "redirect:/list";
        }
    }



