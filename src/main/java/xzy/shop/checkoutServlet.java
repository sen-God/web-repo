package xzy.shop;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/checkout")
//结账
public class checkoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String[][] items= (String[][]) session.getAttribute("cart");
        boolean empty=true;
        for (String[] item : items) {
            if (!item[1].equals("0")) {
                empty=false;
                break;
            }
        }
        if (empty) {
            request.getRequestDispatcher("/shop").forward(request,response);
            return;
        }

        for (String[] item : items) {//加深cookie
            switch (item[0]){
                case "shoes":
                case "bicycle":
                    //gencookiesServlet.gencookies(request,response,"sports", Integer.parseInt(item[1]));
                    gencookiesServlet.add("sports");
                    break;
                case "pc":
                case "phone":
                    //gencookiesServlet.gencookies(request,response,"electronics", Integer.parseInt(item[1]));
                    gencookiesServlet.add("electronics");
                    break;
                case "wine":
                case "spirit":
                    //gencookiesServlet.gencookies(request,response,"liquor", Integer.parseInt(item[1]));
                    gencookiesServlet.add("liquor");
                    break;
                case "sanguo":
                case "wukong":
                    //gencookiesServlet.gencookies(request,response,"book", Integer.parseInt(item[1]));
                    gencookiesServlet.add("book");
                    break;
                case "facewash":
                case "bath_cream":
                    //gencookiesServlet.gencookies(request,response,"bath_item", Integer.parseInt(item[1]));
                    gencookiesServlet.add("bath_item");
                    break;
            }
            item[1]="0";//清空购物车
        }
        request.getRequestDispatcher("/shop").forward(request,response);
    }

}