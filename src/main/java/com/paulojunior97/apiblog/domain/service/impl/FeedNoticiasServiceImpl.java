package com.paulojunior97.apiblog.domain.service.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.FeedNoticiaDto;
import com.paulojunior97.apiblog.domain.entity.Album;
import com.paulojunior97.apiblog.domain.entity.Post;
import com.paulojunior97.apiblog.domain.service.AlbumService;
import com.paulojunior97.apiblog.domain.service.FeedNoticiasService;
import com.paulojunior97.apiblog.domain.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedNoticiasServiceImpl implements FeedNoticiasService {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PostService postService;

    @Autowired
    @Qualifier("listFeedAlbum")
    private Assembler feedAlbumAssembler;

    @Autowired
    @Qualifier("feedPostList")
    private Assembler feedPostAssembler;

    @Override
    public List<FeedNoticiaDto> feedNoticias() {
        List<FeedNoticiaDto> feedNoticiaDtos = new ArrayList<>();
        List<Album> albums = albumService.listar();
        if (albums != null) {
            feedNoticiaDtos.addAll(new ArrayList<>((List<FeedNoticiaDto>)feedAlbumAssembler.entityToDto(albums)));
        }
        List<Post> posts = postService.listar();
        if (posts != null) {
            feedNoticiaDtos.addAll(new ArrayList<>((List<FeedNoticiaDto>)feedPostAssembler.entityToDto(posts)));
        }

        return feedNoticiaDtos;
    }
}
