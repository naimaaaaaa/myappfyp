package com.example.demo.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

  public LoginFilter(String url, AuthenticationManager authManager) {
    super(new AntPathRequestMatcher(url));
    this.setAuthenticationManager(authManager);
  }

  @Override
  public Authentication attemptAuthentication(
  HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException, IOException, ServletException {
  AccountCredentials creds = new ObjectMapper()
        .readValue(req.getInputStream(), AccountCredentials.class);
   
  return this.getAuthenticationManager().authenticate(
        new UsernamePasswordAuthenticationToken(
            creds.getUsername(),
            creds.getPassword(),
            Collections.emptyList()
        )
    );
  }

  @Override
  public void successfulAuthentication(
      HttpServletRequest req,
      HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {
      String token = JWTService.generateToken(auth.getName());
      res.getWriter().write(token);
      res.getWriter().flush();
  }
}
// package com.example.demo.security;

// import java.io.IOException;
// import java.util.Collections;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// import com.fasterxml.jackson.databind.ObjectMapper;

// public class LoginFilter extends AbstractAuthenticationProcessingFilter {

//   public LoginFilter(String url, AuthenticationManager authManager) {
//     super(new AntPathRequestMatcher(url));
//     this.setAuthenticationManager(authManager);
//   }

//   @Override
//   public Authentication attemptAuthentication(
//   HttpServletRequest req, HttpServletResponse res)
//       throws AuthenticationException, IOException, ServletException {
//   AccountCredentials creds = new ObjectMapper()
//         .readValue(req.getInputStream(), AccountCredentials.class);
   
//   return this.getAuthenticationManager().authenticate(
//         new UsernamePasswordAuthenticationToken(
//             // creds.getUsername(),
//             creds.getEmail(),
//             creds.getPassword(),
//             Collections.emptyList()
//         )
//     );
//   }

//   @Override
//   protected void successfulAuthentication(
//       HttpServletRequest req,
//       HttpServletResponse res, FilterChain chain,
//       Authentication auth) throws IOException, ServletException {
//     JWTService.addToken(res, auth.getName());
//   }
// }