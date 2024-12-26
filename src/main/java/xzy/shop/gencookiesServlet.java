package xzy.shop;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/gencookies")
public class gencookiesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
    //点击加入购物车按钮时调用，加入购物车数组并生成cookie
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getParameter("add_to_cart").equals("加入购物车")) {
            switch (request.getParameter("item")){
                case "shoes":
                case "bicycle":
                    add_to_Cart(request,request.getParameter("item"));
                    gencookies(request,response,"sports");
                    //System.out.println("sports");
                    break;
                case "pc":
                case "phone":
                    add_to_Cart(request,request.getParameter("item"));
                    gencookies(request,response,"electronics");
                    //System.out.println("electronics");
                    break;
                case "wine":
                case "spirit":
                    add_to_Cart(request,request.getParameter("item"));
                    gencookies(request,response,"liquor");
                    //System.out.println("liquor");
                    break;
                case "sanguo":
                case "wukong":
                    add_to_Cart(request,request.getParameter("item"));
                    gencookies(request,response,"book");
                    //System.out.println("book");
                    break;
                case "facewash":
                case "bath_cream":
                    add_to_Cart(request,request.getParameter("item"));
                    gencookies(request,response,"bath_item");
                    //System.out.println("bath_item");
                    break;
                default:
                    request.getRequestDispatcher("shopping.jsp").forward(request,response);
            }
        }
    }
    //如果这个类别cookie不存在，就生成这个类别的cookie并设置值为1
    //如果存在，就把值+1，也就是值越大说明用户越对这个类别感兴趣，后面广告就更有针对性
    //关于cookie跨域问题待验证 setdomain/setpath/response头
    public static void gencookies(HttpServletRequest request, HttpServletResponse response,String item) {
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin","???");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Cookie[] cookies=request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(item)) {
                    int n= Integer.parseInt(cookie.getValue());
                    n++;
                    cookie.setValue(String.valueOf(n));
                    return;
                }
            }
            Cookie cookie=new Cookie(item,"1");
            cookie.setMaxAge(60*5);
            cookie.setPath("/");
            cookie.setDomain("localhost");
            response.addCookie(cookie);
        }
        else {
            Cookie cookie=new Cookie(item,"1");
            cookie.setMaxAge(60*5);
            cookie.setPath("/");
            cookie.setDomain("localhost");
            response.addCookie(cookie);
        }
    }
    //添加到购物车，更新数量
    public static void add_to_Cart(HttpServletRequest request,String shopped_item) {
        HttpSession session=request.getSession();
        String[][] items= (String[][]) session.getAttribute("cart");
        for(String[] item:items){
            if(item[0]!=null && item[0].equals(shopped_item)) {
                int n= Integer.parseInt(item[1]);
                item[1]=String.valueOf(n+1);
                return;
            }
        }
        for(String[] item:items){
            if(item[0]==null){
                item[0]=shopped_item;
                item[1]=String.valueOf(1);
                return;
            }
        }
    }
}