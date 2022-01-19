package com.paulojunior97.apiblog.domain.service.impl;

import com.paulojunior97.apiblog.domain.entity.Comentario;
import com.paulojunior97.apiblog.domain.entity.PerfilEnum;
import com.paulojunior97.apiblog.domain.entity.Post;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.exceptions.ComentarioNaoEncontradoException;
import com.paulojunior97.apiblog.domain.exceptions.SemPermissaoExcluirComentarioException;
import com.paulojunior97.apiblog.domain.repository.ComentarioRepository;
import com.paulojunior97.apiblog.domain.service.PostService;
import com.paulojunior97.apiblog.domain.service.UsuarioService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.*;

public class ComentarioServiceImplTeste {

    private ComentarioServiceImpl service;
    private PostService postService;
    private UsuarioService usuarioService;
    private ComentarioRepository repository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before(){
        service = new ComentarioServiceImpl();
        postService = Mockito.mock(PostService.class);
        service.setPostService(postService);

        usuarioService = Mockito.mock(UsuarioService.class);
        service.setUsuarioService(usuarioService);

        repository = Mockito.mock(ComentarioRepository.class);
        service.setRepository(repository);
    }

    @Test
    public void testeBuscarComentario(){
        //cenario
        Long idComentario = 1l;
        Mockito.when(repository.findById(idComentario)).thenReturn(Optional.of(getComentario()));
        //acao
        service.buscarPorId(idComentario);
        //verificacao
    }

    @Test
    public void testeBuscarComentarioInexistente(){
        //cenario
        Long idComentario = 1l;
        Mockito.when(repository.findById(idComentario)).thenReturn(Optional.empty());

        expectedException.expect(ComentarioNaoEncontradoException.class);
        expectedException.expectMessage(Matchers.equalTo("Não foi encontrado o comentário com o id '1'."));

        //acao
        service.buscarPorId(idComentario);
    }

    private Comentario getComentario(){
        Comentario comentario = new Comentario("Comentario teste");
        comentario.setId(1l);
        Post post = new Post("Teste1", "Conteudo teste1");
        comentario.setPost(post);
        comentario.setAutor(getUsuario());
        comentario.setData(getDataAleatoria());
        return comentario;
    }

    @Test
    public void testeExcluirComentario() {
        //cenario
        Long idComentario = 1l;
        String emailUsuario = "teste@teste.com";
        Mockito.when(repository.findById(idComentario)).thenReturn(Optional.of(getComentario()));

        //acao
        service.excluir(idComentario, emailUsuario);

    }
    @Test
    public void testeExcluirComentarioSemPermissao(){
        //cenario
        Long idComentario = 1l;
        String emailUsuario = "teste1@teste.com";
        Mockito.when(repository.findById(idComentario)).thenReturn(Optional.of(getComentario()));

        expectedException.expect(SemPermissaoExcluirComentarioException.class);
        expectedException.expectMessage(Matchers.equalTo("Apenas o usuário que fez o comentário tem permissão para excluí-lo."));
        //acao
        service.excluir(idComentario, emailUsuario);
    }

    @Test
    public void testeExcluirTodosComentariosDoPost() {
        //cenario
        Long idPost = 1l;

        //acao
        service.excluirComentariosDoPost(1l);
    }

    @Test
    public void testeCadastrarComentario() {
        //cenario
        String emailAutor = "teste@teste.com";
        Mockito.when(usuarioService.buscarPorEmail(emailAutor)).thenReturn(getUsuario());
        Mockito.when(repository.save(getComentario())).thenReturn(getComentario());

        //acao
        service.cadastrar(getComentario(), emailAutor);
    }

    @Test
    public void testeBuscarTodosComentarios() {
        //cenario
        Long idPost = 1l;
        Mockito.when(repository.findAllByPost_IdEquals(idPost)).thenReturn(Arrays.asList(getComentario()));

        //acao
        service.buscarTodosComentariosDeUmPost(idPost);
    }

    private Usuario getUsuario(){
        return new Usuario("Teste", "teste@teste.com", "1234", PerfilEnum.CLIENTE);
    }

    private Date getDataAleatoria(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -(new Random().nextInt(365)));
        return calendar.getTime();
    }
}
