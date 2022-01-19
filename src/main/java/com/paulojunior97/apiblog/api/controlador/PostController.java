package com.paulojunior97.apiblog.api.controlador;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.PostDto;
import com.paulojunior97.apiblog.config.security.JwtTokenUtil;
import com.paulojunior97.apiblog.domain.entity.Post;
import com.paulojunior97.apiblog.domain.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Api(tags = "Postagens")
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    @Qualifier("post")
    private Assembler assembler;

    @Autowired @Qualifier("postList")
    private Assembler assemblerList;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private PostService service;

    @ApiOperation("Cadastrar um novo post.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> cadastrar(@ApiParam(name = "Post", required = true, value = "Representação de um post.") @RequestBody PostDto postDto){
        Post post = (Post) assembler.dtoToEntity(postDto);
        post.setTitulo("Post");
        post.setDataPublicacao(LocalDateTime.now());

        String emailUsuario = jwtUtil.getEmailUsuario();

        post = service.cadastrar(emailUsuario, post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("Buscar um post pelo seu identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> buscarPorId(@ApiParam(required = true, value = "Id de identificação da postagem.") @PathVariable("id") Long idPost){
        PostDto postDto = (PostDto) assembler.entityToDto(service.buscarPorId(idPost));

        return ResponseEntity.ok(postDto);
    }

    @ApiOperation("Buscar todas postagens.")
    @GetMapping
    public ResponseEntity<List<PostDto>> buscarTodosPosts(){
        List<PostDto> postDtoList = (List<PostDto>) assemblerList.entityToDto(service.buscarTodos());

        return ResponseEntity.ok(postDtoList);
    }

    @ApiOperation("Excluir uma postagem (apenas o criador pode excluir).")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> excluir(@ApiParam(required = true, value = "Id de identificação da postagem a ser excluida.") @PathVariable("id") Long idPost){
        service.excluir(jwtUtil.getEmailUsuario(), idPost);
        return ResponseEntity.accepted().build();
    }

}
