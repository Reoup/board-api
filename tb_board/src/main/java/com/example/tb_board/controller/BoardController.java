package com.example.tb_board.controller;

import com.example.tb_board.domain.Board;
import com.example.tb_board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// CrossOrigin: CORS 문제를 해결하기 위해 추가
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api")
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<Map> getBoardList(@RequestParam(value="p_num", required=false) Integer p_num){
        if(p_num == null || p_num <= 0) p_num = 1;

        return boardService.getPagingBoard(p_num);
    }

//    @GetMapping("/board")
//    public List<Board> getBoardList(){
//        return boardService.getAllBoard();
//    }

    @PostMapping("/board")
    public Board createBoard(@RequestBody Board board){
        return boardService.createBoard(board);
    }

    @GetMapping("/board/{seq}")
    public ResponseEntity<Board> getBoardBySeq(
            @PathVariable Integer seq) {
        return boardService.getBoard(seq);
    }

    @PutMapping("/board/{seq}")
    public ResponseEntity<Board> updateBoardBySeq(
            @PathVariable Integer seq,
            @RequestBody Board board){
        return boardService.updateBoard(seq, board);
    }

    @DeleteMapping("/board/{seq}")
    public ResponseEntity<Map<String, Boolean>> deleteBoardBySeq(
            @PathVariable Integer seq){
     return boardService.deleteBoard(seq);
    }
}