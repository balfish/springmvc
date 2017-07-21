package com.balfish.hotel.servlet;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * servlet 不能声明成员变量,因为它是线程不安全的,使用的话只能是局部变量
 * <p>
 * 因为,servlet是单例的，也就是成员变量可能被所有请求并发访问
 * 如果能确保所有这些访问都是可读的,或者被很好的进行了同步管理,就没有问题.
 * 应该注意到同步的代价相比于多申请点内存而言，往往是高昂的
 */
public class ServletDemo extends HttpServlet {

    private static final long serialVersionUID = -8441568828023883440L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        writer.write("<html><body>hello servlet</body></html>");
        writer.flush();
        writer.close();
    }
}