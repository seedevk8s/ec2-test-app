package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 게시판 Repository
 * JpaRepository를 상속받아 기본적인 CRUD 기능을 자동으로 제공받음
 * 
 * JpaRepository<Board, Long>:
 * - Board: 엔티티 타입
 * - Long: ID 타입
 * 
 * 제공되는 주요 메서드:
 * - save(Board) : 저장
 * - findById(Long) : ID로 조회
 * - findAll() : 전체 조회
 * - deleteById(Long) : 삭제
 * - count() : 개수 조회
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 기본 메서드 외에 추가로 필요한 쿼리 메서드를 정의할 수 있습니다.
    // 예: List<Board> findByAuthor(String author);
}
