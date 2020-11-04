/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entity.Customer;
import javax.ejb.EJB;
import sessions.CustomerRolesFacade;

/**
 *
 * @author pupil
 */
public class CustomerManager {
    @EJB
    private CustomerRolesFacade customerRolesFacade;
    
    public boolean isRole(Customer customer, String roleName) {
        return customerRolesFacade.checkRole(customer,roleName);
    }   
}
