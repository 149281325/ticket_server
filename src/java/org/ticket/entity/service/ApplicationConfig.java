/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ticket.entity.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Administrator
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.ticket.business.CardService.class);
        resources.add(org.ticket.entity.service.TActivityFacadeREST.class);
        resources.add(org.ticket.entity.service.TCardFacadeREST.class);
        resources.add(org.ticket.entity.service.TMCardtypeFacadeREST.class);
        resources.add(org.ticket.entity.service.TMPaytypeFacadeREST.class);
        resources.add(org.ticket.entity.service.TMStateFacadeREST.class);
        resources.add(org.ticket.entity.service.TMUsertypeFacadeREST.class);
        resources.add(org.ticket.entity.service.TOrderFacadeREST.class);
        resources.add(org.ticket.entity.service.TRegistryFacadeREST.class);
        resources.add(org.ticket.entity.service.TUserFacadeREST.class);
    }
    
}
