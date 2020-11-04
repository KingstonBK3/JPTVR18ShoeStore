/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entity.CustomerShoes;
import entity.Shoe;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class CustomerShoesFacade extends AbstractFacade<CustomerShoes> {

    @PersistenceContext(unitName = "JPTVR18ShoeStorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerShoesFacade() {
        super(CustomerShoes.class);
    }

    public void removeByShoe(Shoe deleteShoe) {
       em.createQuery("DELETE FROM CustomerShoes cg WHERE cg.shoe = :shoe").setParameter("shoe",deleteShoe).executeUpdate();
    }
    
}
