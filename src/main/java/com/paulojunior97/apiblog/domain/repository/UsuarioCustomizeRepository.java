package com.paulojunior97.apiblog.domain.repository;

import com.paulojunior97.apiblog.domain.entity.Usuario;

import java.util.List;

public interface UsuarioCustomizeRepository {

    List<Usuario> findByNomeAndEmail(String nome, String email);
}
