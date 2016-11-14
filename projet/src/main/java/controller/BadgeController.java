/*
 *
 */
package controller;


import java.util.concurrent.atomic.AtomicLong;
import model.Badge;
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
    public Badge badge() {
        return new Badge(1 , "Badge1", "c'est un badge avec le numer 1", "Image1");
    }
}
