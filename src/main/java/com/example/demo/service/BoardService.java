package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 생성
    public Long createBoard(BoardDto boardDto) {
        Board board = boardDto.toEntity();
        boardRepository.save(board);
        return board.getId(); // 생성된 게시글의 ID 반환
    }

    // 게시글 조회 (ID로)
    public BoardDto getBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        return board.map(BoardDto::new).orElse(null); // 없으면 null 반환
    }

    // 모든 게시글 조회
    public List<BoardDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(BoardDto::new).toList();
    }

    // 게시글 수정
    public Long updateBoard(Long id, BoardDto boardDto) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.setPwd(boardDto.getPwd());
            board.setEmail(boardDto.getEmail());
            boardRepository.save(board); // 수정된 내용 저장
            return board.getId();
        }
        return null;
    }

    // 게시글 삭제
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
