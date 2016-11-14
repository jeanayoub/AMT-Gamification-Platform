/*
 *
 */
package app.controller;


import app.model.Badge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * This class offers REST standard operations for a Badge.
 *
 * @author M-H. Aghamahdi, J. Ayoub, S. Baehler, M. Monzione
 * @date 14 Nov 2016
 */
@RestController
public class BadgeController {
    
     @RequestMapping("/badge")
    public Badge badge(@RequestParam(value="desc", defaultValue="World") String name) {
        return new Badge(1 , "name", name, "Image1");
    }
}
