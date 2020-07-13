import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @description:
 *
 *      监听器，【listener、filter、servlet】三大组件之一
 *
 * @projectName testweb
 * @author wukong
 * @date 2020/7/7 19:15
 */
public class MyContextListener implements ServletContextListener {

    /**
     *  接收web应用程序初始化进程开始的通知。
     *  在初始化web应用程序中的任何过滤器或servlet之前，
     *  所有servletcontextlistener都会收到上下文初始化的通知。
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //应用开启时调用
        String switchUserName = sce.getServletContext()
                .getInitParameter("switch_user_name");

        System.out.println("[listener] - 打开应用的用户：" + switchUserName);
    }


    /**
     *  接收ServletContext即将关闭的通知。
     * 在任何servletcontextlistener被通知上下文被破坏之前，
     * 所有的servlet和过滤器都已经被破坏了。
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //应用关闭时调用
        System.out.println("[listener - ServletContextListener.contextDestroyed]");
        sce.getServletContext().getRealPath("img");
    }
}
