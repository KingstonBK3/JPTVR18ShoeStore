/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "JPTVR18ShoesMarketPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }

    public Customer findByLogin(String login) {
        try {
            return (Customer) em.createQuery("SELECT c FROM Customer c WHERE c.login = :login").setParameter("login", login).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }    
}
