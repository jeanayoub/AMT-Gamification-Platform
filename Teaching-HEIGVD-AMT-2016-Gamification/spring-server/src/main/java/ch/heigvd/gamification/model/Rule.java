/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rule {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Application application;
    
    @ManyToOne
    private PointScale pointScale;
    
    @ManyToOne
    private Badge badge;
    
    private String typeEvent;
    private boolean awardBadge;
    private boolean awardPoint;
    private long ruleValue;
    
    public Rule(){
        
    }
    
    public Rule(Application application, PointScale pointScale, Badge badge, String typeEvent, boolean awardBadge, boolean awardPoint, long ruleValue){
        
    }

}
