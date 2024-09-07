package com.juny.jdbc_crud.board;

import com.juny.jdbc_crud.util.DataSourceUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SpringJDBCBoardDAO {

  private JdbcTemplate jdbcTemplate;

  public SpringJDBCBoardDAO(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public void createTable() throws SQLException {
    String createTableSQL =
        "CREATE TABLE IF NOT EXISTS board ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "
            + "title VARCHAR(255) NOT NULL, "
            + "content TEXT NOT NULL, "
            + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
            + ")";

    jdbcTemplate.execute(createTableSQL);
  }

  public void createPost(String title, String content) throws SQLException {
    String sql = "INSERT INTO board (title, content) VALUES (?, ?)";
    jdbcTemplate.update(sql, title, content);
  }

  public void readPost(int id) {
    String sql = "SELECT * FROM board WHERE id = ?";

    ResBoard board = jdbcTemplate.queryForObject(sql, new RowMapper<ResBoard>() {
      @Override
      public ResBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ResBoard(
          rs.getInt("id"),
          rs.getString("title"),
          rs.getString("content"),
          rs.getTimestamp("created_at")
        );
      }
    }, id);

    try {
      if (board != null) {
        System.out.println("ID: " + board.getId());
        System.out.println("Title: " + board.getTitle());
        System.out.println("Content: " + board.getContent());
        System.out.println("Created At: " + board.getCreatedAt());
      }
    } catch (Exception e) {
      System.out.println("포스트 없음 ID: " + id);
    }
  }

  public void updatePost(int id, String title, String content) {
    String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";
    int rowsAffected = jdbcTemplate.update(sql, title, content, id);

    if (rowsAffected > 0) {
      System.out.println("성공적으로 게시물 업데이트");
    } else {
      System.out.println("포스트 없음 ID: " + id);
    }
  }

  public void deletePost(int id) {
    String sql = "DELETE FROM board WHERE id = ?";
    int rowsAffected = jdbcTemplate.update(sql, id);

    if (rowsAffected > 0) {
      System.out.println("성공적으로 게시물 삭제");
    } else {
      System.out.println("포스트 없음 ID: " + id);
    }
  }

  public static void main(String[] args) throws SQLException {

    SpringJDBCBoardDAO dao = new SpringJDBCBoardDAO(DataSourceUtil.getDataSource());

    long startTime = System.currentTimeMillis();

    for(int i = 0; i < 100; i++) {
      dao.createTable();

      dao.createPost("제목1", "내용1");

      dao.readPost(2);

      dao.updatePost(1, "제목2", "내용2");

      dao.deletePost(1);
    }

    long endTime = System.currentTimeMillis();

    long duration = endTime - startTime;

    System.out.println("Hikari Datasource pool 10개 실행 시간: " + duration + " milliseconds");
  }
}
