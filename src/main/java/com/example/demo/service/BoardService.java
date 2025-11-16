package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 게시판 Service
 * 비즈니스 로직을 처리하는 서비스 레이어
 */
@Service
public class BoardService {
    
    private final BoardRepository boardRepository;
    
    // 생성자 주입 방식으로 의존성 주입
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    
    /**
     * 게시글 저장
     * @param board 저장할 게시글
     * @return 저장된 게시글
     */
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }
    
    /**
     * 모든 게시글 조회
     * @return 게시글 목록
     */
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
    
    /**
     * ID로 게시글 조회
     * @param id 게시글 ID
     * @return 게시글 (없으면 null)
     */
    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }
    
    /**
     * 게시글 삭제
     * @param id 삭제할 게시글 ID
     */
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
