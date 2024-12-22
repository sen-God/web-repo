package com.example.advertising.controller;

import com.example.advertising.model.Advertisement;
import com.example.advertising.model.AdvertisementDAO;
import com.example.advertising.model.AdvertisementDAOImpl;
import com.example.advertising.model.WeightCalculator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdController extends HttpServlet {
    private AdvertisementDAO advertisementDAO = new AdvertisementDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置CORS响应头，允许跨域及Cookie传递
        setCORSHeaders(response);

        // 处理OPTIONS预检请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // 读取Cookie并获取标签信息（这里简单假设Cookie中用逗号分隔的字符串表示标签）
        Map<String, Integer> tagFrequency = getTagFrequencyFromCookie(request);

        // 根据标签频率获取广告列表
        List<Advertisement> advertisements = advertisementDAO.getAdvertisementsByTags(tagFrequency);

        // 计算每个广告的权重
        List<Advertisement> weightedAdvertisements = calculateWeights(advertisements, tagFrequency);

        // 选择权重较高的广告（这里简单取权重最高的，若权重相同可随机等更复杂策略）
        Advertisement selectedAd = getSelectedAdvertisement(weightedAdvertisements);

        request.setAttribute("selectedAd", selectedAd);
        request.getRequestDispatcher("/ad_display.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void setCORSHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://www.example.com");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    private Map<String, Integer> getTagFrequencyFromCookie(HttpServletRequest request) {
        Map<String, Integer> tagFrequency = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        String[] tags={};//可以根据实际情况添加
        if (cookies!= null) {
            for (Cookie cookie : cookies) {
                for(String tag : tags) {
                    if (cookie.getName().equals(tag)) {
                        tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
                    }
                }
            }
        }
        return tagFrequency;
    }

    private List<Advertisement> calculateWeights(List<Advertisement> advertisements, Map<String, Integer> tagFrequency) {
        List<Advertisement> weightedAdvertisements = new ArrayList<>();
        for (Advertisement ad : advertisements) {
            int weight = WeightCalculator.calculateWeight(ad, tagFrequency);
            ad = new Advertisement(ad.getId(), ad.getTitle(), ad.getContent(), ad.getTags(), ad.getImageUrl());
            ad.setTitle(ad.getTitle() + " (权重: " + weight + ")"); // 简单示例，将权重添加到标题展示，实际可灵活处理
            weightedAdvertisements.add(ad);
        }
        return weightedAdvertisements;
    }

    private Advertisement getSelectedAdvertisement(List<Advertisement> weightedAdvertisements) {
        Advertisement selectedAd = null;
        int maxWeight = 0;
        for (Advertisement ad : weightedAdvertisements) {
            int weight = WeightCalculator.calculateWeight(ad, new HashMap<>()); // 重新计算权重，仅用于比较
            if (weight > maxWeight) {
                maxWeight = weight;
                selectedAd = ad;
            }
        }
        return selectedAd;
    }
}