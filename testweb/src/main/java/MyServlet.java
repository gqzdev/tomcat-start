import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: MyServlet
 *
 *  web.xml配置形式，当访问请求路径是myservlet，则进入当前类中的方法
 *      <servlet>
 *         <servlet-name>myServletName</servlet-name>
 *         <servlet-class>MyServlet</servlet-class>
 *     </servlet>
 *     <servlet-mapping>
 *         <servlet-name>myServletName</servlet-name>
 *         <url-pattern>/myservlet</url-pattern>
 *     </servlet-mapping>
 *
 *  MyServlet 继承 HttpServlet
 *
 *   GenericServlet (javax.servlet)
 *     HttpServlet (javax.servlet.http)
 *         MyServlet ()
 *         AnnotationServlet ()
 *         ServletTest ()
 *
 * @projectName testweb
 * @author ganquanzhong
 * @date 2020/6/29 14:37
 */
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("MyServlet 在处理get（）请求...");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        out.println("My Servlet!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("MyServlet 在处理post（）请求...");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        out.println("My Servlet!");
    }
}