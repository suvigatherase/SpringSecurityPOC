package com.hook.form.backend.org.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
// @Order(1)
public class SimpleCORSFilter extends OncePerRequestFilter  {
  
   @Override
   protected void doFilterInternal(HttpServletRequest req   , HttpServletResponse res, FilterChain chain)
           throws ServletException, IOException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Credentials", "true");
    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH");
    response.addHeader("Access-Control-Max-Age", "3600");
    response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    //  response.add(200);, remember-me, Access-Control-Allow-Methods, Access-Control-Allow-Origin,, Accept, X-Requested-With
    // chain.doFilter(request, response);

    if ("OPTIONS".equals(request.getMethod())) {
        response.setStatus(HttpServletResponse.SC_OK);
    } else { 
        chain.doFilter(request, response);
    }
}



@Override
public void destroy() {
}
}
