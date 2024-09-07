package com.juny.jdbc_crud.board;

import com.juny.jdbc_crud.util.DataSourceUtil;
import com.juny.jdbc_crud.util.DriverManagerUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDAO {

  public void createTable() throws SQLException {
    String createTableSQL =
        "CREATE TABLE IF NOT EXISTS board ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "
            + "title VARCHAR(255) NOT NULL, "
            + "content TEXT NOT NULL, "
            + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
            + ")";

    try (Connection conn = DataSourceUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(createTableSQL)) {
      pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void createPost(String title, String content) throws SQLException {
    String sql = "INSERT INTO board (title, content) VALUES (?, ?)";

    try (Connection conn = DataSourceUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, title);
      pstmt.setString(2, content);
      pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void readPost(int id) {
    String sql = "SELECT * FROM board WHERE id = ?";

    try (Connection conn = DataSourceUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        System.out.println("id: " + rs.getInt("id"));
        System.out.println("title: " + rs.getString("title"));
        System.out.println("content: " + rs.getString("content"));
        System.out.println("created_at: " + rs.getTimestamp("created_at"));
      } else {
        System.out.println("포스트 없음 ID: " + id);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updatePost(int id, String title, String content) {
    String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";

    try (Connection conn = DataSourceUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, title);
      pstmt.setString(2, content);
      pstmt.setInt(3, id);
      int rowsAffected = pstmt.executeUpdate();

      if (rowsAffected > 0) {
        System.out.println("성공적으로 게시물 업데이트");
      } else {
        System.out.println("포스트 없음 ID: " + id);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deletePost(int id) {
    String sql = "DELETE FROM board WHERE id = ?";

    try (Connection conn = DataSourceUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, id);
      int rowsAffected = pstmt.executeUpdate();

      if (rowsAffected > 0) {
        System.out.println("성공적으로 게시물 삭제");
      } else {
        System.out.println("포스트 없음 ID: " + id);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws SQLException {
    BoardDAO dao = new BoardDAO();

    long startTime = System.currentTimeMillis();

    dao.createTable();
    for(int i = 0; i < 100; i++) {

      dao.createPost("제목1", "내용1");

      dao.readPost(2);

      dao.updatePost(1, "제목2", "내용2");
    }

    long endTime = System.currentTimeMillis();

    long duration = endTime - startTime;

    System.out.println("Hikari Datasource pool 10개 실행 시간: " + duration + " milliseconds");
//    System.out.println("DriverManger 실행 시간: " + duration + " milliseconds");
  }
}
