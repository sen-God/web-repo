package main.java.com.example.dao;

import main.java.com.example.model.Advertisement;
import main.java.com.example.Util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdDAO {
    public List<Advertisement> getRandomAds(int limit) throws SQLException {
        List<Advertisement> adList = new ArrayList<>();
        String sql = "SELECT * FROM advertisements WHERE expire_time > NOW() OR expire_time IS NULL ORDER BY RAND() LIMIT ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Advertisement ad = new Advertisement(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getString("type"),
                            rs.getString("image_url"),
                            rs.getString("source_system"),
                            rs.getTimestamp("create_time"),
                            rs.getTimestamp("expire_time")
                    );
                    adList.add(ad);
                }
            }
        }
        return adList;
    }

    public void addAdvertisement(Advertisement ad) throws SQLException {
        String sql = "INSERT INTO advertisements (title, content, type, image_url, source_system, expire_time) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getContent());
            stmt.setString(3, ad.getType());
            stmt.setString(4, ad.getImageUrl());
            stmt.setString(5, ad.getSourceSystem());
            stmt.setTimestamp(6, ad.getExpireTime());

            stmt.executeUpdate();
        }
    }

    public boolean verifyAdvertisementOwnership(int id, String sourceSystem) throws SQLException {
        String sql = "SELECT COUNT(*) FROM advertisements WHERE id = ? AND source_system = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, sourceSystem);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void updateAdvertisement(int id, Advertisement ad) throws SQLException {
        String sql = "UPDATE advertisements SET title=?, content=?, type=?, image_url=?, expire_time=? WHERE id=?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getContent());
            stmt.setString(3, ad.getType());
            stmt.setString(4, ad.getImageUrl());
            stmt.setTimestamp(5, ad.getExpireTime());
            stmt.setInt(6, id);

            stmt.executeUpdate();
        }
    }

    public void deleteAdvertisement(int id) throws SQLException {
        String sql = "DELETE FROM advertisements WHERE id=?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}