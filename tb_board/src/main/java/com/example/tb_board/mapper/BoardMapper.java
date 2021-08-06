package com.example.tb_board.mapper;

import com.example.tb_board.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> getBoardList();
}