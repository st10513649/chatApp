/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.*;
import java.util.regex.*;


/**
 *
 * @author Student
 */
public class UserRegestration {

    private String username;
    private String password;
    private String cellnumber;
    private String firstname;
    private String lastname;
    Scanner input = new Scanner(System.in);
    
    public boolean Username(){
        System.out.println("Enter your name: ");
        firstname = input.next();
        System.out.println("Enter your surname: ");
        lastname = input.next();
        boolean usernameVerdict = false;
        System.out.println("Input a username that is lessthan five characters long: ");
        username = input.next();
        
        if(username.length() <= 5){
            if(username.charAt(0) == '_' || username.charAt(1) == '_' || username.charAt(2) == '_' || username.charAt(3) == '_' || username.charAt(4) == '_' ){
                
                System.out.println("username successfully captured!!!");
                usernameVerdict = true;
            }
            else{
                 System.out.println("Username is not correctly formatted, please make sure that your username contains an underscore and is not more than five characters");
            }
        }
        else{
            System.out.println("Username is not correctly formatted, please make sure that your username contains an underscore and is not more than five characters");
        }
        
        return usernameVerdict;
    }
    
    public boolean Password(){
        boolean passwordVerdict = false;
        boolean isDigit = false;
        boolean isSpecial = false;
        boolean isUpper = false;
        
        System.out.println("Enter your password");
        password = input.next();
        if(password.length() >= 8){
            for(char ch: password.toCharArray())
            {
                if(Character.isUpperCase(ch)){
                  isUpper = true;
                }
                else if(Character.isDigit(ch)){
                        isDigit = true;
                }
                else if(!Character.isDigit(ch) && !Character.isLetter(ch)){ 
                isSpecial = true;
                }
            }
            
        }
        if(isDigit ==true && isSpecial == true && isUpper == true){
            System.out.println("Password successfully captured");
            passwordVerdict = true;
            return passwordVerdict;
        }
        else{
            System.out.println("Password is not correctly formatted; please ensure that the password contains atleast 8 characters");
            return passwordVerdict;
        }
    }
    public String Cellphone(){
        System.out.println("Enter your cellphone number starting with yourcountry code");
        cellnumber = input.next();
        String regex = "\\+27\\d(9)";
        if(cellnumber.matches(regex)){
            System.out.println("Cell phone number successfully added");
        }
        else{
            System.out.println("Cell phone number incorrectly formatted or does not contain international code");
        }
        return cellnumber;
    }
    public boolean Login(){
    boolean loginVerdict = false;
        System.out.println("Profile creted");
        System.out.println("Enter your username: ");
        String tempusername =input.next();
        System.out.println("Enter your password: ");
        String tempPassword =input.next();
        if(tempPassword.equals(password) && tempusername.equals(username)){
            System.out.println("Welcome " + firstname + " " + lastname + "It is great to see you again");
            loginVerdict = true; 
        }
        else{
            System.out.println("Username or password incorrect,please try again");
        }
        return loginVerdict;
    }
}
   

