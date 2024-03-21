import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.example.demo.security.LoginFilter;

public class LoginFilterTest {

    @Test
    public void testAttemptAuthentication_ValidCredentials() throws AuthenticationException, ServletException, IOException {
       
        AuthenticationManager authManager = mock(AuthenticationManager.class);
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);
        String requestBody = "{\"username\":\"naima@gmail.com\",\"password\":\"Naima12345\"}";
        ((MockHttpServletRequest) request).setContent(requestBody.getBytes());
        Authentication authentication = new UsernamePasswordAuthenticationToken("naima@gmail.com", 
        "Naima12345", Collections.emptyList());
        when(authManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        LoginFilter loginFilter = new LoginFilter("/login", authManager);
        loginFilter.attemptAuthentication(request, response);
                verify(authManager).authenticate(any(Authentication.class));
    }

    @Test
    public void testSuccessfulAuthentication_GeneratesToken() throws IOException, ServletException {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("naima@gmail.com");
        LoginFilter loginFilter = new LoginFilter("/login", null);
        loginFilter.successfulAuthentication(request, response, filterChain, authentication);
        String responseContent = ((MockHttpServletResponse) response).getContentAsString(); 
        assertEquals("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYWltYUBnbWFpbC5jb20iLCJleHAiOjE3MTEwNDg4ODd9.gK_3lh4AgB36l3RbNHNa3rR4kBEpsHdHKcK8NqGsqwQbOPY4bF7teoePlpqoI6UZPSiwIibliyPTjHFOFd0QAA", responseContent.trim()); // Replace "generatedToken" with the expected token value
    }
}
