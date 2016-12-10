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
    private Long numberOfPoints;
    
    public Rule(){
        
    }
    
    public Rule(Application application, PointScale pointScale, Badge badge, String typeEvent, Long numberOfPoints){
   
        this.application = application;
        this.pointScale = pointScale;
        this.badge = badge;
        this.typeEvent = typeEvent;
        this.numberOfPoints = numberOfPoints;
       
    }

    public Long getId() {
        return id;
    }
    
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public PointScale getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScale pointScale) {
        this.pointScale = pointScale;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public Long getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Long numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }
}
