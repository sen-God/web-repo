package com.example.advertising.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdvertisementDAOImpl implements AdvertisementDAO {
    @Override
    public List<Advertisement> getAdvertisementsByTags(Map<String, Integer> tagFrequency) {
        List<Advertisement> result = new ArrayList<>();
        String query = buildQuery(tagFrequency);
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int adId = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String content = resultSet.getString("content");
                    String[] tags = resultSet.getString("tags").split(",");
                    String imageUrl = resultSet.getString("image_url"); // 从数据库获取广告图片链接
                    Advertisement ad = new Advertisement(adId, title, content, tags, imageUrl);
                    result.add(ad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String buildQuery(Map<String, Integer> tagFrequency) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM advertisements");

        String s = "";//这里设置个默认值
        if(!tagFrequency.isEmpty()) {
            int maxval = -1;
            for (String tag : tagFrequency.keySet()) {
                if (tagFrequency.get(tag) > maxval) {
                    maxval = tagFrequency.get(tag);
                    s = tag;
                }
            }
        }
        queryBuilder.append(" WHERE tag=");
        queryBuilder.append(s);
        return queryBuilder.toString();
    }
}