/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class login {
  
    
    private String username;
    private String password;
    private String cellnumber;
    private String firstname ;
    private String lastname;
    Scanner input = new Scanner(System.in);
    public boolean checkUsername(String username){
        return username.contains("_") && username.length() <=5;
        
        }
    public boolean checkPasswordComplexity(String password){
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
        if(cellnumber.matches(regex) == true){
            System.out.println("Cell phone number successfully added");
        }
        else{
            System.out.println("Cell phone number incorrectly formatted or does not contain international code");
        }
        return cellnumber;
    }
    
    // user registering
    public String registerUser(){
           String verdict = ""; 
           if(checkUsername(username)){
               verdict ="Username is not correctly formatted;please ensure that your username contains an underscore and is not more than five characters";
           }
           if(checkPasswordComplexity(password)){
               verdict = "Password is not correctly formatted; please ensure that the password contains atleast 8 characters";
           }
           if(checkPasswordComplexity(password)== true && checkUsername(username) == true){
           verdict = "you have successfully regestered";
           }
           return verdict;
        }
    
    // user logging in
    public boolean loginUser(String username, String password){
        this.username = username;
        this.password = password;
        if(username.equals(username) && password.equals(password)){
        return true;
        }
        return false;
    }
    
    
    // result of user logging in
    public String loginStatus(boolean isLoggedIn){
        if(isLoggedIn){
            System.out.println("Welcome"+ this.firstname + this.lastname + "it is great to see you again");
        }
        else{
            System.out.println("username or Password incorrect, please try again"); 
            }
        return "User has successfully logged in" ;
    }
}
  
