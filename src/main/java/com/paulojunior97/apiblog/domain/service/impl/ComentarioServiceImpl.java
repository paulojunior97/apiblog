package com.paulojunior97.apiblog.domain.service.impl;

import com.paulojunior97.apiblog.domain.entity.Comentario;
import com.paulojunior97.apiblog.domain.entity.Post;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.exceptions.ComentarioNaoEncontradoException;
import com.paulojunior97.apiblog.domain.exceptions.SemPermissaoExcluirComentarioException;
import com.paulojunior97.apiblog.domain.repository.ComentarioRepository;
import com.paulojunior97.apiblog.domain.service.ComentarioService;
import com.paulojunior97.apiblog.domain.service.PostService;
import com.paulojunior97.apiblog.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private PostService postService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ComentarioRepository repository;

    @Override
    public Comentario buscarPorId(Long id) {
        Comentario comentario = repository.findById(id).orElse(null);
        if(comentario == null)
            throw new ComentarioNaoEncontradoException(id);
        return comentario;
    }

    @Override
    public Comentario cadastrar(Comentario comentario, String emailAutor) {
        Post post = postService.buscarPorId(comentario.getPost().getId());
        comentario.setPost(post);

        Usuario usuario = usuarioService.buscarPorEmail(emailAutor);
        comentario.setAutor(usuario);

        comentario.setData(new Date());

        return repository.save(comentario);
    }

    @Override
    public List<Comentario> buscarTodosComentariosDeUmPost(Long idPost) {
        return repository.findAllByPost_IdEquals(idPost);
    }

    @Override
    public void excluir(Long id, String emailUsuario) {
        Comentario comentario = buscarPorId(id);
        if (!isUsuarioCriador(comentario, emailUsuario))
            throw new SemPermissaoExcluirComentarioException();
        repository.delete(comentario);
    }

    private boolean isUsuarioCriador(Comentario comentario, String emailUsuario){
        return comentario.getAutor().getEmail().equalsIgnoreCase(emailUsuario);
    }

    @Override @Transactional
    public void excluirComentariosDoPost(Long idPost) {
        repository.deleteAllByPost_IdEquals(idPost);
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void setRepository(ComentarioRepository repository) {
        this.repository = repository;
    }
}
