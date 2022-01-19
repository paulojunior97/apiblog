package com.paulojunior97.apiblog.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataPublicacao;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Link> links;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Imagem> imagens;

    @JoinColumn(name = "usuario_id")
    @ManyToOne
    private Usuario criador;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    public Post(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }
}
