/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Student
 */
public class Messages {
private String MessageID;
private String sentMessages;
private String Recipient;
private String Message;
private String MessageHash;
private String sendMessage;

private static int totalMessages =0;
private static String sentMessage ="";

private static String storeMessage =""; 

public boolean checkMessageID(){
    return MessageID.lenght <= 10;
    }
public String checkReecipientCell(){
    if(Recipient.lenght() !=12){
    return false;
    }
    if(!recipient.startsWith("+27")){
    return false
    }
    for (int i=3; i< recipient.lenght(); i++){
    if(!Character.isDigit (recipient.charAt(i))){
        return false;
    }
    }
    return true;
    private messagelenght(); 
    }
public String createMessageHash(){

    }
public String sentMessages(){
    
    }
public String printMessage(){
    
    }
public int returnTotalMessages(){

    }
}

