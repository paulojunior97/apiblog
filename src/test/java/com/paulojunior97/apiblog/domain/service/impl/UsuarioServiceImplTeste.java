package com.paulojunior97.apiblog.domain.service.impl;

import com.paulojunior97.apiblog.domain.entity.PerfilEnum;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.exceptions.*;
import com.paulojunior97.apiblog.domain.repository.UsuarioRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Optional;

public class UsuarioServiceImplTeste {

    private UsuarioServiceImpl service;
    private UsuarioRepository repository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before(){
        service = new UsuarioServiceImpl();
        repository = Mockito.mock(UsuarioRepository.class);
        service.setRepository(repository);
    }

    @Test
    public void testeBuscarPorEmail(){
        //cenario
        String email = "teste@teste.com";
        Mockito.when(repository.findByEmailEqualsAndAtivoIsTrue(email)).thenReturn(getUsuario());

        //acao
        service.buscarPorEmail(email);
    }

    @Test
    public void testeCadastrar(){
        //acao
        service.cadastrar(getUsuario());
    }

    @Test
    public void testDeletarUsuario(){
        //cenario
        Long idUsuario = 1l;
        Mockito.when(repository.findById(idUsuario)).thenReturn(Optional.of(getUsuario()));

        //acao
        service.deletar(idUsuario);
    }

    @Test
    public void testDeletarUsuarioInexistente(){
        //cenario
        Long idUsuario = 1l;
        Mockito.when(repository.findById(idUsuario)).thenReturn(Optional.empty());

        expectedException.expect(UsuarioNaoEncontradoException.class);
        expectedException.expectMessage(Matchers.equalTo("Não foi encontrado um usuário com o id '1'."));

        //acao
        service.deletar(idUsuario);
    }

    @Test
    public void testeUsuarioNomeInvalido(){
        //cenario
        Usuario usuario = getUsuario();
        usuario.setNome("");

        expectedException.expect(NomeUsuarioObrigatorioException.class);
        expectedException.expectMessage(Matchers.equalTo("Nome do usuário é um parâmetro obrigatório."));

        //acao
        service.validar(usuario);
    }

    @Test
    public void testeUsuarioEmailNaoInformado(){
        //cenario
        Usuario usuario = getUsuario();
        usuario.setEmail("");

        expectedException.expect(EmailUsuarioObrigatorioException.class);
        expectedException.expectMessage(Matchers.equalTo("Email do usuário é um parâmetro obrigatório."));

        //acao
        service.validar(usuario);
    }

    @Test
    public void testeUsuarioEmailInvalido(){
        //cenario
        Usuario usuario = getUsuario();
        usuario.setEmail("teste.teste.com");

        expectedException.expect(EmailUsuarioInvalidoException.class);
        expectedException.expectMessage(Matchers.equalTo("Email 'teste.teste.com' informado é inválido."));

        //acao
        service.validar(usuario);
    }

    @Test
    public void testeUsuarioSenhaEmBranco(){
        //cenario
        Usuario usuario = getUsuario();
        usuario.setSenha("");

        expectedException.expect(SenhaUsuarioObrigatorioException.class);
        expectedException.expectMessage(Matchers.equalTo("Senha do usuário é um parâmetro obrigatório."));

        //acao
        service.validar(usuario);
    }

    private Usuario getUsuario(){
        return new Usuario("Teste", "teste@teste.com", "1234", PerfilEnum.ADMIN);
    }
}
