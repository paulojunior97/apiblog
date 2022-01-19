package com.paulojunior97.apiblog.infrastructure.repository;

import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.repository.UsuarioCustomizeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioCustomizeRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Usuario> findByNomeAndEmail(String nome, String email) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteria.from(Usuario.class);

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotBlank(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (StringUtils.isNotBlank(email)) {
            predicates.add(builder.like(root.get("email"), "%" + email + "%"));
        }
        predicates.add(builder.equal(root.get("ativo"), true));

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Usuario> query = manager.createQuery(criteria);
        return query.getResultList();
    }
}
