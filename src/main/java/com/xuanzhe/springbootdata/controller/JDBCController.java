package com.xuanzhe.springbootdata.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JDBCController {
  @Autowired
  JdbcTemplate jdbcTemplate;
  @GetMapping("/bookList")
  public List<Map<String, Object>> bookList(){
    String sql = "select * from books";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
    return maps;
  }

  @GetMapping("/updateBook")
  public String addBook(){
    String sql = "insert into ssmbibliotheque.books(bookID,bookName,bookCounts,detail) values(100,'xxz',5,'csdcsdcsdc')";
    jdbcTemplate.update(sql);
    return "add_ok";
  }

  @GetMapping("/updateBook/{bookID}")
  public String updateBook(@PathVariable("bookID") int bookID){
    String sql = "update ssmbibliotheque.books set bookName=?,bookCounts=?,detail=? where bookID="+bookID;
    Object[] objects = new Object[3];
    objects[0]="helloxiaxuanzhe";
    objects[1]=2;
    objects[2]="haahhahaha";
    jdbcTemplate.update(sql,objects);
    return "update_ok";
  }

  @GetMapping("/deleteBook/{bookID}")
  public String deleteBook(@PathVariable("bookID") int bookID){
    String sql = "delete from ssmbibliotheque.books where bookID=?";
    jdbcTemplate.update(sql,bookID);
    return "delete_ok";
  }
}
