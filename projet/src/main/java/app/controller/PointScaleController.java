/*
 * 
 */
package app.controller;


import app.model.PointScale;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Map;

/**
 * This class offers REST standard operations for a PointScale.
 *
 * @author M-H. Aghamahdi, J. Ayoub, S. Baehler, M. Monzione
 * @date 14 Nov 2016
 */
@RestController
public class PointScaleController {
    LinkedList<PointScale> PointScalesList = new LinkedList<PointScale>();
    int count = 0;

    @RequestMapping("/PointScales/{id}")
    public PointScale badge(@PathVariable("id") int id) {
        for(int i = 0; i <  PointScalesList.size(); i++) {
            if( PointScalesList.get(i).getID() == id)
            {
                return  PointScalesList.get(i);
            }
        }
        return null;
    }

    @RequestMapping("/PointScales")
    public LinkedList badges() {
        return  PointScalesList;
    }

    @RequestMapping(value = "/PointScales", method = RequestMethod.POST)
    public PointScale doPost(@RequestBody Map<String, Object> payload) {
        PointScale l = new PointScale(++count, payload.get("name").toString(), payload.get("desc").toString(), payload.get("image").toString());
        PointScalesList.add(l);
        return l;
    }
    @RequestMapping(value = "/PointScales/{id}", method = RequestMethod.PUT)
    public PointScale doLPut(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) {
        String name = payload.get("name").toString();
        String desc = payload.get("desc").toString();
        String image = payload.get("image").toString();
        for(int i = 0; i <  PointScalesList.size(); i++) {
            if ( PointScalesList.get(i).getID() == id) {
                if (name != null)
                     PointScalesList.get(i).setName(name);
                if (desc != null)
                     PointScalesList.get(i).setDescription(desc);
                if (image != null)
                     PointScalesList.get(i).setIcon(image);
            }
            return  PointScalesList.get(i);
        }
        return null;
    }


    /**
     * delete le pointScale passé en parametre
     *
     * @date 15 Nov 2016
     * @param id
     * @return delete pointScale avec l'id en param, null si rien n'est trouvé
     */
    @RequestMapping(value = "/PointScales/{id}", method = RequestMethod.DELETE)
    public PointScale doDelete(@PathVariable("id") int id) {
        for(int i = 0; i < PointScalesList.size(); i++) {
            if(PointScalesList.get(i).getID() == id)
            {
                PointScalesList.remove(i);
            }
        }
        return null;
    }
}
