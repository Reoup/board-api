package com.example.tb_board.repository;

import com.example.tb_board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer>  {
    String SELECT_BOARD_LIST_PAGED = "SELECT title,content, created_time FROM tb_board WHERE 0 < seq ORDER BY seq DESC LIMIT ?1, ?2";

    @Query(
            value = SELECT_BOARD_LIST_PAGED,
            nativeQuery = true
    )


    List<Board> findFromTo(Integer var1, Integer var2);
// 간단한 생성, 조회, 수정, 삭제 기능은 JpaRepository를 상속하는 것으로 구현이 가능
}
