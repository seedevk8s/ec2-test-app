package com.example.demo.domain;

import jakarta.persistence.*;

/**
 * 게시판 엔티티 클래스
 * JPA를 사용한 게시글 도메인 모델
 */
@Entity
@Table(name = "boards")
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    private String author;
    
    // 기본 생성자 (JPA 필수)
    protected Board() {
    }
    
    // 제목과 내용만 받는 생성자
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    // 전체 필드 생성자
    public Board(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    // Getter & Setter
    public Long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
