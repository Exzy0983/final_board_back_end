package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.codingrecipe.board.entity.BoardEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity entity = convertToEntity(boardDTO);
        boardRepository.save(entity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardDTO> dtoList = new ArrayList<>();
        for (BoardEntity entity : entityList) {
            BoardDTO dto = convertToDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public void updateHits(Long id) {
        Optional<BoardEntity> result = boardRepository.findById(id);
        if (result.isPresent()) {
            BoardEntity entity = result.get();
            entity.setBoardHits(entity.getBoardHits() + 1);
        }
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> result = boardRepository.findById(id);
        if (result.isPresent()) {
            BoardEntity entity = result.get();
            return convertToDTO(entity);
        } else {
            return null; // or throw an exception
        }
    }

    public void update(BoardDTO boardDTO) {
        Optional<BoardEntity> result = boardRepository.findById(boardDTO.getId());
        if (result.isPresent()) {
            BoardEntity entity = result.get();
            entity.setBoardWriter(boardDTO.getBoardWriter());
            entity.setBoardPass(boardDTO.getBoardPass());
            entity.setBoardTitle(boardDTO.getBoardTitle());
            entity.setBoardContents(boardDTO.getBoardContents());
            entity.setBoardHits(boardDTO.getBoardHits());
            if (boardDTO.getCreatedAt() != null) {
                entity.setCreatedAt(LocalDateTime.parse(boardDTO.getCreatedAt()));
            }
        }
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    private BoardEntity convertToEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        if (boardDTO.getCreatedAt() != null) {
            boardEntity.setCreatedAt(LocalDateTime.parse(boardDTO.getCreatedAt()));
        }
        return boardEntity;
    }

    private BoardDTO convertToDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        if (boardEntity.getCreatedAt() != null) {
            boardDTO.setCreatedAt(boardEntity.getCreatedAt().toString());
        }
        return boardDTO;
    }
}