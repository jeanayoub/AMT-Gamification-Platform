/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.dao;

import ch.heigvd.gamification.model.User;
import java.util.LinkedList;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author marco
 */
public interface UserRepository extends CrudRepository <User, Long>{
    LinkedList<User> findAll();
}
