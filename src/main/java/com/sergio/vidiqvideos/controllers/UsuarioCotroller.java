package com.sergio.vidiqvideos.controllers;

import com.sergio.vidiqvideos.models.Usuario;
import com.sergio.vidiqvideos.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
@Tag(name = "Usuarios", description = "API REST para realizar operaciones CRUD con usuarios")
public class UsuarioCotroller {
    private final UsuarioService usuarioService;

    @PostMapping("/guardar")
    @Operation(summary = "Entrypoint para guardar un usuario")
    @ApiResponse(responseCode = "201", description = "el Usuario se ha guardado correctamente",
            content = @Content(schema = @Schema(implementation = Usuario.class)))
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuario));
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Entrypoint para obtener un usuario por su id")
    @Parameter(name = "identificador del usuario", example = "CC-1234567890")
    @ApiResponse(responseCode = "200", description = "el Usuario se ha obtenido correctamente")
    public Usuario getUsuario(@PathVariable("id") String id) {
        return usuarioService.getUsuarioById(id);
    }

    @GetMapping("/findByUsename")
    @Operation(summary = "Entrypoint para obtener un usuario por su nombre de usuario")
    @Parameter(name = "nombre de usuario", example = "sergio")
    @ApiResponse(responseCode = "200", description = "el Usuario se ha obtenido correctamente")
    public List<Usuario> getUsuarioByUserName(@RequestBody String username) {
        return usuarioService.getUsuarioByUserName(username);
    }

    @GetMapping("/findAll")
    @Operation(summary = "Entrypoint para obtener todos los usuarios")
    @ApiResponse(responseCode = "200", description = "los Usuarios se han obtenido correctamente")
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PutMapping("/updateUsuario")
    @Operation(summary = "Entrypoint para actualizar un usuario")
    @ApiResponse(responseCode = "200", description = "el Usuario se ha actualizado correctamente",
            content = @Content(schema = @Schema(implementation = Usuario.class)))
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        try{
            return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @DeleteMapping("eliminar/{id}")
    @Operation(summary = "Entrypoint para eliminar un usuario")
    @Parameter(name = "identificador del usuario", example = "CC-1234567890")
    @ApiResponse(responseCode = "204", description = "el Usuario se ha eliminado correctamente")
    @ResponseBody
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") String id) {
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioService.deleteUsuario(id));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
