package board.boardDemo.service;

import board.boardDemo.domain.entity.Board;
import board.boardDemo.dto.BoardDto;
import board.boardDemo.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 저장
    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    // 게시글 목록 뽑아오기
    @Transactional
    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder().id(board.getId()).author(board.getAuthor()).title(board.getTitle()).content(board.getContent()).createdDate(board.getCreatedDate()).build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
}
