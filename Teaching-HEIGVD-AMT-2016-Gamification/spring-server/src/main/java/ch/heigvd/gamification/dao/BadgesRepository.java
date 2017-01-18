package ch.heigvd.gamification.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ch.heigvd.gamification.model.Badge;
import java.util.LinkedList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface  BadgesRepository extends CrudRepository <Badge, Long> {

      LinkedList<Badge> findAll();
      
     // @Query("Select * FROM Badge where Badge.id = idBadge")
     // Badge findByBadgeIdApplicationId(@Param("idBadge") Long idBadge, @Param("idApp") Long idApp);
     
      LinkedList<Badge>findByApplicationId(Long id);
      
      
      
      
}
