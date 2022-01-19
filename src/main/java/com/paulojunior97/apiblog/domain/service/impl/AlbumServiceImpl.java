package com.paulojunior97.apiblog.domain.service.impl;

import com.paulojunior97.apiblog.domain.entity.Album;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.exceptions.AlbumNaoEncontradoException;
import com.paulojunior97.apiblog.domain.exceptions.SemPermissaoExcluirAlbumException;
import com.paulojunior97.apiblog.domain.repository.AlbumRepository;
import com.paulojunior97.apiblog.domain.service.AlbumService;
import com.paulojunior97.apiblog.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Album cadastrar(String emailCriador, Album album) {
        Usuario usuario = usuarioService.buscarPorEmail(emailCriador);
        album.setCriador(usuario);
        return repository.save(album);
    }

    @Override
    public Album buscarPeloId(Long id) {
        Album album = repository.findById(id).orElse(null);
        if(album == null)
            throw new AlbumNaoEncontradoException(id);
        return album;
    }

    @Override
    public void excluir(Long id, String emailUsuario) {
        Album album = buscarPeloId(id);
        if(!isUsuarioCriador(album, emailUsuario))
            throw new SemPermissaoExcluirAlbumException();
        repository.delete(album);
    }

    @Override
    public List<Album> listar() {
        return repository.findAlbumsByCriador_AtivoIsTrue();
    }

    private boolean isUsuarioCriador(Album album, String emailUsuario){
        return album.getCriador().getEmail().equalsIgnoreCase(emailUsuario);
    }

    public void setRepository(AlbumRepository repository) {
        this.repository = repository;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
