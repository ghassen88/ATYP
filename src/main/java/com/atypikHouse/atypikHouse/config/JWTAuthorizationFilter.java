package com.atypikHouse.atypikHouse.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
  //  @Override
   protected void doFilterInternal
            (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
      System.out.println(jwt);
        System.out.println("ghassen");
        if (jwt ==null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, ""))
                .getBody();
       String username = claims.getSubject();
      ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.get("authority")));
        });
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

   }
