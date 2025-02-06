package br.com.login.backend.filter;

import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import br.com.login.backend.service.JWTService;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenFilter extends GenericFilterBean {
  public static final int TOKEN_INDEX = 7;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    String header = httpServletRequest.getHeader("Authorization");

    if (header == null || !header.startsWith("Bearer ")) {
      httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou mal formatado");
      return;
    }

    String token = header.substring(TOKEN_INDEX);

    try {
      JwtParser parser = Jwts.parserBuilder().setSigningKey(JWTService.TOKEN_KEY).build();
      parser.parseClaimsJws(token).getBody();
    } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | PrematureJwtException
        | IllegalArgumentException | ExpiredJwtException e) {
      httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
      return;
    }

    chain.doFilter(request, response);
  }
}
