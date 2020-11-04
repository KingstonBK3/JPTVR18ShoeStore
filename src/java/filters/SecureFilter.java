/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import entity.Customer;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.ejb.EJB;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sessions.CustomerRolesFacade;

/**
 *
 * @author pupil
 */
@WebFilter(filterName = "SecureFilter", dispatcherTypes = {DispatcherType.FORWARD}, urlPatterns = {"/*"})
public class SecureFilter implements Filter {
    
    @EJB
    private CustomerRolesFacade customerRolesFacade;
    
    public SecureFilter() {
        
    }    
       
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String topRoleCurrentCustomer=null;
        HttpSession session = httpRequest.getSession(false);
        if(session == null){
            httpRequest.setAttribute("topRoleCurrentCustomer", topRoleCurrentCustomer);
            chain.doFilter(request, response);
            return;
        }
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            httpRequest.setAttribute("topRoleCurrentCustomer", topRoleCurrentCustomer);
            chain.doFilter(request, response);
            return;
        }
        topRoleCurrentCustomer = customerRolesFacade.getTopRoleName(customer);
        if(topRoleCurrentCustomer == null){
            httpRequest.setAttribute("topRoleCurrentCustomer", topRoleCurrentCustomer);
            chain.doFilter(request, response);
            return;
        }
        request.setAttribute("topRoleCurrentCustomer", topRoleCurrentCustomer);
        chain.doFilter(request, response);
    }
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        

    }

}
