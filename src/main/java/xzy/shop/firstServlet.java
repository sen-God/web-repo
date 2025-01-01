package xzy.shop;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/shop")
public class firstServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().isNew()){//如果第一次访问会话，初始化数组
            String[][] items={{"bicycle","0"},{"wukong","0"},{"pc","0"},{"phone","0"},{"wine","0"},{"spirit","0"},{"facewash","0"},{"bath_cream","0"},{"sanguo","0"},{"shoes","0"}};
            //session里存了购物车信息，关闭页面就清空
            request.getSession().setAttribute("cart",items);
        }
        request.getRequestDispatcher("shopping.jsp").forward(request,response);
    }

}