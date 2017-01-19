package ch.heigvd.gamification.api;


import ch.heigvd.gamification.api.dto.UserGet;
import ch.heigvd.gamification.dao.ApplicationsRepository;
import ch.heigvd.gamification.dao.UsersRepository;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.User;
import ch.heigvd.gamification.utils.toDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by oem on 18.01.17.
 */
public class UsersEndpoint implements UsersApi{

    UsersRepository userRepository;
    ApplicationsRepository applicationsRepository;

    @Autowired
    UsersEndpoint(UsersRepository userRepository, ApplicationsRepository applicationsRepository) {
        this.userRepository = userRepository;
        this.applicationsRepository = applicationsRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserGet>> usersGet(@RequestHeader String token) {
        Application appTmp = applicationsRepository.findByName(token);
        if(appTmp == null) {
            LinkedList<User> listTmp = userRepository.findByApplicationId(appTmp.getId());
            LinkedList<UserGet> listTmpDtoGet = new LinkedList<UserGet>();

            for(User user : listTmp){
                listTmpDtoGet.add(toDTO.userToDTO(user));
            }

            return ResponseEntity.ok().body(listTmpDtoGet);
        }
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
}
