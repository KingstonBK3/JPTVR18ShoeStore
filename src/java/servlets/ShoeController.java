/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Customer;
import entity.CustomerShoes;
import entity.Shoe;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessions.CustomerShoesFacade;
import sessions.CustomerRolesFacade;
import sessions.ShoeFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "shoeController", urlPatterns =
        {"/createShoe",
         "/listShoes",
         "/showFormAddShoes",
         "/deleteShoe",
         "/updateSshoe",
         "/showShoe",
         "/showEditShoe"
    })
public class ShoeController extends HttpServlet {
    @EJB
    private ShoeFacade shoeFacade;
    @EJB
    private CustomerShoesFacade customerShoesFacade;
    @EJB
    private CustomerRolesFacade customerRolesFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "You are not authorized to view shoes. Log in");
            request.getRequestDispatcher("/showFormLogin")
                .forward(request, response);
        }
        Customer customer = (Customer) session.getAttribute("customer");
        //UserManager userManager = new UserManager();
        if(!customerRolesFacade.checkRole(customer,"CUSTOMER")){
            request.setAttribute("info", "You are not authorized to view shoes. Log in");
            request.getRequestDispatcher("/showFormLogin")
                .forward(request, response);
        }
        try (PrintWriter out = response.getWriter()) {
             String path = request.getServletPath();
            switch (path) {
                
                case "/showFormAddShoes":                   
                    request.getRequestDispatcher("/pages/showFormAddShoes.jsp")
                        .forward(request, response);                   
                    break;
                    
                case "/createShoe":
                    String name = request.getParameter("name");
                    String devName = request.getParameter("devName");
                    int price = Integer.parseInt(request.getParameter("price"));   
                    Shoe shoe = new Shoe (name, devName, price);
                    shoeFacade.create(shoe);
                    Calendar c = new GregorianCalendar();
                    CustomerShoes customerShoes = new CustomerShoes(customer, shoe, c.getTime());
                    customerShoesFacade.create(customerShoes);
                    request.setAttribute("info", "Shoe \""
                        +shoe.getName()+"\" создана");
                    request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                    break;
                    
                case "/listShoes":
                   List<Shoe> listShoes = shoeFacade.findByCustomer(customer);
                   request.setAttribute("listShoes", listShoes);
                   request.getRequestDispatcher("/pages/showListShoes.jsp")
                        .forward(request, response); 
                    break;   
                
                case "/showShoe":
                   String id = request.getParameter("idShoe");
                   shoe = shoeFacade.find(Long.parseLong(id));
                   request.setAttribute("shoe", shoe);
                   request.getRequestDispatcher("/pages/showShoe.jsp")
                           .forward(request, response);                   
                    break;       
                      
                case "/deleteShoe":
                    id = request.getParameter("idShoe");
                    if(id == null || "".equals(id)){
                    request.setAttribute("info", "This shoe don't exists");
                    request.getRequestDispatcher("/showListShoes")
                        .forward(request, response);
                    break;
                }
                    Shoe deleteShoe = shoeFacade.find(Long.parseLong(id));
                    listShoes = shoeFacade.findByCustomer(customer);
                    if(!listShoes.contains(deleteShoe)){
                    request.setAttribute("info", "This shoe don't exists");
                    request.getRequestDispatcher("/showListshoes")
                        .forward(request, response);
                    break;
                }
                    customerShoesFacade.removeByShoe(deleteShoe);
                    request.setAttribute("info", "Shoe "+ deleteShoe.getName()+" deleted.");
                    request.getRequestDispatcher("/listShoes")
                        .forward(request, response);
                    
                    break;
                case "/showEditShoe":
                    id = request.getParameter("idShoe");
                    request.setAttribute("idShoe", id);
                    request.getRequestDispatcher("/pages/showFormEditShoe.jsp")
                        .forward(request, response);                                       
                    break;   
                    
                case "/updateShoe":
                id = request.getParameter("idShoe");
                shoe = shoeFacade.find(Long.parseLong(id));
                name = request.getParameter("name");               
                devName = request.getParameter("devName");
                price = Integer.parseInt(request.getParameter("price"));
                shoe.setName(name);
                shoe.setBrandName(devName);               
                shoe.setPrice(price);
                shoeFacade.edit(shoe);
                request.setAttribute("shoe", shoe);
                request.getRequestDispatcher("/pages/showShoe.jsp")
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
