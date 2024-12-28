package main.java.com.example.dao;

import main.java.com.example.model.News;
import main.java.com.example.Util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
    public List<News> getAllNews() throws SQLException {
        List<News> newsList = new ArrayList<>();
        String sql = "SELECT * FROM news ORDER BY create_time DESC";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Executing SQL: " + sql);

            while (rs.next()) {
                News news = new News(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("category"),
                        rs.getTimestamp("create_time")
                );
                newsList.add(news);
            }

            System.out.println("Found " + newsList.size() + " news items");
        }
        return newsList;
    }

    public List<News> getNewsByCategory(String category) throws SQLException {
        List<News> newsList = new ArrayList<>();
        String sql = "SELECT * FROM news WHERE category = ? ORDER BY create_time DESC";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    News news = new News(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("category"),
                            rs.getTimestamp("create_time")
                    );
                    newsList.add(news);
                }
            }
        }
        return newsList;
    }

    public List<News> searchNews(String keyword) throws SQLException {
        List<News> newsList = new ArrayList<>();
        String sql = "SELECT * FROM news WHERE title LIKE ? OR content LIKE ? ORDER BY create_time DESC";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    News news = new News(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("category"),
                            rs.getTimestamp("create_time")
                    );
                    newsList.add(news);
                }
            }
        }
        return newsList;
    }

    public News getNewsById(int id) throws SQLException {
        String sql = "SELECT * FROM news WHERE id = ?";
        System.out.println("Executing SQL: " + sql + " with ID: " + id);

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    News news = new News(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("category"),
                            rs.getTimestamp("create_time")
                    );
                    System.out.println("Found news: " + news);
                    return news;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting news by ID: " + e.getMessage());
            throw e;
        }
        System.out.println("No news found with ID: " + id);
        return null;
    }
}
