package xzy.shop;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/deletecookies")
public class deletecookiesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (!cookie.getName().equals("JSESSIONID")) {//删除除了会话级cookie以外的所有cookie
//                    cookie.setMaxAge(0);
//                    response.addCookie(cookie);
//                }
//            }
//        }
        delete("sports");
        delete("book");
        delete("electronics");
        delete("liquor");
        delete("bath_item");
        request.getRequestDispatcher("shopping.jsp").forward(request,response);
    }
    public static void delete(String filename){
        String filePath = "/usr/local/"+filename+".txt";  // 动态路径

        File file = new File(filePath);
        try {
            // 确保目录存在
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("0");
            }
        } catch (IOException ignored) {
        }
    }
}