package xzy.shop;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/getimage_banner")
public class getimage_banner extends HttpServlet {
    private static String getpath(int n){
        return switch (n) {
            case 0 -> "http://47.116.49.214:8080/ssm2bl10-1.0-SNAPSHOT/sports/";//这里是sports网络地址目录
            case 1 -> "http://47.116.49.214:8080/ssm2bl10-1.0-SNAPSHOT/electronics/";//这里是electronics网络地址目录
            case 2 -> "http://47.116.49.214:8080/ssm2bl10-1.0-SNAPSHOT/liquor/";//这里是liquor地址目录
            case 3 -> "http://47.116.49.214:8080/ssm2bl10-1.0-SNAPSHOT/book/";//这里是book网络地址目录
            case 4 -> "http://47.116.49.214:8080/ssm2bl10-1.0-SNAPSHOT/bath_item/";//这里是bath_item网络地址目录
            default -> "";
        };
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String serverAUrl = "http://10.100.164.46:8080/shop-1.0-SNAPSHOT/fetch";
        URL u = new URL(serverAUrl);
        HttpURLConnection connect = (HttpURLConnection) u.openConnection();
        connect.setRequestMethod("GET");
        connect.setRequestProperty("Accept", "text/plain");
        // 获取服务器 A 的响应
        StringBuilder jsonResponse = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
        }
        String[] num= jsonResponse.substring(1,jsonResponse.toString().length()-1).split(",");
        int[] array=new int[num.length];
        for(int i=0;i<num.length;i++){
            array[i]=Integer.parseInt(num[i].trim());
        }

        int max1=0;
        int n1=0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]>max1) {
                max1=array[i];
                n1=i;
            }
        }
        System.out.println(max1);
        System.out.println(n1);

        String url_banner;
        if(n1!=0){
        url_banner=getpath(n1)+"banner.png";//拼成横幅网路地址
            }
        else {//默认
            url_banner="http://47.116.49.214:8080/ssm2bl10-1.0-SNAPSHOT/sports/banner.png";
        }

        try {
            // 创建到目标服务器的连接
            URL url = new URL(url_banner);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 检查响应代码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 设置响应头，告知浏览器这是图片
                response.setContentType(connection.getContentType());
                response.setHeader("Cache-Control", "no-cache");

                // 读取图片流并写入响应
                try (InputStream inputStream = connection.getInputStream();
                     OutputStream outputStream = response.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                response.getWriter().write("Failed to fetch image");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

    }

}