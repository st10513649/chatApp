/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

/**
 *
 * @author Student
 */
public class ChatApp {

    public static void main(String[] args) {


        UserRegestration user = new UserRegestration();

        System.out.println("===== USER REGISTRATION =====");

        // Register username
        boolean usernameRegistered = user.Username();

        // Register password
        boolean passwordRegistered = user.Password();

        // Register cellphone number
        user.Cellphone();

        // Check whether registration details are valid
        if (usernameRegistered && passwordRegistered) {

            System.out.println("\n===== LOGIN =====");

            // Attempt to login
            boolean loginSuccessful = user.Login();

            if (loginSuccessful) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed.");
            }

        } else {
            System.out.println("\nRegistration was unsuccessful.");
            System.out.println("Please correct your username and password.");
        }
    }
}
