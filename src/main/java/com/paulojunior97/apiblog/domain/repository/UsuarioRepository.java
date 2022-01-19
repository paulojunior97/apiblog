package com.paulojunior97.apiblog.domain.repository;

import com.paulojunior97.apiblog.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailEqualsAndAtivoIsTrue(String email);

}
