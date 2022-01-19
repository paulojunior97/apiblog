package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.UsuarioDto;
import com.paulojunior97.apiblog.domain.entity.PerfilEnum;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioAssemblerImplTeste {
    private Assembler assembler;

    @Before
    public void before(){
        assembler = new UsuarioAssemblerImpl();
    }

    @Test
    public void testeUsuarioDtoParaEntity(){
        //cenario
        UsuarioDto usuarioDto = new UsuarioDto("Teste", "teste@teste.com", "1234", PerfilEnum.CLIENTE);

        //acao
        Usuario usuario = (Usuario) assembler.dtoToEntity(usuarioDto);

        //verificacao
        Assert.assertEquals(usuarioDto.nome, usuario.getNome());
        Assert.assertEquals(usuarioDto.email, usuario.getEmail());
        Assert.assertEquals(usuarioDto.senha, usuario.getSenha());
    }

    @Test
    public void testeUsuarioEntityParaDto(){
        //cenario
        Usuario usuario = new Usuario("Teste", "teste@teste.com", "1234", PerfilEnum.CLIENTE);

        //acao
        UsuarioDto usuarioDto = (UsuarioDto) assembler.entityToDto(usuario);

        //verificacao
        Assert.assertEquals(usuario.getNome(), usuarioDto.nome);
        Assert.assertEquals(usuario.getEmail(), usuarioDto.email);
        Assert.assertEquals(usuario.getSenha(), usuarioDto.senha);
    }
}
