/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entity.Customer;
import entity.Shoe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class ShoeFacade extends AbstractFacade<Shoe> {

    @PersistenceContext(unitName = "JPTVR18ShoesMarketPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShoeFacade() {
        super(Shoe.class);
    }

    public List<Shoe> findByCustomer(Customer customer) {
        try {
            return em.createQuery("SELECT cg.shoes FROM CustomerShoess cg WHERE cg.customer = :customer").setParameter("customer", customer).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
