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
import javax.persistence.OneToOne;






@Entity
public class Progression {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long   id;
    private Long actualPoint;
    
    @OneToOne
    private PointScale pointScale;
    
    @OneToOne
    private User user;
    
    public Progression(){
        
    }
    
    public Progression(PointScale pointScale, User user){
        this.pointScale = pointScale;
        this.user = user;
        this.actualPoint = 0L;
    }
    
    public void addPoint(Long points){
        actualPoint += points;
    }
    
    public void setActualPoint(Long actualPoint) {
        this.actualPoint = actualPoint;
    }

    public void setPointScale(PointScale pointScale) {
        this.pointScale = pointScale;
    }
    
    public Long getId() {
        return id;
    }

    public Long getActualPoint() {
        return actualPoint;
    }

    public PointScale getPointScale() {
        return pointScale;
    }

    public User getUser() {
        return user;
    }
}
