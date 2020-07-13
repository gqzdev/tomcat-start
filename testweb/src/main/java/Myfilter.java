import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 *
 *  过滤器
 *
 * @projectName testweb
 * @author wukong
 * @date 2020/7/7 19:11
 */
public class Myfilter implements Filter {

    /**
     *  由web容器调用，以指示筛选器将其放置到服务中。
     * servlet容器在实例化过滤器之后只调用init方法一次。
     * 在要求筛选器执行任何筛选工作之前，init方法必须成功完成。
     * 如果使用init方法，web容器也不能将筛选器放置到服务中
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //过滤器初始化
        System.out.println("[filter] filter#init  过滤器初始化");
    }

    /**
     *  每次由于客户机请求链末端的资源 而通过链传递请求/响应对时，
     *  容器都会调用过滤器的doFilter方法。
     *
     *  传入此方法的过滤器链允许过滤器将请求和响应传递给链中的下一个实体。
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        System.out.println("[filter] - 请求地址>>>"+req.getRequestURI());
        //让过滤器放行
        //过滤器相互之间是不清楚它的上一个或下一个是谁，所有的调用都是服务器在处理
        //Filter执行顺序可以由 <filter_mapping> 的顺序决定
        filterChain.doFilter(req, resp);
    }

    /**
     *  由web容器调用，以指示筛选器将其从服务中取出。
     * 此方法只在过滤器的doFilter方法中的所有线程都退出或超过超时时间后调用。
     * 在web容器调用此方法之后，它将不再在过滤器的此实例上调用doFilter方法。
     * 这个方法让过滤器有机会清理任何被占用的资源(例如，内存、文件句柄、线程)，
     * 并确保任何持久状态都与过滤器在内存中的当前状态同步。
     */
    @Override
    public void destroy() {
        //过滤器销毁
        System.out.println("[filter] - javax.servlet.Filter.destroy 过滤器销毁");
    }
}