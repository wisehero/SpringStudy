package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회");
        req.getParameterNames().asIterator().
                forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName))); // 모든 파라미터 다 꺼냄
        System.out.println("전체 파라미터 조회");
        System.out.println();

        System.out.println("단일 파라미터 조회");
        req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        res.getWriter().write("ok");
    }
}
