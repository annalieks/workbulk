package com.univ.workbulk.auth;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class TokenServiceTest {

    private final static String SECRET_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9klkkhuh";
    TokenService tokenService;

    @DataProvider(name = "usersToTokens")
    public static Object[][] usersToTokens() {
        return new Object[][]{
                {new AuthUser(UUID.fromString("00000000-0000-0000-0000-000000000000"), "name", "pass"),
                        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMDAwMDAwMC0wMDAwLTAwMDAtMDAwMC0wMDAwMDAwMDAwMDAiLCJpYXQiOjE2MzU2MjQ1NTQsImV4cCI6MTYzNjQ4ODU1NH0.cepsKTJFwfaVvhJB3GWh-2B3Z_FO0BRMgbVVdNq1EHQ"},
                {new AuthUser(UUID.fromString("00000000-0000-0000-0000-000000000000"), "name1", "pass1"),
                        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMDAwMDAwMC0wMDAwLTAwMDAtMDAwMC0wMDAwMDAwMDAwMDAiLCJpYXQiOjE2MzU2MjQ2MTUsImV4cCI6MTYzNjQ4ODYxNX0.6OWt-C0JZAvIaEdHOVxCFIFSQ3kKeLhGZBY5ih83ZDI"}};
    }

    @DataProvider(name = "tokens")
    public static Object[][] tokens() {
        var date1 = ZonedDateTime.of(2021, 11, 9, 22, 9, 14, 0, ZoneId.of("EET"));
        var date2 = ZonedDateTime.of(2021, 11, 9, 22, 10, 15, 0, ZoneId.of("EET"));
        return new Object[][]{
                {"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMDAwMDAwMC0wMDAwLTAwMDAtMDAwMC0wMDAwMDAwMDAwMDAiLCJpYXQiOjE2MzU2MjQ1NTQsImV4cCI6MTYzNjQ4ODU1NH0.cepsKTJFwfaVvhJB3GWh-2B3Z_FO0BRMgbVVdNq1EHQ", Date.from(date1.toInstant())},
                {"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMDAwMDAwMC0wMDAwLTAwMDAtMDAwMC0wMDAwMDAwMDAwMDAiLCJpYXQiOjE2MzU2MjQ2MTUsImV4cCI6MTYzNjQ4ODYxNX0.6OWt-C0JZAvIaEdHOVxCFIFSQ3kKeLhGZBY5ih83ZDI", Date.from(date2.toInstant())}
        };
    }

    @BeforeClass
    public void setUp() {
        tokenService = new TokenService(SECRET_KEY);
    }

    @Test(dataProvider = "usersToTokens", groups = "user")
    void whenExtractUserClaim_thenCorrectUser(AuthUser user, String token) {
        Assert.assertEquals(tokenService.extractUserid(token), user.getId().toString());
    }

    @Test(dataProvider = "tokens", groups = "token")
    void whenExtractExpiration_thenCorrectDate(String token, Date expired) {
        Assert.assertEquals(tokenService.extractExpiration(token), expired);
    }

}
