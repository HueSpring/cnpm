package dut.itf.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"*.xhtml"})
public class AuthenticationFilter implements Filter {

    private FilterConfig filterConfig = null;

    public AuthenticationFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession sesA = reqt.getSession(false);
            HttpSession sesS = reqt.getSession(false);
            String idA = null;
            String idS = null;

            if (sesA != null) {
                idA = (String) sesA.getAttribute("idaccount");
            }

            if (sesS != null) {
                idS = (String) sesS.getAttribute("idname");
            }

            String reqURI = reqt.getRequestURI();
            if (reqURI.indexOf("account/account.xhtml") >= 0
                    || reqURI.indexOf("student/student.xhtml") >= 0
                    || reqURI.indexOf("/public/") >= 0
                    || reqURI.contains("javax.faces.resource")
                    || idA != null || idS != null) {
                chain.doFilter(request, response);
            } else {
                if (reqURI.indexOf("/account/") >= 0) {
                    resp.sendRedirect(reqt.getContextPath() + "/dut/account/account.xhtml");
                }else if (reqURI.indexOf("/student/") >= 0) {
                    resp.sendRedirect(reqt.getContextPath() + "/dut/student/student.xhtml");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

}
