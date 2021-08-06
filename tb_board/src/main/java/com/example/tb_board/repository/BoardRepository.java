package com.example.tb_board.repository;

import com.example.tb_board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer>  {

}
