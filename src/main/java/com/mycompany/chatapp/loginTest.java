/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
/**
 *
 * @author Student
 */


public class loginTest {

    @Test
    public void testUsernameCorrectEquals() {
        login user = new login();
        assertEquals(true, user.checkUsername("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectEquals() {
        login user = new login();
        assertEquals(false, user.checkUsername("kyle !!!!!!!"));
    }

    @Test
    public void testPasswordCorrectEquals() {
        System.setIn(new ByteArrayInputStream("Ch&&sec@ke99!\n".getBytes()));
        login user = new login();
        assertEquals(true, user.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordIncorrectEquals() {
        System.setIn(new ByteArrayInputStream("password\n".getBytes()));
        login user = new login();
        assertEquals(false, user.checkPasswordComplexity("password"));
    }

    @Test
    public void testPhoneCorrectEquals() {
        System.setIn(new ByteArrayInputStream("+27838968975\n".getBytes()));
        login user = new login();
        assertEquals("+27838968975", user.Cellphone());
    }

    @Test
    public void testPhoneIncorrectEquals() {
        System.setIn(new ByteArrayInputStream("08966553\n".getBytes()));
        login user = new login();
        assertEquals("08966553", user.Cellphone());
    }

    @Test
    public void testUsernameCorrectTrue() {
        login user = new login();
        assertTrue(user.checkUsername("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectFalse() {
        login user = new login();
        assertFalse(user.checkUsername("kyle !!!!!!!"));
    }

    @Test
    public void testPasswordCorrectTrue() {
        System.setIn(new ByteArrayInputStream("Ch&&sec@ke99!\n".getBytes()));
        login user = new login();
        assertTrue(user.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordIncorrectFalse() {
        System.setIn(new ByteArrayInputStream("password\n".getBytes()));
        login user = new login();
        assertFalse(user.checkPasswordComplexity("password"));
    }

    @Test
    public void testLoginCorrectTrue() {
        login user = new login();
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginIncorrectPasswordTrue() {
        login user = new login();
        assertTrue(user.loginUser("kyl_1", "password"));
    }

    @Test
    public void testLoginIncorrectUsernameTrue() {
        login user = new login();
        assertTrue(user.loginUser("kyle !!!!!!!", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginIncorrectUsernameAndPasswordTrue() {
        login user = new login();
        assertTrue(user.loginUser("kyle !!!!!!!", "password"));
    }
}    

