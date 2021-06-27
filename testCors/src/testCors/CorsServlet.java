package testCors;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cros")
public class CorsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		String method = req.getMethod();
		String originHeader = req.getHeader("Origin");
		System.out.println("收到请求："+requestURI+"，方法："+method+"， Origin头："+originHeader);
		
		resp.setHeader("Access-Control-Allow-Origin", originHeader);
		resp.setHeader("Access-Control-Expose-Headers","token,secret"); 
		// 一般来讲，让此头的值是上面那个的【子集】（或相同）
		resp.setHeader("Access-Control-Allow-Headers","token,secret"); 

		resp.getWriter().write("hello cors...");
	}
	
	@Override 
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String originHeader = req.getHeader("Origin");
		System.out.println("option 处理");
		
		resp.setHeader("Access-Control-Allow-Origin", originHeader);
		resp.setHeader("Access-Control-Expose-Headers","token,secret"); 
		// 一般来讲，让此头的值是上面那个的【子集】（或相同）
		resp.setHeader("Access-Control-Allow-Headers","token,secret"); 
		//允许浏览器缓存此结果多久，单位：秒。有了缓存，以后就不用每次请求都发送预检请求啦
		resp.setHeader("Access-Control-Max-Age", "2000");
		
	}
	

}
