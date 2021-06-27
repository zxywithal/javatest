package testCors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        String originHeader = req.getHeader("Origin");
        System.out.println("收到请求: "+requestURI);
        System.out.println("方法 : "+ method);
        System.out.println("Origin 头 : "+ originHeader);

        List<Cookie> cookies = new ArrayList<>();
        if (req.getCookies()!=null) {
            cookies = Arrays.stream(req.getCookies()).filter(obj->obj.getName().equals("name") || obj.getName().equals("age")).collect(Collectors.toList());
        }

        if(cookies.isEmpty()){
            Cookie cookie = new Cookie("name", "YourBatman");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
            Cookie age = new Cookie("age", "18");
            age.setMaxAge(3600);
            resp.addCookie(age);
        }

    }
}
