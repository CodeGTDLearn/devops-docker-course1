package org.pictolearn.docker.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This proxy filter forwards the request to a Proxy Servlet
 * with the URI
 *
 * @author agane
 */
@WebFilter(filterName = "proxyFilter")
public class ProxyFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ProxyFilter.class);

    public void destroy() {
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {


        logger.debug("\n\n");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


        HttpServletRequest req = (HttpServletRequest) request;

        String log1 = req.getRequestURI().substring(req.getContextPath().length());

        logger.debug("1) ProxyFilter: {}",log1);

        request.setAttribute(
                "uri",
                req.getRequestURI().substring(req.getContextPath().length()));

        request.getRequestDispatcher("/ProxyServlet").forward(request, response);

        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.debug("\n\n");
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}
