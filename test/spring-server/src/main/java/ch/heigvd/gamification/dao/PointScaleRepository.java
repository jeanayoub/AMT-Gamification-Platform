/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import cg.heigvd.gamification.model.PointScale;
import java.util.LinkedList;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author marco
 */
public interface PointScaleRepository extends CrudRepository <PointScale, Long> {
    LinkedList<PointScale> findAll();
}
