package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 홈 컨트롤러
 * 메인 페이지와 게시판 API를 처리하는 컨트롤러
 */
@Controller
public class HomeController {
    
    private final BoardService boardService;
    private final DataSource dataSource;
    
    public HomeController(BoardService boardService, DataSource dataSource) {
        this.boardService = boardService;
        this.dataSource = dataSource;
    }

    /**
     * 메인 페이지
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "EC2 배포 테스트 성공! 🚀");
        model.addAttribute("currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("serverInfo", System.getProperty("os.name") + " / Java " + System.getProperty("java.version"));
        
        // DB 연결 정보
        String dbStatus = "연결 실패";
        String dbUrl = "알 수 없음";
        String dbName = "알 수 없음";
        try (Connection conn = dataSource.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                dbStatus = "연결 성공 ✅";
                dbUrl = conn.getMetaData().getURL();
                dbName = conn.getMetaData().getDatabaseProductName() + " " + conn.getMetaData().getDatabaseProductVersion();
            }
        } catch (Exception e) {
            dbStatus = "연결 실패 ❌: " + e.getMessage();
        }
        
        model.addAttribute("dbStatus", dbStatus);
        model.addAttribute("dbUrl", dbUrl);
        model.addAttribute("dbName", dbName);
        
        return "index";
    }

    /**
     * 헬스 체크 페이지
     */
    @GetMapping("/health")
    public String health(Model model) {
        model.addAttribute("status", "OK");
        model.addAttribute("timestamp", LocalDateTime.now());
        return "health";
    }

    /**
     * 게시글 목록 조회 API
     * 더미 데이터를 저장하고 전체 목록을 반환
     */
    @GetMapping("/boards")
    public ResponseEntity<List<Board>> getBoards() {
        // 더미 데이터 생성 및 저장
        Board board = new Board("테스트 게시글 " + System.currentTimeMillis(), "DB 연동 테스트용 게시글입니다.");
        boardService.saveBoard(board);

        // 게시글 전체 조회
        List<Board> boards = boardService.getAllBoards();
        return ResponseEntity.ok().body(boards);
    }
}
