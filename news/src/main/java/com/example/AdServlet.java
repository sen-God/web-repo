package main.java.com.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.google.gson.Gson;
import main.java.com.example.dao.AdDAO;
import main.java.com.example.model.Advertisement;
import java.util.List;
import main.java.com.example.response.ErrorResponse;
import main.java.com.example.response.SuccessResponse;

public class AdServlet extends HttpServlet {
    private AdDAO adDAO = new AdDAO();
    private Gson gson = new Gson();

    // 验证外部系统的API密钥
    private boolean validateApiKey(String apiKey) {
        // 这里应该实现实际的API密钥验证逻辑
        return apiKey != null && !apiKey.isEmpty();
    }

    // 获取广告
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            int limit = Integer.parseInt(request.getParameter("limit") != null ?
                    request.getParameter("limit") : "3");
            List<Advertisement> ads = adDAO.getRandomAds(limit);
            out.print(gson.toJson(ads));
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(gson.toJson(new ErrorResponse("Database error")));
        }
        out.flush();
    }

    // 接收外部系统投放的广告
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // 验证API密钥
        String apiKey = request.getHeader("X-API-Key");
        if (!validateApiKey(apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print(gson.toJson(new ErrorResponse("Invalid API key")));
            out.flush();
            return;
        }

        try {
            Advertisement ad = gson.fromJson(request.getReader(), Advertisement.class);
            // 设置广告来源
            String sourceSystem = request.getHeader("X-Source-System");
            if (sourceSystem == null || sourceSystem.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print(gson.toJson(new ErrorResponse("Source system is required")));
                out.flush();
                return;
            }

            adDAO.addAdvertisement(ad);
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(new SuccessResponse("Advertisement created successfully")));
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(gson.toJson(new ErrorResponse("Database error")));
        }
        out.flush();
    }

    // 更新外部系统的广告
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // 验证API密钥
        String apiKey = request.getHeader("X-API-Key");
        if (!validateApiKey(apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print(gson.toJson(new ErrorResponse("Invalid API key")));
            out.flush();
            return;
        }

        try {
            String pathInfo = request.getPathInfo();
            int id = Integer.parseInt(pathInfo.substring(1));
            Advertisement ad = gson.fromJson(request.getReader(), Advertisement.class);

            // 验证广告所属系统
            String sourceSystem = request.getHeader("X-Source-System");
            if (!adDAO.verifyAdvertisementOwnership(id, sourceSystem)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                out.print(gson.toJson(new ErrorResponse("Not authorized to modify this advertisement")));
                out.flush();
                return;
            }

            adDAO.updateAdvertisement(id, ad);
            out.print(gson.toJson(new SuccessResponse("Advertisement updated successfully")));
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(gson.toJson(new ErrorResponse("Database error")));
        }
        out.flush();
    }

    // 删除外部系统的广告
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // 验证API密钥
        String apiKey = request.getHeader("X-API-Key");
        if (!validateApiKey(apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print(gson.toJson(new ErrorResponse("Invalid API key")));
            out.flush();
            return;
        }

        try {
            String pathInfo = request.getPathInfo();
            int id = Integer.parseInt(pathInfo.substring(1));

            // 验证广告所属系统
            String sourceSystem = request.getHeader("X-Source-System");
            if (!adDAO.verifyAdvertisementOwnership(id, sourceSystem)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                out.print(gson.toJson(new ErrorResponse("Not authorized to delete this advertisement")));
                out.flush();
                return;
            }

            adDAO.deleteAdvertisement(id);
            out.print(gson.toJson(new SuccessResponse("Advertisement deleted successfully")));
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(gson.toJson(new ErrorResponse("Database error")));
        }
        out.flush();
    }
}
