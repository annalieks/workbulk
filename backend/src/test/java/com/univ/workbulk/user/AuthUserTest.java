package com.univ.workbulk.user;

import com.univ.workbulk.auth.AuthUser;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

public class AuthUserTest {

    private AuthUser userSpy;

    @BeforeTest
    public void setUp() {
        userSpy = Mockito.spy(new AuthUser(UUID.randomUUID(), "name", "pass"));
    }

    @Test(groups = "user")
    void whenInit_thenPasswordSet() {
        UUID id = UUID.randomUUID();
        Mockito.doReturn(id).when(userSpy).getId();
        Assertions.assertEquals(id, userSpy.getId());
        Assertions.assertEquals("name", userSpy.getUsername());
        Assertions.assertEquals("pass", userSpy.getPassword());
    }

    @Test(groups = "user")
    void whenInit_thenUsernameSet() {
        String username = "name";
        Mockito.doReturn(username).when(userSpy).getUsername();
        Assertions.assertEquals(username, userSpy.getUsername());
        Assertions.assertEquals("pass", userSpy.getPassword());
    }

}
