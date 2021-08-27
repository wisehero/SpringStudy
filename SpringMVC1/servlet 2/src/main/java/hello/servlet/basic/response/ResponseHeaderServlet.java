package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setStatus(HttpServletResponse.SC_OK);

        res.setHeader("Content-Type", "text/plain");
        res.setHeader("Cache-Control", "no-cach, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setHeader("my-header", "hello");

        content(res);

        res.getWriter().write("ok");

    }

    private void content(HttpServletResponse res) {
        res.setContentType("text/plain");
        res.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse res) {
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        res.addCookie(cookie);
    }
}
