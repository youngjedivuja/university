/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.metropolitan.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Jovan Vujovic
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
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
        resources.add(rs.ac.metropolitan.rest.DetaljiporudzbineFacadeREST.class);
        resources.add(rs.ac.metropolitan.rest.KorisnikFacadeREST.class);
        resources.add(rs.ac.metropolitan.rest.KupacFacadeREST.class);
        resources.add(rs.ac.metropolitan.rest.PorudzbinaFacadeREST.class);
        resources.add(rs.ac.metropolitan.rest.ProizvodFacadeREST.class);
        resources.add(rs.ac.metropolitan.rest.RolaFacadeREST.class);
    }
    
}
