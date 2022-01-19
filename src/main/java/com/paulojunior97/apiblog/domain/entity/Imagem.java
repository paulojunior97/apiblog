package com.paulojunior97.apiblog.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] imagem;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Imagem(byte[] imagem, Post post) {
        this.imagem = imagem;
        this.post = post;
    }
}
