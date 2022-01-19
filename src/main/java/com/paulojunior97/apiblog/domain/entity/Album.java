package com.paulojunior97.apiblog.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataPublicacao;
    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Foto> fotos;

    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario criador;

    public Album() {
    }

    public Album(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
