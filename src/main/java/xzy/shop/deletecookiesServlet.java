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
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (!cookie.getName().equals("JSESSIONID")) {//删除除了会话级cookie以外的所有cookie
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        request.getRequestDispatcher("shopping.jsp").forward(request,response);
    }
}