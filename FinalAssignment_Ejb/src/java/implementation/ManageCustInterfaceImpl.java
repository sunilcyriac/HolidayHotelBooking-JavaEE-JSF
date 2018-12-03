/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import java.util.List;
import javax.persistence.EntityManager;
import entities.Customers;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.persistence.PersistenceContext;
import interfaces.ManageCustInterfaceRemote;

/**
 *
 * @author Sunil
 */

//Criteria API query method is used here
//referred from various sources including weekly tasks and stackoverflow
@ManagedBean(name = "ManageCustInterfaceImpl")
@Stateless
public class ManageCustInterfaceImpl implements ManageCustInterfaceRemote {
    
 @javax.persistence.PersistenceContext(unitName="FinalAssignment_EjbPU")

  private EntityManager em;


    @Override
    public void create(Customers entity) {
        em.persist(entity);
    }

    @Override
    public void edit(Customers entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Customers entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public Customers find(Object id) {
        return em.find(Customers.class, id);
    }

    @Override
    public List<Customers> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Customers.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Customers> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Customers.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Customers> rt = cq.from(Customers.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
