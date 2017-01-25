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

import ch.heigvd.gamification.model.PointScale;
import java.util.LinkedList;
import org.springframework.data.repository.CrudRepository;


public interface PointScalesRepository extends CrudRepository <PointScale, Long> {
    LinkedList<PointScale> findAll();
    PointScale findById(Long id);
    LinkedList<PointScale>findByApplicationId(Long id);
}
