/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.LoginGet;
import ch.heigvd.gamification.api.dto.LoginPost;
import ch.heigvd.gamification.dao.ApplicationRepository;
import ch.heigvd.gamification.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginEndpoint implements LoginApi{

    ApplicationRepository applicationRepository;
    
    @Autowired
    LoginEndpoint(ApplicationRepository applicationRepository) {
            this.applicationRepository = applicationRepository;
    }
    
    
    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> loginPost(LoginPost loginInfo) {
        
        Application appTmp = applicationRepository.findByName(loginInfo.getName());
        LoginGet loginGet = new LoginGet();
        
        if(appTmp != null){
            if(appTmp.getPassword().equalsIgnoreCase(loginInfo.getPassword())){

                loginGet.setToken(appTmp.getName());
                return ResponseEntity.ok().body(loginGet);
            }
            
        }
        
        loginGet.setToken("");
        return ResponseEntity.ok().body(loginGet);
    }
    
}
