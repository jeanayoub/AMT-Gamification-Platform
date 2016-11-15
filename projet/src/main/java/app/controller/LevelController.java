/*
 * 
 */
package app.controller;


import app.model.Level;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

/**
 * This class offers REST standard operations for a Level.
 *
 * @author M-H. Aghamahdi, J. Ayoub, S. Baehler, M. Monzione
 * @date 14 Nov 2016
 */
@RestController
public class LevelController {
    LinkedList<Level> LevelsList = new LinkedList<Level>();
    int count = 0;

    @RequestMapping("/level")
    public Level badge(@RequestParam(value="id") int id) {
        for(int i = 0; i < LevelsList.size(); i++) {
            if(LevelsList.get(i).getID() == id)
            {
                return LevelsList.get(i);
            }
        }
        return null;
    }

    @RequestMapping("/levels")
    public LinkedList badges() {
        return LevelsList;
    }

    @RequestMapping(value = "/level", method = RequestMethod.POST)
    public Level doPost(@RequestParam int no, String name, String desc, String image) {
        Level l = new Level(++count, no, name, desc, image);
        LevelsList.add(l);
        return l;
    }
    @RequestMapping(value = "/level", method = RequestMethod.PUT)
    public Level doLPut(@RequestParam int id, String name, String desc, String image) {
        for(int i = 0; i < LevelsList.size(); i++) {
            if (LevelsList.get(i).getID() == id) {
                if (name != null)
                    LevelsList.get(i).setName(name);
                if (desc != null)
                    LevelsList.get(i).setDescription(desc);
                if (image != null)
                    LevelsList.get(i).setIcon(image);
            }
            return LevelsList.get(i);
        }
        return null;
    }
}
