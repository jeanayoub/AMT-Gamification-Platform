/*
 *
 */
package app.controller;


import app.model.Badge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Ayoubo
 */
@RestController
public class BadgeController {
    
     @RequestMapping("/badge")
    public Badge badge(@RequestParam(value="desc", defaultValue="World") String name) {
        return new Badge(1 , "name", name, "Image1");
    }
}
