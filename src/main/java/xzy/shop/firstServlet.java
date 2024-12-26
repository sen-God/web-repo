package xzy.shop;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/shop")
public class firstServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().isNew()){
            String[][] items=new String[10][2];
            //session里存了购物车信息，关闭页面就清空
            request.getSession().setAttribute("cart",items);
            //request.getSession().setMaxInactiveInterval(300);
        }
        request.getRequestDispatcher("shopping.jsp").forward(request,response);
    }
}