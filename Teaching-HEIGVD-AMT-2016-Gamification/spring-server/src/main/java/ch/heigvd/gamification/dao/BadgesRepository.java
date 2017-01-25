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

package ch.heigvd.gamification.dao;

import ch.heigvd.gamification.model.Badge;
import java.util.LinkedList;
import org.springframework.data.repository.CrudRepository;


public interface  BadgesRepository extends CrudRepository <Badge, Long> {

      LinkedList<Badge> findAll();
      LinkedList<Badge>findByApplicationId(Long id);
      
      
      
      
}
