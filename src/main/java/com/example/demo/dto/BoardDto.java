package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {
    private Long id;
    private String pwd;
    private String email;
    private Date regdate;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.pwd = board.getPwd();
        this.email = board.getEmail();
        this.regdate = board.getRegdate();
    }

    public Board toEntity() {
//        return new Member(id, pwd, email, age, regdate);
        return Board.builder()
                .id(this.id)
                .pwd(this.pwd)
                .email(this.email)
                .regdate(this.regdate).build();
    }
}
