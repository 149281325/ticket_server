/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ticket.entity.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.ticket.entity.TOrder;

/**
 *
 * @author Administrator
 */
@Stateless
@Path("org.ticket.entity.torder")
public class TOrderFacadeREST extends AbstractFacade<TOrder> {
    @PersistenceContext(unitName = "TicketServerPU")
    private EntityManager em;

    public TOrderFacadeREST() {
        super(TOrder.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(TOrder entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, TOrder entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public TOrder find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<TOrder> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<TOrder> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("query")
    @Produces({"application/json"})
    public List<TOrder> findByCardAndActivity(@QueryParam("cardId") Integer cardId, @QueryParam("actId") Integer actId) {
        Query q = em.createNamedQuery("TOrder.findByCardAndActivity");
        q.setParameter("cardId", cardId).setParameter("activityId", actId);
        return q.getResultList();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
