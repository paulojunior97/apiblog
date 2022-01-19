package com.paulojunior97.apiblog.api.controlador;

import com.paulojunior97.apiblog.api.dto.JwtRequestDto;
import com.paulojunior97.apiblog.api.dto.JwtResponseDto;
import com.paulojunior97.apiblog.config.security.JwtTokenUtil;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.service.UsuarioService;
import com.paulojunior97.apiblog.domain.service.impl.JwtUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Autenticação")
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation("Obter token.")
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());
        final Usuario usuario = usuarioService.buscarPorEmail(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(usuario);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
