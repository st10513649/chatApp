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
public class QuickChat {

    private static boolean isLoggedIn = false; 

    private static Scanner scanner = new Scanner(System.in); 

 

    public static void main(String[] args) { 

        // Simple login (extend from previous Learning Units if you already have registration) 

        login(); 

 

        if (!isLoggedIn) { 

            System.out.println("You must be logged in to use QuickChat."); 

            return; 

        } 

 

        System.out.println("Welcome to QuickChat."); 

 

        // Ask how many messages the user wants to send 

        System.out.print("How many messages do you wish to send? "); 

        int maxMessages = Integer.parseInt(scanner.nextLine()); 

 

        int messagesEntered = 0; 

 

        while (true) { 

            System.out.println("\nPlease choose an option:"); 

            System.out.println("1) Send Messages"); 

            System.out.println("2) Show recently sent messages"); 

            System.out.println("3) Quit"); 

            System.out.print("Enter choice: "); 

            String choice = scanner.nextLine().trim(); 

 

            switch (choice) { 

                case "1": 

                    if (messagesEntered >= maxMessages) { 

                        System.out.println("You have reached the maximum number of messages (" + maxMessages + ")."); 

                        break; 

                    } 

                    sendMessageFlow(messagesEntered + 1); 

                    messagesEntered++; 

                    break; 

                case "2": 

                    System.out.println("Coming Soon."); 

                    break; 

                case "3": 

                    Message msg = new Message(); 

                    System.out.println("\nTotal messages sent: " + msg.returnTotalMessagess()); 

                    System.out.println("Thank you for using QuickChat. Goodbye!"); 

                    return; 

                default: 

                    System.out.println("Invalid option. Please try again."); 

            } 

        } 

    } 

 

    private static void login() { 

        System.out.print("Enter username: "); 

        String user = scanner.nextLine(); 

        System.out.print("Enter password: "); 

        String pass = scanner.nextLine(); 

 

        // Simple hard-coded check – replace with your previous registration logic 

        if (user.equalsIgnoreCase("admin") && pass.equals("password123")) { 

            isLoggedIn = true; 

            System.out.println("Login successful!"); 

        } else { 

            System.out.println("Login failed."); 

        } 

    } 

 

    private static void sendMessageFlow(int currentNum) { 

        Message message = new Message(); 

 

        // Recipient 

        System.out.print("Enter recipient cell number (with international code, e.g. +27718693002): "); 

        String cell = scanner.nextLine(); 

        String cellResult = message.checkRecipientCell(cell); 

        System.out.println(cellResult); 

        if (!cellResult.contains("successfully")) { 

            return; 

        } 

 

        // Message text 

        System.out.print("Enter your message (max 250 characters): "); 

        String text = scanner.nextLine(); 

        String lengthResult = message.validateMessageLength(text); 

        System.out.println(lengthResult); 

        if (!lengthResult.equals("Message ready to send.")) { 

            return; 

        } 

 

        // Show options 

        System.out.println("\nChoose an action:"); 

        System.out.println("1) Send Message"); 

        System.out.println("2) Disregard Message"); 

        System.out.println("3) Store Message to send later"); 

        System.out.print("Enter choice: "); 

        int action = Integer.parseInt(scanner.nextLine()); 

 

        String result = message.SentMessage(action); 

        System.out.println(result); 

 

        if (action == 1) { // Only display full details when actually sent 

            System.out.println("\n----- Message Details -----"); 

            System.out.println("Message ID: " + message.getMessageID()); 

            System.out.println("Message Hash: " + message.getMessageHash()); 

            System.out.println("Recipient: " + message.getRecipient()); 

            System.out.println("Message: " + message.getMessageText()); 

            System.out.println("---------------------------"); 

        } 

    } 

}     

