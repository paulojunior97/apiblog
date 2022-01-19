package com.paulojunior97.apiblog.domain.repository;

import com.paulojunior97.apiblog.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByCriador_AtivoIsTrue();
}
