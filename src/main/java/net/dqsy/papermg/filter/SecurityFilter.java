package net.dqsy.papermg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dqsy.papermg.sysmanager.po.PaperUser;
import net.dqsy.papermg.sysmanager.po.PaperUser;

/**
 * 
 * <p>
 * Description: 判读用户是否登录的拦截器
 * </p>
 * <p>
 * Company: www.itheima.com
 * </p>
 * 创建时间：2015年12月22日 下午3:05:33
 *
 * @author  King-Bao
 * @version 1.0
 */
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