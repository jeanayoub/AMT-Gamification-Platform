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

@Entity
public class UserPointScaleAssociation {
   
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private       long   id;
    
   // @Id
   // private long userId;
   // @Id
   // private long pointScaleId;
    
    //@Column
    private long numberOfPoint;
    
   
    @ManyToOne
    //@PrimaryKeyJoinColumn(name="userId", referencedColumnName="ID")
    private User user;
    
   
    @ManyToOne
   // @PrimaryKeyJoinColumn(name="pointScaleId", referencedColumnName="ID")
    private PointScale pointScale;
    
    
    
    
}
