package com.example.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExternalApiIntegrationTest {

    private static final String AUTH_HEADER = "Authorization";
    private final String BASE_URL = "/external-api";

    @Autowired
    private MockMvc mockMvc;

    /* TESTING EXTERNAL API CONTROLLER */
    @Test
    void getHome_returns401_UnauthorizedResponse_ifUnauthenticated() throws Exception {
        this.mockMvc
                .perform(get(BASE_URL + "/resource"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getHome_returns200_OkResponse_ifAuthorizedAs_externalApiUser() throws Exception {
        this.mockMvc
                .perform(get(BASE_URL + "/resource").header(AUTH_HEADER, getExternalUserAuthHeader()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Dear johndoe@gmail.com, you have been authorized and got 'EXTERNAL RESOURCE'"));
    }

    @Test
    void getHome_returns403_ForbiddenResponse_ifAuthorizedAs_internalApiUser() throws Exception {
        this.mockMvc
                .perform(get(BASE_URL + "/resource").header(AUTH_HEADER, getInternalUserAuthHeader()))
                .andDo(print())
                .andExpect(status().isForbidden());
    }


    // Utility methods
    private String getInternalUserAuthHeader() {
        String credentials = "fairyprincess@gmail.com:fairyprincess";
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        return "Basic " + encodedCredentials;
    }

    private String getExternalUserAuthHeader() {
        String credentials = "johndoe@gmail.com:johndoe";
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        return "Basic " + encodedCredentials;
    }
}
