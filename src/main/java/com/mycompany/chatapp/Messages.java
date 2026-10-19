/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.ArrayList; 
import java.util.List; 
import java.util.Random; 
import java.io.FileWriter; 
import java.io.IOException; 
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;   // You can use Gson instead if preferred 

/**
 *
 * @author Student
 */
public class Message{ 
    private String messageID; 
    private int numMessagesSent; 
    private String recipient; 
    private String messageText; 
    private String messageHash; 
    private static int totalMessages = 0; 
    private static List<Message> sentMessages = new ArrayList<>(); 
   
    public Message() { 

        this.messageID = generateMessageID(); 
        this.numMessagesSent = 0; 
    } 

    // Generates a random 10-digit Message ID 

    private String generateMessageID() { 

        Random rand = new Random(); 

        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L); 

        return String.valueOf(id); 

    } 
    
    // Boolean: checkMessageID() – ensures Message ID is not more than 10 characters 

    public boolean checkMessageID() { 

        return messageID != null && messageID.length() <= 10; 

    } 

    // String: checkRecipientCell() – max 10 chars? + must start with international code 

    // (Note: test data exceeds 10 chars; implementation allows up to 15 and requires + or leading 0) 

    public String checkRecipientCell(String cell) { 

        if (cell == null || cell.trim().isEmpty()) { 

            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again."; 

        } 

        String cleaned = cell.trim(); 

        // Accept numbers starting with + or 0 and reasonable length 

        if ((cleaned.startsWith("+") || cleaned.startsWith("0")) && cleaned.length() <= 15) { 

            this.recipient = cleaned; 

            return "Cell phone number successfully captured."; 

        } 
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again."; 

    } 

 

    // String: createMessageHash() 

    public String createMessageHash() { 

        if (messageID == null || messageText == null || messageText.trim().isEmpty()) { 

            return ""; 

        } 

        String firstTwo = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID; 

        String[] words = messageText.trim().split("\\s+"); 

        String firstWord = words.length > 0 ? words[0].replaceAll("[^a-zA-Z]", "") : ""; 

        String lastWord = words.length > 0 ? words[words.length - 1].replaceAll("[^a-zA-Z]", "") : ""; 

        this.messageHash = (firstTwo + ":" + numMessagesSent + ":" + firstWord + lastWord).toUpperCase(); 

        return this.messageHash; 

    } 

    // Validates message length ≤ 250 characters 

    public String validateMessageLength(String text) { 

        if (text == null) text = ""; 

        if (text.length() > 250) { 

            int excess = text.length() - 250; 

            return "Message exceeds 250 characters by " + excess + "; please reduce the size."; 

        } 

        this.messageText = text; 

        return "Message ready to send."; 

    } 

    // String: SentMessage() – choose Send / Disregard / Store 

    public String SentMessage(int choice) { 

        switch (choice) { 

            case 1: // Send Message 

                totalMessages++; 

                this.numMessagesSent = totalMessages; 

                createMessageHash(); 

                sentMessages.add(this); 

                return "Message successfully sent."; 

            case 2: // Disregard Message 

                return "Press 0 to delete the message."; 

            case 3: // Store Message 

                createMessageHash(); 

                storedMessages.add(this); 

                storeMessage(); // also write to JSON 

                return "Message successfully stored."; 

            default: 

                return "Invalid option."; 

        } 

    } 

 

    // String: printMessages() 

    public String printMessages() { 

        if (sentMessages.isEmpty()) { 

            return "No messages have been sent yet."; 

        } 

        StringBuilder sb = new StringBuilder(); 

        for (Message m : sentMessages) { 

            sb.append("Message ID: ").append(m.messageID).append("\n") 

              .append("Message Hash: ").append(m.messageHash).append("\n") 

              .append("Recipient: ").append(m.recipient).append("\n") 

              .append("Message: ").append(m.messageText).append("\n") 

              .append("------------------------\n"); 

        } 

        return sb.toString(); 

    } 

 

    // Int: returnTotalMessagess() 

    public int returnTotalMessagess() { 

        return totalMessages; 

    } 

 

    // Custom storeMessage() – stores messages in a JSON file 

    @SuppressWarnings("unchecked") 

    public void storeMessage() { 

        JSONArray jsonArray = new JSONArray(); 

        for (Message m : storedMessages) { 

            JSONObject obj = new JSONObject(); 

            obj.put("messageID", m.messageID); 

            obj.put("messageHash", m.messageHash); 

            obj.put("recipient", m.recipient); 

            obj.put("message", m.messageText); 

            obj.put("numMessagesSent", m.numMessagesSent); 

            jsonArray.add(obj); 

        } 

        try (FileWriter file = new FileWriter("stored_messages.json")) { 

            file.write(jsonArray.toJSONString()); 

            file.flush(); 

        } catch (IOException e) { 

            System.out.println("Error writing JSON file: " + e.getMessage()); 

        } 

    } 
    // Getters 

    public String getMessageID() { return messageID; } 

    public String getRecipient() { return recipient; } 

    public String getMessageText() { return messageText; } 

    public String getMessageHash() { return messageHash; } 

    public int getNumMessagesSent() { return numMessagesSent; } 

} 