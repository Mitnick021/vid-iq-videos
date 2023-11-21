package com.sergio.vidiqvideos.controllers;

import com.sergio.vidiqvideos.models.Usuario;
import com.sergio.vidiqvideos.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioCotroller {
    private final UsuarioService usuarioService;

    @PostMapping("/guardar")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuario));
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable("id") String id) {
        return usuarioService.getUsuarioById(id);
    }

    @GetMapping("/findByUsename")
    public List<Usuario> getUsuarioByUserName(@RequestBody String username) {
        return usuarioService.getUsuarioByUserName(username);
    }

    @GetMapping("/findAll")
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PutMapping("/updateUsuario")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        try{
            return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @DeleteMapping("eliminar/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") String id) {
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioService.deleteUsuario(id));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
