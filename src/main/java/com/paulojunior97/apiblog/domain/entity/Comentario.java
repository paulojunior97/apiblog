package com.paulojunior97.apiblog.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private Date data;
    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario autor;
    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    public Comentario(String comentario) {
        this.comentario = comentario;
    }
}
