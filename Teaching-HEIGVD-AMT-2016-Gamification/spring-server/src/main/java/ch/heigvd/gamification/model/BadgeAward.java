/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
