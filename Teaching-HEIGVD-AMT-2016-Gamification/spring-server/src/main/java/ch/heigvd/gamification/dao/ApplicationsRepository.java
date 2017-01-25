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

import ch.heigvd.gamification.model.Application;
import java.util.LinkedList;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationsRepository  extends CrudRepository <Application, Long> {
    LinkedList<Application> findAll();
    Application findByName(String name);
}
