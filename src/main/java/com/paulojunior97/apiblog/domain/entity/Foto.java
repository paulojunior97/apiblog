package com.paulojunior97.apiblog.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] foto;
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    public Foto(byte[] foto, Album album) {
        this.foto = foto;
        this.album = album;
    }
}
