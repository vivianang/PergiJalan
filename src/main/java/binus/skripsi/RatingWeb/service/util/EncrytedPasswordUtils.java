package binus.skripsi.RatingWeb.service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
 
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
 
    public static void main(String[] args) {
        String password1 = "atur123";
        String password2 = "eric123";
        String password3 = "widhi123";
        String password4 = "febri123";
        String password5 = "galih123";
        String password6 = "vivian123";
        String password7 = "kresti123";
        String password8 = "bram123";
        String encrytedPassword1 = encrytePassword(password1);
        String encrytedPassword2 = encrytePassword(password2);
        String encrytedPassword3 = encrytePassword(password3);
        String encrytedPassword4 = encrytePassword(password4);
        String encrytedPassword5 = encrytePassword(password5);
        String encrytedPassword6 = encrytePassword(password6);
        String encrytedPassword7 = encrytePassword(password7);
        String encrytedPassword8 = encrytePassword(password8);
 
        System.out.println("Encryted Password: " + encrytedPassword1);
        System.out.println("Encryted Password: " + encrytedPassword2);
        System.out.println("Encryted Password: " + encrytedPassword3);
        System.out.println("Encryted Password: " + encrytedPassword4);
        System.out.println("Encryted Password: " + encrytedPassword5);
        System.out.println("Encryted Password: " + encrytedPassword6);
        System.out.println("Encryted Password: " + encrytedPassword7);
        System.out.println("Encryted Password: " + encrytedPassword8);
        
    }
     
}
