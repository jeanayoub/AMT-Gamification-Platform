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

import ch.heigvd.gamification.model.Award;
import org.springframework.data.repository.CrudRepository;

public interface AwardsRepository extends CrudRepository <Award, Long> {
    
}
