package Filter;


import Model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length());
        HttpSession session = request.getSession(false);

        System.out.println("[SecurityFilter - doFilter] path - " + path);
        boolean isTargetUrlProtected = true;



        if (path.startsWith("/login")) {
            isTargetUrlProtected = false;
        } else if (path.contains("/vendor")) {
            isTargetUrlProtected = false;
        } else if (path.contains("/js")) {
            isTargetUrlProtected = false;
        } else if (path.contains("/images")) {
            isTargetUrlProtected = false;
        } else if (path.contains("/css")) {
            isTargetUrlProtected = false;
        } else if (path.contains("/fonts")) {
            isTargetUrlProtected = false;
        }else if (path.startsWith("/resetpassword")) {
            isTargetUrlProtected = false;
        } else if ("/register".startsWith(path)) {
            isTargetUrlProtected = false;
        } else {
            request.setAttribute("targetUrl", path);
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            System.out.println("[SecurityFilter - doFilter] user is null");
            if (isTargetUrlProtected) {
                request.setAttribute("targetUrl", path);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        } else {
            System.out.println("[SecurityFilter - doFilter] user is not null");
            System.out.println("[SecurityFilter - doFilter] user is reset ? " + user.isReset());
            if (user.isReset() && !path.contains("setpassword")) {
                System.out.println("[SecurityFilter - doFilter] change password");
                response.sendRedirect("setpassword");
                return;
            }

            if (path.startsWith("/login")) {
                System.out.println("[SecurityFilter - doFilter] login but logged ? - ");
                response.sendRedirect("home");
                return;
            }

            if (path.startsWith("/administrator")) {
                System.out.println("[SecurityFilter - doFilter] Administrator PAGE ? - " + path.startsWith("/administrator"));
                if (!user.isAdmin()) {
                    System.out.println("[SecurityFilter - doFilter] user is not admin");
                    // request.getRequestDispatcher("home.jsp").forward(request, response);
                    response.sendRedirect("home");
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}