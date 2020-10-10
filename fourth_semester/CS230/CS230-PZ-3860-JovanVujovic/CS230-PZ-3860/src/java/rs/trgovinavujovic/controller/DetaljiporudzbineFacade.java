/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.trgovinavujovic.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rs.trgovinavujovic.entities.Detaljiporudzbine;

/**
 *
 * @author Jovan Vujovic
 */
@Stateless
public class DetaljiporudzbineFacade extends AbstractFacade<Detaljiporudzbine> {

    @PersistenceContext(unitName = "CS230-PZ-3860PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetaljiporudzbineFacade() {
        super(Detaljiporudzbine.class);
    }
    
}
