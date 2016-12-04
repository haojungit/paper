package net.dqsy.papermg.filter;

import net.dqsy.papermg.sysmanager.po.PaperUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        PaperUser user = (PaperUser) request.getSession().getAttribute("curr_user");
        if (user != null) {
            chain.doFilter(req, res);
        } else {
            request.getSession().setAttribute("msg", "后台操作需要登录");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}