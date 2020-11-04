/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Customer;
import entity.CustomerRoles;
import entity.Role;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessions.CustomerFacade;
import sessions.CustomerRolesFacade;
import sessions.RoleFacade;
import utilities.MakeHash;

/**
 *
 * @author pupil
 */
@WebServlet(name = "CustomerController", loadOnStartup = 1, urlPatterns =
    {"/createCustomer",
     "/showFormAddCustomer",
     "/showFormLogin",
     "/login",
     "/logout"
    })
public class CustomerController extends HttpServlet {
    @EJB 
    private CustomerFacade customerFacade;
    @EJB
    private RoleFacade roleFacade = new RoleFacade();
    @EJB
    private CustomerRolesFacade customerRolesFacade;

    @Override
    public void init() throws ServletException {
        int countRoles = this.roleFacade.count();
        if(countRoles > 0) return;
        MakeHash mh = new MakeHash();
        String salts = mh.createSalts();
        String password = mh.createHash("123123", salts);
        Customer admin = new Customer("admin", password, salts);
        customerFacade.create(admin);
        CustomerRoles customerRoles = new CustomerRoles();
        customerRoles.setCustomer(admin);
        Role roleCustomer = new Role("ADMIN");
        roleFacade.create(roleCustomer);
        customerRoles.setRole(roleCustomer);
        customerRolesFacade.create(customerRoles);
        roleCustomer = new Role("CUSTOMER");
        roleFacade.create(roleCustomer);
        customerRoles.setRole(roleCustomer);
        customerRolesFacade.create(customerRoles);
    }

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             String path = request.getServletPath();
       switch (path) {
          case "/index":
            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
                break;
          case "/showFormLogin":
                request.getRequestDispatcher("/systemPages/showFormLogin.jsp")
                        .forward(request, response);
                break;
          case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                Customer customer = customerFacade.findByLogin(login);
                if(customer == null){
                    request.setAttribute("info", "No such username or password");
                    request.getRequestDispatcher("/showFormLogin")
                        .forward(request, response);
                }
                MakeHash mh = new MakeHash();
                String encryptPassword = mh.createHash(password,customer.getSalts());
                if(!encryptPassword.equals(customer.getPassword())){
                    request.setAttribute("info", "No such username or password");
                    request.getRequestDispatcher("/showFormLogin")
                        .forward(request, response);
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("customer", customer);
                request.setAttribute("info", "Hi, "+customer.getLogin());
                request.setAttribute("customer", customer);
                
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null) session.invalidate();
                request.setAttribute("info", "You log out");
                response.sendRedirect("index.jsp");
                break;
            case "/showFormAddCustomer":
                request.getRequestDispatcher("/pages/showFormAddCustomer.jsp")
                        .forward(request, response);
                break;
            case "/createCustomer":
                login = request.getParameter("login");
                password = request.getParameter("password");               
                MakeHash makeHash = new MakeHash();
                String salts = makeHash.createSalts();
                String encodingPassword = makeHash.createHash(password, salts);
                customer = new Customer(login, encodingPassword, salts);
                customerFacade.create(customer);
                Role role = roleFacade.getRole("CUSTOMER");
                CustomerRoles customerRoles = new CustomerRoles(customer, role);
                customerRolesFacade.create(customerRoles);
                
                request.setAttribute("info", "Added user with login: "+customer.getLogin());
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
