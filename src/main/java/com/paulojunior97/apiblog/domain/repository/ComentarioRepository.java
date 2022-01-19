package com.paulojunior97.apiblog.domain.repository;

import com.paulojunior97.apiblog.domain.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findAllByPost_IdEquals(Long idPostagem);
    void deleteAllByPost_IdEquals(Long idPost);

}
