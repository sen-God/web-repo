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
    //点击加入购物车按钮时调用，加入购物车数组并生成cookie/本地文件
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getParameter("add_to_cart") !=null &&request.getParameter("add_to_cart").equals("加入购物车")) {
            add_to_Cart(request,request.getParameter("item"));
            switch (request.getParameter("item")){
                case "shoes":
                case "bicycle":
                    add("sports");
                    break;
                case "pc":
                case "phone":
                    add("electronics");
                    break;
                case "wine":
                case "spirit":
                    add("liquor");
                    break;
                case "sanguo":
                case "wukong":
                    add("book");
                    break;
                case "facewash":
                case "bath_cream":
                    add("bath_item");
                    break;
                default:
                    request.getRequestDispatcher("shopping.jsp").forward(request,response);
            }
            request.getRequestDispatcher("shopping.jsp").forward(request,response);
        } else if (request.getParameter("delete_from_cart")!=null && request.getParameter("delete_from_cart").equals("移除购物车")) {
            delete_from_cart(request,request.getParameter("item"));
            request.getRequestDispatcher("shopping.jsp").forward(request,response);
        }
    }
    //如果这个类别cookie不存在，就生成这个类别的cookie并设置值为1
    //如果存在，就把值+1，也就是值越大说明用户越对这个类别感兴趣，后面广告就更有针对性
    //关于cookie跨域问题待验证 setdomain/setpath/response头
    //cookie跨域跨站不可行，放弃该方法

    //添加到购物车，更新数量
    public static void add_to_Cart(HttpServletRequest request,String shopped_item) {
        HttpSession session=request.getSession();
        String[][] items= (String[][]) session.getAttribute("cart");
        for(String[] item:items){//假如购物车已经有了这个商品
            if(item[0].equals(shopped_item)) {
                int n= Integer.parseInt(item[1]);
                item[1]=String.valueOf(n+1);
                return;
            }
        }
    }
    //从购物车中移除一件
    public static void delete_from_cart(HttpServletRequest request,String shopped_item) {
        HttpSession session=request.getSession();
        String[][] items= (String[][]) session.getAttribute("cart");
        for(String[] item:items){
            if(item[0].equals(shopped_item)) {
                int n= Integer.parseInt(item[1]);
                item[1]=String.valueOf(n-1);
                return;
            }
        }
    }
    public static void add(String filename){
        String filePath = "/usr/local/"+filename+".txt";  // 动态路径// 替换为你的文件路径

        File file = new File(filePath);
        int number = 0;

        // 读取文件中的整数
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                number = Integer.parseInt(line.trim()); // 去掉多余空格并转换为整数
            }
        } catch (IOException | NumberFormatException ignored) {
        }
        number += 1;
        // 将结果保存回文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.valueOf(number)); // 转换为字符串写入
        } catch (IOException ignored) {
        }
    }
}