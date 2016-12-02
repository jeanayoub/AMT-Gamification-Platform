package ch.heigvd.gamification.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ch.heigvd.gamification.model.Badge;
import java.util.LinkedList;
import org.springframework.data.repository.CrudRepository;


public interface  BadgesRepository extends CrudRepository <Badge, Long> {

      LinkedList<Badge> findAll();
}
