package com.sergio.vidiqvideos.services;

import com.sergio.vidiqvideos.models.Usuario;
import com.sergio.vidiqvideos.models.Video;
import com.sergio.vidiqvideos.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioById(String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> getUsuarioByUserName(String userName) {
        return usuarioRepository.findByUserNameContainsIgnoreCase(userName);
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario updateUsuario(Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(usuario.getId()).orElse(null);
        Objects.requireNonNull(existingUsuario).setUserName(usuario.getUserName());
        Objects.requireNonNull(existingUsuario).setEmail(usuario.getEmail());
        Objects.requireNonNull(existingUsuario).setPassword(usuario.getPassword());
        return usuarioRepository.save(existingUsuario);
    }

    public String deleteUsuario(String id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuarioRepository.deleteById(usuario.getId());
            return "Usuario con id = " + id + " eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario con id = " + id + " no encontrado");
        }
    }
}
