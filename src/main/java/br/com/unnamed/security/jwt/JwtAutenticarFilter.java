package br.com.unnamed.security.jwt;

import br.com.unnamed.domain.Usuario;
import br.com.unnamed.security.data.DetalhesUsuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    public static final long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(12);

    public static final String KEY = "4d251558-022b-4905-af62-a08a8f752b65";

    private final AuthenticationManager authenticationManager;

    public JwtAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha(), new ArrayList<>()));
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario!");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        DetalhesUsuario detalhesUsuario = (DetalhesUsuario) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(detalhesUsuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(KEY));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
