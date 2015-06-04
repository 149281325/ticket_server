/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ticket.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.ticket.entity.TActivity;

/**
 *
 * @author Administrator
 */
public class SysConfig {
    
    private static Properties p = new Properties();
    
    private static int activityId;
    
    @PersistenceContext(unitName = "TicketServerPU")
    private static EntityManager em;
    
    static {
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(SysConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(p.containsKey("activityId")) {
            try {
                activityId = Integer.parseInt(p.getProperty("activityId"));
            } catch(Exception e) {
                activityId = 0;
            }
        }
        if(activityId == 0) {
            loadActivityIdFromDB();
        }
    }
    
    //Get the most recent activity
    private static void loadActivityIdFromDB() {
        List<TActivity> acts = em.createNamedQuery("TActivity.findAll").getResultList();
        if(acts == null || acts.isEmpty()) {
            return;
        }
        acts.sort(new Comparator<TActivity>(){
            @Override
            public int compare(TActivity a1, TActivity a2) {
                if(a1.getTimeFrom() == null || a2.getTimeFrom() == null) return 0;
                if(a1.getTimeFrom().after(a2.getTimeFrom())) {
                    return -1;
                } else if(a1.getTimeFrom().before(a2.getTimeFrom())) {
                    return 1;
                }
                return 0;
            }
        });
        activityId = acts.get(0).getId();
    }
    
    private static void init() throws IOException {
        String domainHome = System.getenv("DOMAIN_HOME");
        System.out.println("domain home: " + domainHome);
        FileReader f = new FileReader(domainHome + "/ticket.sysconfig.properties");
        p.load(f);
        p.list(System.out); 
        f.close();    
    }
    
    public static String getConfig(String key, String defaultVal) {
        return p.getProperty(key, defaultVal);
    }
    
    public static String getConfig(String key) {
        return p.getProperty(key);
    }
    
    public static int getActivityId() {
        return activityId;
    }

    public static double getDefaultPrice() {
        String price = getConfig("defaultPrice");
        if(price != null) {
            try {
                return Double.parseDouble(price);
            } catch(Exception e) {
                Logger.getLogger(SysConfig.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return 0;
    }
    
}
