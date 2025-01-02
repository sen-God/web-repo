package xzy.shop;

import java.io.*;
import java.util.Arrays;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/fetch")
//给外部服务器访问的接口，响应返回数组,这个文件放在购物网站
public class fetch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sport= readitem("sports");
        String electronics= readitem("electronics");
        String liquor=readitem("liquor");
        String book=readitem("book");
        String bath_item=readitem("bath_item");
        String[] num={sport,electronics,liquor,book,bath_item};
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(Arrays.toString(num));
    }
    public static String readitem(String item){
        String filePath = "/usr/local/"+item+".txt";

        File file = new File(filePath);
        int number=0;
        // 读取文件中的整数
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                number = Integer.parseInt(line.trim()); // 去掉多余空格并转换为整数
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("读取文件时发生错误: " + e.getMessage());
        }
        return String.valueOf(number);
    }
}


