package com.paulojunior97.apiblog.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Link(String url, String descricao, Post post) {
        this.url = url;
        this.descricao = descricao;
        this.post = post;
    }
}
