package com.paulojunior97.apiblog.api.controlador;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.UsuarioDto;
import com.paulojunior97.apiblog.api.dto.UsuarioResponseDto;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import com.paulojunior97.apiblog.domain.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.PermitAll;
import java.net.URI;
import java.util.List;

@Api(tags = "Usuários")
@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired @Qualifier("usuario")
    private Assembler assembler;

    @Autowired @Qualifier("usuarioResponseList")
    private Assembler assemblerList;

    @ApiOperation("Cadastrar novo usuário.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> cadastrar(@ApiParam(name = "Usuário", required = true, value = "Representação de um usuário.") @RequestBody UsuarioDto usuarioDto){
        Usuario usuario = (Usuario) assembler.dtoToEntity(usuarioDto);

        usuario = service.cadastrar(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("Excluir usuário.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deletar(@ApiParam("ID do usuário a ser excluido.") @PathVariable("id") Long idUsuario){
        service.deletar(idUsuario);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation("Consultar usuários.")
    @GetMapping
    @PermitAll
    public ResponseEntity<List<UsuarioResponseDto>> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) String email) {
        return ResponseEntity.ok((List<UsuarioResponseDto>)assemblerList.entityToDto(service.buscarPorNomeEmail(nome, email)));
    }
}
