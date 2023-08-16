package com.zyf.web.servlet;

/**
 * @ClassName StaticFileServlet
 * @Description TODO
 * @Author Geordie
 * @Date 2023/7/8 20:25
 * @Version 1.0
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaticFileServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String filePath = request.getPathInfo();
        //文件存放地址,
        String realPath = "D:\\softwore\\Vue\\vue_opus\\public\\uploadImages" + filePath;
        File file = new File(realPath);
        if (file.exists() && file.isFile())
        {
            String mimeType = Files.probeContentType(Path.of(realPath));
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());

            //读取文件
            //获取response字节输出流
            try (InputStream inputStream = new FileInputStream(file);
                 OutputStream outputStream = response.getOutputStream())
            {
                //完成流的copy
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1)
                {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }
        else
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
