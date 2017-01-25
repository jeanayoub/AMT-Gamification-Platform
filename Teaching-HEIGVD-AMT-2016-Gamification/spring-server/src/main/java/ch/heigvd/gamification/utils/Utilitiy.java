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

package ch.heigvd.gamification.utils;

import ch.heigvd.gamification.api.LoginEndpoint;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

public class Utilitiy {
    
    static String MD5Converter(String str){
        
        MessageDigest md;
        String md5Hash = "";
                
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            md5Hash = DatatypeConverter.printHexBinary(digest).toUpperCase();    

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        return md5Hash;
    }
    
}
