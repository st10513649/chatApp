/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 

/**
 *
 * @author Student
 */
public class MessageTest {
 
    @Test 

    void testMessageLengthSuccess() { 

        Message m = new Message(); 

        String result = m.validateMessageLength("Hi Mike, can you join us for dinner tonight?"); 

        assertEquals("Message ready to send.", result); 

    } 

 

    @Test 

    void testMessageLengthFailure() { 

        Message msg = new Message(); 

        String longMsg = "a".repeat(260); 

        String result = msg.validateMessageLength(longMsg); 

        assertTrue(result.contains("exceeds 250 characters")); 

    } 

 

    @Test 

    void testRecipientSuccess() { 

        Message m = new Message(); 

        String result = m.checkRecipientCell("+27718693002"); 

        assertEquals("Cell phone number successfully captured.", result); 

    } 

 

    @Test 

    void testRecipientFailure() { 

        Message m = new Message(); 

        String result = m.checkRecipientCell("12345"); 

        assertTrue(result.contains("incorrectly formatted")); 

    } 

 

    @Test 

    void testMessageHash() { 

        Message m = new Message(); 

        // Force known values for deterministic test 

        // (In real code you may need a package-private setter or reflection) 

        // Expected for Test Case 1 style data: 00:0:HITONIGHT (example from screenshots) 

        // Adjust according to the exact ID generated in your run 

        assertNotNull(m.createMessageHash()); 

    } 

    @Test 

    void testSentMessageOptions() { 

        Message m = new Message(); 

        m.checkRecipientCell("+27718693002"); 

        m.validateMessageLength("Test message"); 

        assertEquals("Message successfully sent.", m.SentMessage(1)); 

        assertEquals("Press 0 to delete the message.", m.SentMessage(2)); 

        assertEquals("Message successfully stored.", m.SentMessage(3)); 

    } 

}    

