package com.paulojunior97.apiblog.domain.service.impl;

import com.paulojunior97.apiblog.domain.entity.PerfilEnum;
import com.paulojunior97.apiblog.domain.entity.Post;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.exceptions.PostagemNaoEncontradaException;
import com.paulojunior97.apiblog.domain.exceptions.SemPermissaoExcluirPostagemException;
import com.paulojunior97.apiblog.domain.repository.PostRepository;
import com.paulojunior97.apiblog.domain.service.ComentarioService;
import com.paulojunior97.apiblog.domain.service.UsuarioService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Optional;

public class PostServiceImplTeste {

    private PostServiceImpl service;
    private UsuarioService usuarioService;
    private PostRepository postRepository;
    private ComentarioService comentarioService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void Before() {
        service = new PostServiceImpl();
        postRepository = Mockito.mock(PostRepository.class);
        service.setRepository(postRepository);

        usuarioService = Mockito.mock(UsuarioService.class);
        service.setUsuarioService(usuarioService);

        comentarioService = Mockito.mock(ComentarioService.class);
        service.setComentarioService(comentarioService);
    }

    @Test
    public void testeCriarPost(){
        //cenario
        String emailCriador = "teste@teste.com";
        Mockito.when(usuarioService.buscarPorEmail(emailCriador)).thenReturn(getUsuario());

        //acao
        service.cadastrar(emailCriador, getPost());
    }

    @Test
    public void testeBuscarPost(){
        //cenario
        Long idPost = 1l;
        Mockito.when(postRepository.findById(idPost)).thenReturn(Optional.of(getPost()));
        //acao

        service.buscarPorId(idPost);
    }

    @Test
    public void testeBuscarPostInexistente(){
        //cenario
        Long idPost = 1l;
        Mockito.when(postRepository.findById(idPost)).thenReturn(Optional.empty());

        expectedException.expect(PostagemNaoEncontradaException.class);
        expectedException.expectMessage(Matchers.equalTo("Não foi encontrado a postagem com o id '1'."));
        //acao

        service.buscarPorId(idPost);
    }

    @Test
    public void testeExcluirPost(){
        //cenario
        Long idPost = 1l;
        String emailCriador = "teste@teste.com";
        Mockito.when(postRepository.findById(idPost)).thenReturn(Optional.of(getPost()));

        //acao
        service.excluir(emailCriador, idPost);
    }

    @Test
    public void testeExcluirPostSemPermissao(){
        //cenario
        Long idPost = 1l;
        String emailCriador = "teste1@teste.com";
        Mockito.when(postRepository.findById(idPost)).thenReturn(Optional.of(getPost()));

        expectedException.expect(SemPermissaoExcluirPostagemException.class);
        expectedException.expectMessage(Matchers.equalTo("Apenas o usuário que criou a postagem tem permissão para excluí-la."));

        //acao
        service.excluir(emailCriador, idPost);
    }

    @Test
    public void testeBuscarTodosPost(){
        service.buscarTodos();
    }

    private Post getPost(){
        Post post = new Post("Teste", "Conteudo teste");
        post.setCriador(getUsuario());
        return post;
    }

    private Usuario getUsuario(){
        return new Usuario("Teste", "teste@teste.com", "1234", PerfilEnum.CLIENTE);
    }
}
