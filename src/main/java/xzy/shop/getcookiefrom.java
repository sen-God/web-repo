package xzy.shop;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/getCookiesFrom")
public class getcookiefrom extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 服务器 A 的接口地址
        String serverAUrl = "http://10.100.164.46:8080/shop-1.0-SNAPSHOT/fetch";

        // 创建 HTTP 请求
        URL url = new URL(serverAUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Cookie", request.getHeader("Cookie")); // 转发客户端 Cookie

        // 读取响应
        StringBuilder jsonResponse = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
        }

        // 打印 A 的响应
        System.out.println("Received JSON from A: " + jsonResponse);

        // 返回响应给客户端
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
    }
}
