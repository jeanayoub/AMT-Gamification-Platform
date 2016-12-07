/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


public class PointAward extends Award{
    
    private long actualValue;
    private long criticalValue; 
    
    public PointAward(){
        super();
    }
   
      public long getActualValue() {
        return actualValue;
    }

    public void setActualValue(long actualValue) {
        this.actualValue = actualValue;
    }

    public long getCriticalValue() {
        return criticalValue;
    }

    public void setCriticalValue(long criticalValue) {
        this.criticalValue = criticalValue;
    }
    
   // @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    //private       long   id;
        

    //private long awardPoint;
    
    /*@ManyToOne
    //@PrimaryKeyJoinColumn(name="userId", referencedColumnName="ID")
    private User user;
    
   
    @ManyToOne
   // @PrimaryKeyJoinColumn(name="pointScaleId", referencedColumnName="ID")
    private PointScale pointScale;*/
    
    
    
    
}
