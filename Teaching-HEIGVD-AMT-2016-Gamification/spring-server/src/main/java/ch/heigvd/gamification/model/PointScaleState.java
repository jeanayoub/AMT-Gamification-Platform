/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PointScaleState {
    
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long   id;
    
    private long points;
    
    
    @OneToOne
    private PointScale pointScale;
    
    //@ManyToOne(mappedBy = "User")
    //private List<User> listUser = new ArrayList();
    
}
