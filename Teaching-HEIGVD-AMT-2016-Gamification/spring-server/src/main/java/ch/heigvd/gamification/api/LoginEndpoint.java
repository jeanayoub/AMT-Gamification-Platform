/**
 * Author : Aghamahdi Mohammad Hossein
 *          Ayoub jean
 *          Baehler Simon
 *          Monzione Marco
 * 
 * Project : AMT-Gamification-platform
 * 
 * Date : 25.01.2017
 *          
 */

package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.LoginGet;
import ch.heigvd.gamification.api.dto.LoginPost;
import ch.heigvd.gamification.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ch.heigvd.gamification.dao.ApplicationsRepository;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoginEndpoint implements LoginApi{

    ApplicationsRepository applicationRepository;
    
    @Autowired
    LoginEndpoint(ApplicationsRepository applicationRepository) {
            this.applicationRepository = applicationRepository;
    }
    
    
    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> loginPost(@RequestBody LoginPost loginInfo) {
        
        Application appTmp = applicationRepository.findByName(loginInfo.getName());
        LoginGet loginGet = new LoginGet();
        
        if(appTmp != null){
            if(appTmp.getPassword().equalsIgnoreCase(loginInfo.getPassword())){
                
                loginGet.setToken(appTmp.getName()); 
                return ResponseEntity.status(HttpStatus.OK).body(loginGet);
            }
        }
        
        loginGet.setToken("");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginGet);
    }
    
}
