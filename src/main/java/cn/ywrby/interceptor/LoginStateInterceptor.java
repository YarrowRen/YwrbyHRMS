package cn.ywrby.interceptor;

import cn.ywrby.domain.User;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登陆状态拦截器：用于判断用户是否登录
 */
public class LoginStateInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录（通过判断session中是否存储user对象）
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){
            //用户为空的情况下，表示用户未登录，跳转到登录页面，并返回false表示拦截器拦截，停止放行
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
        //返回true表示正常登录，允许放行
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
