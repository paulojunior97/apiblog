package com.paulojunior97.apiblog.api.controlador;

import com.paulojunior97.apiblog.api.dto.FeedNoticiaDto;
import com.paulojunior97.apiblog.domain.service.FeedNoticiasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Feed Noticias")
@RestController
@RequestMapping("/feed-noticias")
public class FeedNoticiasController {

    @Autowired
    private FeedNoticiasService service;

    @ApiOperation("Listar feed noticias")
    @GetMapping
    public ResponseEntity<List<FeedNoticiaDto>> buscarAlbum(){
        return ResponseEntity.ok(service.feedNoticias());
    }
}
