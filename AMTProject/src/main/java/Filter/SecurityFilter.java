package Filter;


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
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        // Don't filter resources and registration page
        if (request.getRequestURI().matches
                (".*(ttf|eot|svg|woff|jsp|ico|css|jpg|png|gif|js|register|login|home|profil|project|administrator|logout|resetpassword|)$")) {
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("SecurityFilter => Resources or basic pages asked");
            return;
        }
    }


    @Override
    public void destroy() {

    }
}