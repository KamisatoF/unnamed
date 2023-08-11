package br.com.unnamed.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtValidateFilter extends BasicAuthenticationFilter {

    public static final String ATRIBUTE_HEADER = "Authorization";
    public static final String ATRIBUTE_PREFIX = "Bearer ";

    public JwtValidateFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String atributo = request.getHeader(ATRIBUTE_HEADER);

        if (atributo == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!atributo.startsWith(ATRIBUTE_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = atributo.replace(ATRIBUTE_PREFIX, "");
        UsernamePasswordAuthenticationToken authenticationToken = getUsernamePasswordAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {
        String usuario = JWT.require(Algorithm.HMAC512(JwtAutenticarFilter.KEY))
                .build()
                .verify(token)
                .getSubject();

        if (usuario == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());

    }
}
