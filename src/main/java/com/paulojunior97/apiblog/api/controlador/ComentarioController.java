package com.paulojunior97.apiblog.api.controlador;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.ComentarioDto;
import com.paulojunior97.apiblog.api.dto.ComentarioResponseDto;
import com.paulojunior97.apiblog.config.security.JwtTokenUtil;
import com.paulojunior97.apiblog.domain.entity.Comentario;
import com.paulojunior97.apiblog.domain.service.ComentarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Comentários")
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    @Qualifier("comentario")
    private Assembler assembler;

    @Autowired @Qualifier("comentarioList")
    private Assembler assemblerList;

    @Autowired
    private ComentarioService service;
    @Autowired
    private JwtTokenUtil jwtUtil;

    @ApiOperation("Cadastrar um novo comentário para a postagem.")
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> cadastrarComentario(@ApiParam(name = "Comentário", required = true, value = "Representação de um comentário.") @RequestBody ComentarioDto comentarioDto){
        Comentario comentario = (Comentario) assembler.dtoToEntity(new ComentarioResponseDto(comentarioDto.conteudo, comentarioDto.idPost));

        service.cadastrar(comentario, jwtUtil.getEmailUsuario());

        return ResponseEntity.accepted().build();
    }

    @ApiOperation("Listar comentários de uma postagem.")
    @GetMapping
    public ResponseEntity<List<ComentarioResponseDto>> buscarTodosComentarioDeUmaPostagem(@ApiParam(required = true, value = "Identificação de uma Postagem") @RequestParam Long idPostagem){
        List<Comentario> comentarios = service.buscarTodosComentariosDeUmPost(idPostagem);
        List<ComentarioResponseDto> comentariosResponseDto = (List<ComentarioResponseDto>) assemblerList.entityToDto(comentarios);
        return ResponseEntity.ok(comentariosResponseDto);
    }

    @ApiOperation("Deletar um comentário.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> removerComentario(@ApiParam(required = true, value = "Identificação de um comentário") @PathVariable("id") Long idComentario){
        service.excluir(idComentario, jwtUtil.getEmailUsuario());
        return ResponseEntity.accepted().build();
    }
}
