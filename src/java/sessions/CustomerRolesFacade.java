/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entity.Customer;
import entity.CustomerRoles;
import entity.Role;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class CustomerRolesFacade extends AbstractFacade<CustomerRoles> {
    @EJB
    private RoleFacade roleFacade;

    @PersistenceContext(unitName = "JPTVR18ShoeStorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerRolesFacade() {
        super(CustomerRoles.class);
    }

    public boolean checkRole(Customer customer, String roleName) {
        try {
            CustomerRoles cr = (CustomerRoles) em.createQuery("SELECT cr FROM CustomerRoles cr WHERE cr.role.name = :roleName AND cr.customer = :customer")
                    .setParameter("roleName", roleName)
                    .setParameter("customer", customer)
                    .getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTopRoleName(Customer customer) {
       List<CustomerRoles> listCustomerRoles = em.createQuery("SELECT cr FROM CustomerRoles cr WHERE cr.customer = :customer")
               .setParameter("customer", customer)
               .getResultList();
       for(CustomerRoles customerRole : listCustomerRoles) {
           if("ADMIN".equals(customerRole.getRole().getName())) {
               return "ADMIN";
           }else if("CUSTOMER".equals(customerRole.getRole().getName())) {
               return "CUSTOMER";
           }
       }
       return null;
    }
    

    public void deleteAllCustomerRoles(Customer updateCustomer) {
        em.createQuery("DELETE FROM CustomerRoles cr WHERE cr.customer = :updateCustomer")
            .setParameter("updateCustomer", updateCustomer)
            .executeUpdate();
    }

    public void setNewRoleToCustomer(Role newRole, Customer updateCustomer) {
        CustomerRoles cr = new CustomerRoles();
        if(newRole.getName().equals("ADMIN")) {
            cr.setCustomer(updateCustomer);
            cr.setRole(newRole);
            this.create(cr);
            Role role = roleFacade.getRole("CUSTOMER");
            cr = new CustomerRoles();
            cr.setCustomer(updateCustomer);
            cr.setRole(role);
            this.create(cr);
        }
        if(newRole.getName().equals("CUSTOMER")) {
            cr.setCustomer(updateCustomer);
            cr.setRole(newRole);
            this.create(cr);
        }
    }
}
