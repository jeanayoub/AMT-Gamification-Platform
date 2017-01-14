/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.model;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class PointAward extends Award{

     
    private Long awardedPoint;
    private Date date;

    public PointAward(Date date){
        super();
        this.date = date;
    }
    
      public Long getAwardedPoint() {
        return awardedPoint;
    }

    public void setAwardedPoint(Long awardedPoint) {
        this.awardedPoint = awardedPoint;
    }
}
