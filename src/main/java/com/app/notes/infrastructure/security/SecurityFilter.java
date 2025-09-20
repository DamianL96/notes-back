package com.app.notes.infrastructure.security;

import com.app.notes.User.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("security filter llamado");
        var tokenDeRequest = recuperarTokenDeRequest(request);

        if(tokenDeRequest != null){
            var subject = tokenService.decodificarToken(tokenDeRequest);
            var usuario = usuarioRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Usuario logueado");
        }

        filterChain.doFilter(request,response);//continua la cadena de filtros

    }



    private String recuperarTokenDeRequest(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if(authHeader !=null){
            return authHeader.replace("Bearer ",""); //me desago del prefijo por defecto
        }
        return null;
    }

}
