/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.util.LinkedList;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author marco
 */
public interface ApplicationRepository  extends CrudRepository <Application, Long> {
    LinkedList<Application> findAll();
    Application findByName(String name);
}
