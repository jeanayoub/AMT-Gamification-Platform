/**
 * Author : Aghamahdi Mohammad Hossein
 *          Ayoub jean
 *          Baehler Simon
 *          Monzione Marco
 * 
 * Project : AMT-Gamification-platform
 * 
 * Date : 25.01.2017
 *          
 */

package ch.heigvd.gamification.model;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class PointAward extends Award{

    private Long awardedPoint;
    private Date date;

    public PointAward(){
    
    }

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
    
    public Date getDate() {
        return date;
    }
}
