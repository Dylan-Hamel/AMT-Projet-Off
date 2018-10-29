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
        HttpSession session = request.getSession(false);
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // Don't filter resources and registration page
        System.out.println(request.getRequestURI().matches
                (".*(ttf|eot|svg|woff|jsp|ico|css|jpg|png|gif|js|register|login|resetpassword|)$"));
        System.out.println(request.getRequestURI());
        if (request.getRequestURI().contains("home")) {
            System.out.println("\n\n\n");
            System.out.println(request.getRequestURI());
            System.out.println(request.getRequestURI().matches
                    (".*(ttf|eot|svg|woff|jsp|ico|css|jpg|png|gif|js|register|login|resetpassword|)$"));
            System.out.println("\n\n\n");
        }
        if (request.getRequestURI().matches (".*(ttf|eot|svg|woff|ico|css|jpg|png|gif|js|register|login|resetpassword|)$")) {
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("[SecurityFilter - doFilter] page not filtered");
            return;
        }


        // We use a filter for personnal page
        System.out.println("[SecurityFilter - doFilter] request.getAttribute(\"user\") - " + request.getAttribute("user"));
        if (request.getSession().getAttribute("user") == null) {

            System.out.println("[SecurityFilter - doFilter] Filter Section");
            String loginURI = request.getContextPath() + "/login";

            boolean loggedIn = session != null && session.getAttribute("email") != null;
            boolean loginRequest = request.getRequestURI().equals(loginURI);

            System.out.println("Requested URI: " + request.getRequestURI());

            if (loggedIn) {
                System.out.println("[SecurityFilter - doFilter] loggedIn ");

                // Protect ADMIN area
                if (request.getRequestURI().contains(request.getContextPath() + "/administrator")) {
                    System.out.println("Contains : " + request.getContextPath() + "/administrator");
                    User user = (User) session.getAttribute("user");
                    if (user != null && user.getAdmin()) {
                        filterChain.doFilter(request, servletResponse);
                    } else {
                        httpServletResponse.sendRedirect(request.getContextPath() + "/home");
                    }
                } else {
                    if (request.getRequestURI().endsWith("/"))
                        httpServletResponse.sendRedirect(request.getContextPath() + "/home");

                    filterChain.doFilter(request, servletResponse);
                }
            } else if (loginRequest) {
                System.out.println("SecurityFilter => Login request");
                filterChain.doFilter(request, servletResponse);
            } else {
                System.out.println("SecurityFilter => To login");
                httpServletResponse.sendRedirect(request.getContextPath() + "/login");
            }
        }

    }


    @Override
    public void destroy() {

    }
}