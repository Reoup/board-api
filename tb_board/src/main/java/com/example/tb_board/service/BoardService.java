package com.example.tb_board.service;

import com.example.tb_board.domain.Board;
import com.example.tb_board.exception.ResourceNotFoundException;
import com.example.tb_board.repository.BoardRepository;
import com.example.tb_board.util.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public int findAllCount(){
        return (int) boardRepository.count();
    }

    public ResponseEntity<Map> getPagingBoard(Integer p_num){
        Map result = null;

        PagingUtil pu = new PagingUtil(p_num, 5, 5);
        List<Board> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
        pu.setObjectCountTotal(findAllCount());
        pu.setCalcForPaging();

        if(list == null || list.size() == 0){
            return null;
        }

        result = new HashMap<>();
        result.put("pagingData", pu);
        result.put("list", list);
        System.out.println(result);
        return ResponseEntity.ok(result);
    }

    // Service를 호출해서 글목록의 데이터를 리턴하는 메소드
    public List<Board> getAllBoard() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "seq"));
    }

    public Board createBoard(Board board){
        return boardRepository.save(board);
    }

    public ResponseEntity<Board> getBoard(Integer seq){
        Board board = boardRepository.findById(seq)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Detail by seq: ["+seq+"]"));
        return ResponseEntity.ok(board);
    }

    public ResponseEntity<Board> updateBoard(
            Integer seq, Board updateBoard) {
        Board board = boardRepository.findById(seq)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Detail by seq: [" + seq + "]"));
        board.setTitle(updateBoard.getTitle());
        board.setContent(updateBoard.getContent());

        Board endUpdateBoard = boardRepository.save(board);
        return ResponseEntity.ok(endUpdateBoard);
    }

    public ResponseEntity<Map<String, Boolean>> deleteBoard(
            Integer seq) {
        Board board = boardRepository.findById(seq)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Detail by seq: ["+seq+"]"));
        boardRepository.delete(board);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Board Data by id: ["+seq+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
