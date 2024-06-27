package com.moving.moving_app.board.service;

import com.moving.moving_app.board.entity.Board;
import com.moving.moving_app.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board){
        boardRepository.save(board);
    }

    // 게시글 리스트 불러오기 처리
    public Page<Board> boardlist(Pageable pageable){
        return boardRepository.findAll(pageable); //Board라는 class가 담긴 list를 찾아 반환 , 매개변수가 없는 경우에는 public list이지만, 매개변수로 pageable을 주면 public pableable로 바뀜
    }

    // 특정 게시글 불러오기
    public Board boardview(Integer id){
        return boardRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
