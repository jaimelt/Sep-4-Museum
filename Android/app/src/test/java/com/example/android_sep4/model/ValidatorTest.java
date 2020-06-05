package com.example.android_sep4.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ValidatorTest {

    private Validator validator;
    private String email;
    private String email2;
    private String password;
    private String password2;
    private String repeatPassword;
    private String repeatPassword2;
    private String co2;
    private String temperature;
    private String light;
    private String humidity;
    private String empty;
    private String allowedName;
    private String notAllowed;
    private String nullString;


    @Before
    public void setUp() throws Exception {
        validator = new Validator();
        email = "wrongemail";
        email2 = "correct@email.com";
        password = "2']-/";
        password2 = "a12t3g5.3'";
        repeatPassword = "000";
        repeatPassword2 = "a12t3g5.3'";
        co2 = "35";
        temperature = "20";
        light = "300";
        humidity = "20";
        empty= "";
        allowedName = "Leonardo Da Vinci";
        notAllowed = ".Leonardo Da! Vinci";
    }

    @Test
    public void validateRegister() {
        int result1 = validator.validateRegister(email, password2, repeatPassword);
        Assert.assertEquals(2, result1, 0);

        int result2 = validator.validateRegister(email2, password, repeatPassword);
        Assert.assertEquals(3, result2, 0);

        int result3 = validator.validateRegister(email2, password2, nullString);
        Assert.assertEquals(4, result3, 0);

        int result4 = validator.validateRegister(email2, password2, repeatPassword2);
        Assert.assertEquals(1, result4, 0);

        Exception hasToBeThrown = null;
        try {
            validator.validateRegister(nullString, password2, repeatPassword2);
        } catch (Exception e) {
            hasToBeThrown = e;
        }
        assertNotNull(hasToBeThrown);
    }


    @Test
    public void validateChangePassword() {
        int result1 = validator.validateChangePassword(password2, nullString);
        Assert.assertEquals(3, result1, 0);

        int result2 = validator.validateChangePassword(password, nullString);
        Assert.assertEquals(2, result2, 0);

        int result3 = validator.validateChangePassword(password2, repeatPassword2);
        Assert.assertEquals(1, result3, 0);

        Exception hasToBeThrown = null;
        try {
            validator.validateChangePassword(nullString, password);
        } catch (Exception e) {
            hasToBeThrown = e;
        }
        assertNotNull(hasToBeThrown);
    }

    @Test
    public void validateLogin()
    {
        int result1 = validator.validateLogin(email, null);
        Assert.assertEquals(2, result1, 0);

        int result2 = validator.validateLogin(email2, password);
        Assert.assertEquals(3, result2, 0);

        int result3 = validator.validateLogin(email2, password2);
        Assert.assertEquals(1, result3, 0);

        Exception hasToBeThrown = null;
        try {
            validator.validateLogin(nullString, password);
        } catch (Exception e) {
            hasToBeThrown = e;
        }
        assertNotNull(hasToBeThrown);
    }

    @Test
    public void validateEditRoomFields()
    {
        int result = validator.validateEditRoomFields(light, co2, temperature, empty);
        Assert.assertEquals(4, result, 0);

        int result2 = validator.validateEditRoomFields(light, empty, temperature, humidity);
        Assert.assertEquals(2, result2, 0);

        int result3 = validator.validateEditRoomFields(light, co2, temperature, humidity);
        Assert.assertEquals(5, result3, 0);

        Exception hasToBeThrown = null;
        try {
            validator.validateEditRoomFields(nullString, empty, temperature, humidity);
        } catch (Exception e) {
            hasToBeThrown = e;
        }
        assertNotNull(hasToBeThrown);
    }

    @Test
    public void validateEditArtworkFields()
    {
        int result = validator.validateEditArtworkFields(allowedName, notAllowed);
        Assert.assertEquals(4, result,0);

        int result2 = validator.validateEditArtworkFields(empty, empty);
        Assert.assertEquals(1, result2, 0);

        int result3 = validator.validateEditArtworkFields(notAllowed, empty);
        Assert.assertEquals(2, result3, 0);

        Exception hasToBeThrown = null;
        try {
            validator.validateEditArtworkFields(nullString, allowedName);
        } catch (Exception e) {
            hasToBeThrown = e;
        }
        assertNotNull(hasToBeThrown);
    }

}