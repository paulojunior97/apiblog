package com.paulojunior97.apiblog.domain.service.impl;

import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.exceptions.*;
import com.paulojunior97.apiblog.domain.repository.UsuarioCustomizeRepository;
import com.paulojunior97.apiblog.domain.repository.UsuarioRepository;
import com.paulojunior97.apiblog.domain.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioCustomizeRepository customizeRepository;

    @Override
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmailEqualsAndAtivoIsTrue(email);
    }

    @Override
    public List<Usuario> buscarPorNomeEmail(String nome, String email) {
        return customizeRepository.findByNomeAndEmail(nome, email);
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        this.validar(usuario);
        usuario.setAtivo(true);
        return repository.save(usuario);
    }

    @Override
    public void deletar(Long id) {
        Usuario usuario = repository.findById(id).orElse(null);
        if(usuario == null) {
            throw new UsuarioNaoEncontradoException(id);
        }
        usuario.setAtivo(false);
        repository.save(usuario);
    }

    @Override
    public void validar(Usuario usuario) {
        validarNome(usuario.getNome());
        validarEmail(usuario.getEmail());
        validarSenha(usuario.getSenha());
    }

    private void validarEmail(String email){
        if(StringUtils.isBlank(email))
            throw new EmailUsuarioObrigatorioException();
        if(!email.contains("@"))
            throw new EmailUsuarioInvalidoException(email);
    }

    private void validarNome(String nome){
        if(StringUtils.isBlank(nome))
            throw new NomeUsuarioObrigatorioException();
    }

    private void validarSenha(String senha){
        if(StringUtils.isBlank(senha))
            throw new SenhaUsuarioObrigatorioException();
    }

    public void setRepository(UsuarioRepository repository) {
        this.repository = repository;
    }

}
