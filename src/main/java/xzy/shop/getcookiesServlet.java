package xzy.shop;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/getcookies")
public class getcookiesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求中的 Cookie
        Cookie[] cookies = request.getCookies();

        // 使用 StringBuilder 构建 JSON 数据
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("["); // JSON 数组起始

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];

                // 添加 Cookie 的 JSON 对象
                jsonBuilder.append("{");
                jsonBuilder.append("\"name\":\"").append(cookie.getName()).append("\",");
                jsonBuilder.append("\"value\":\"").append(cookie.getValue()).append("\"");
                jsonBuilder.append("}");

                // 如果不是最后一个 Cookie，添加逗号
                if (i < cookies.length - 1) {
                    jsonBuilder.append(",");
                }
            }
        }

        jsonBuilder.append("]"); // JSON 数组结束

        // 设置响应内容类型为 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 将 JSON 数据返回给客户端
        response.getWriter().write(jsonBuilder.toString());
        System.out.println(jsonBuilder);
    }

}