package main.java.com.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.google.gson.Gson;
import main.java.com.example.dao.NewsDAO;
import main.java.com.example.model.News;
import main.java.com.example.response.ErrorResponse;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import main.java.com.example.util.CookieUtil;


public class NewsServlet extends HttpServlet {
    private NewsDAO newsDAO = new NewsDAO();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String pathInfo = request.getPathInfo();
            System.out.println("PathInfo: " + pathInfo);

            if (pathInfo != null && !pathInfo.equals("/")) {
                // 获取单条新闻
                try {
                    // 确保正确处理路径参数
                    String idStr = pathInfo.replaceAll("^/+", "").replaceAll("/+$", "");
                    System.out.println("Trying to parse ID: " + idStr);

                    int newsId = Integer.parseInt(idStr);
                    System.out.println("Looking for news with ID: " + newsId);

                    News news = newsDAO.getNewsById(newsId);
                    if (news != null) {
                        System.out.println("Found news: " + news);
                        // 记录访问历史
                        CookieUtil.addToHistory(response, news.getCategory(),request);

                        String jsonResponse = gson.toJson(news);
                        System.out.println("Sending response: " + jsonResponse);
                        out.print(jsonResponse);
                    } else {
                        System.out.println("News not found for ID: " + newsId);
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        out.print(gson.toJson(new ErrorResponse("News not found")));
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid news ID format: " + pathInfo);
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print(gson.toJson(new ErrorResponse("Invalid news ID")));
                }
            } else {
                // 获取新闻列表
                String category = request.getParameter("category");
                String search = request.getParameter("search");
                List<News> newsList;

                try {
                    if (category != null && !category.isEmpty()) {
                        newsList = newsDAO.getNewsByCategory(category);
                    } else if (search != null && !search.isEmpty()) {
                        newsList = newsDAO.searchNews(search);
                    } else {
                        newsList = newsDAO.getAllNews();
                    }

                    // 添加调试日志
                    System.out.println("Retrieved news count: " + newsList.size());
                    if (!newsList.isEmpty()) {
                        System.out.println("First news: " + gson.toJson(newsList.get(0)));
                    }

                    out.print(gson.toJson(newsList));
                } catch (SQLException e) {
                    System.err.println("Database error: " + e.getMessage());
                    e.printStackTrace();
                    throw e;
                }
            }
        } catch (Exception e) {
            System.err.println("Error processing request: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(gson.toJson(new ErrorResponse("Server error: " + e.getMessage())));
        } finally {
            out.flush();
        }
    }
}
