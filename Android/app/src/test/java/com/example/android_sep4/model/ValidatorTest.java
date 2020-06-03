package com.example.android_sep4.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;
@RunWith(JUnit4.class)
public class ValidatorTest {

    private Validator validator;
    private String email;
    private String email2;
    private String password;
    private String password2;
    private String repeatPassword;
    private String repeatPassword2;

    @Before
    public void setUp() throws Exception {
        validator = new Validator();
        email = "wrongemail";
        email2 = "correct@email.com";
        password = "2']-/";
        password2 = "a12t3g5.3'";
        repeatPassword = "000";
        repeatPassword2 = "a12t3g5.3'";
    }

    @Test
    public void validateRegister() {
        int result1 = validator.validateRegister(email, password2, repeatPassword);
        Assert.assertEquals(2, result1, 0);

        int result2 = validator.validateRegister(email2, password, repeatPassword);
        Assert.assertEquals(3, result2, 0);

        int result3 = validator.validateRegister(email2, password2, repeatPassword);
        Assert.assertEquals(4, result3, 0);

        int result4 = validator.validateRegister(email2, password2, repeatPassword2);
        Assert.assertEquals(1, result4, 0);
    }


    @Test
    public void validateChangePassword() {
        int result1 = validator.validateChangePassword(password2, null);
        Assert.assertEquals(3, result1, 0);

        int result2 = validator.validateChangePassword(password, null);
        Assert.assertEquals(2, result2, 0);

        int result3 = validator.validateChangePassword(password2, repeatPassword2);
        Assert.assertEquals(1, result3, 0);
    }

    @After
    public void tearDown() throws Exception {
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
    }

}