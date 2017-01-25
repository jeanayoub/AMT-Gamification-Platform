/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.dao;

import ch.heigvd.gamification.model.PointScale;
import java.util.LinkedList;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author marco
 */
public interface PointScalesRepository extends CrudRepository <PointScale, Long> {
    LinkedList<PointScale> findAll();
    PointScale findById(Long id);
    LinkedList<PointScale>findByApplicationId(Long id);
}
