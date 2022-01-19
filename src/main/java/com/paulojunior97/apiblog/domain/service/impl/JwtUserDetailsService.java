package com.paulojunior97.apiblog.domain.service.impl;

import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = userService.buscarPorEmail(email);
        if (usuario.getEmail().equals(email)) {
            return new User(email, usuario.getSenha(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Não foi encontrado usuário com o email: " + email);
        }
    }

    public void setUserService(UsuarioService userService) {
        this.userService = userService;
    }
}
