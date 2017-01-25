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
public class BadgeAward extends Award{

    
    
    private Date date;
    
    public BadgeAward(){
        
    }
    
    public BadgeAward(Date date){
        super();
        this.date = date;
    }
    
    public Date getDate() {
        return date;
    }
    
}
