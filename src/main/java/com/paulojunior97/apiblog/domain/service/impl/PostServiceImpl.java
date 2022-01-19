package com.paulojunior97.apiblog.domain.service.impl;

import com.paulojunior97.apiblog.domain.entity.Post;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.exceptions.PostagemNaoEncontradaException;
import com.paulojunior97.apiblog.domain.exceptions.SemPermissaoExcluirPostagemException;
import com.paulojunior97.apiblog.domain.repository.PostRepository;
import com.paulojunior97.apiblog.domain.service.ComentarioService;
import com.paulojunior97.apiblog.domain.service.PostService;
import com.paulojunior97.apiblog.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostRepository repository;

    @Autowired
    private ComentarioService comentarioService;

    @Override
    public Post cadastrar(String emailCriador, Post post) {
        Usuario usuario = usuarioService.buscarPorEmail(emailCriador);
        post.setCriador(usuario);
        return repository.save(post);
    }

    @Override
    public Post buscarPorId(Long id) {
        Post post = repository.findById(id).orElse(null);

        if(post == null)
            throw new PostagemNaoEncontradaException(id);

        return post;
    }

    @Override
    public void excluir(String emailUsuario, Long idPostagem) {
        Post post = buscarPorId(idPostagem);
        if(!isUsuarioCriador(post, emailUsuario))
            throw new SemPermissaoExcluirPostagemException();
        comentarioService.excluirComentariosDoPost(post.getId());
        repository.delete(post);
    }

    private boolean isUsuarioCriador(Post post, String emailUsuario){
        return post.getCriador().getEmail().equalsIgnoreCase(emailUsuario);
    }

    @Override
    public List<Post> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public List<Post> listar() {
        return repository.findPostsByCriador_AtivoIsTrue();
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void setRepository(PostRepository repository) {
        this.repository = repository;
    }

    public void setComentarioService(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

}
