package com.example.demo.controller;

import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 작성 폼 페이지
    @GetMapping("/insert")
    public String boardForm() {
        return "board/insert";  // Thymeleaf 템플릿에서 "insert" 페이지로 이동
    }

    // 게시글 작성 처리
    @PostMapping("/insert")
    public String insert(@ModelAttribute BoardDto boardDto) {
        Long id = boardService.createBoard(boardDto);  // 서비스의 createBoard 호출
        return "redirect:/board/list";  // 게시글 작성 후 게시글 목록 페이지로 리다이렉트
    }

    // 게시글 목록 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDto> list = boardService.getAllBoards();  // 서비스에서 모든 게시글 조회
        model.addAttribute("list", list);  // 모델에 게시글 리스트 추가
        return "board/list";  // Thymeleaf 템플릿에서 "list" 페이지로 이동
    }

    // 게시글 상세 조회
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.getBoard(id);  // ID로 게시글 조회
        model.addAttribute("board", boardDto);  // 모델에 조회된 게시글 추가
        return "board/view";  // 게시글 상세 조회 페이지로 이동
    }

    // 게시글 수정 폼 페이지
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.getBoard(id);  // 수정할 게시글 조회
        model.addAttribute("board", boardDto);  // 모델에 게시글 추가
        return "board/update";  // 게시글 수정 폼 페이지로 이동
    }

    // 게시글 수정 처리
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute BoardDto boardDto) {
        boardService.updateBoard(id, boardDto);  // 게시글 수정 처리
        return "redirect:/board/list";  // 수정 후 게시글 목록으로 리다이렉트
    }

    // 게시글 삭제 처리
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.deleteBoard(id);  // 게시글 삭제 처리
        return "redirect:/board/list";  // 삭제 후 게시글 목록으로 리다이렉트
    }
}
