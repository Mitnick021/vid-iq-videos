package com.sergio.vidiqvideos.controllers;


import com.sergio.vidiqvideos.models.Usuario;
import com.sergio.vidiqvideos.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioCotroller usuarioCotroller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioService.saveUsuario(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioCotroller.saveUsuario(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testGetUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioService.getUsuarioById(anyString())).thenReturn(usuario);

        Usuario response = usuarioCotroller.getUsuario("1");

        assertEquals(usuario, response);
    }

    @Test
    public void testGetUsuarioByUserName() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioService.getUsuarioByUserName(anyString())).thenReturn(usuarios);

        List<Usuario> response = usuarioCotroller.getUsuarioByUserName("username");

        assertEquals(usuarios, response);
    }

    @Test
    public void testGetUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioService.getUsuarios()).thenReturn(usuarios);

        List<Usuario> response = usuarioCotroller.getUsuarios();

        assertEquals(usuarios, response);
    }

    @Test
    public void testUpdateUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioService.updateUsuario(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioCotroller.updateUsuario(usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testDeleteUsuario() {
        String message = "Usuario deleted successfully";
        when(usuarioService.deleteUsuario(anyString())).thenReturn(message);

        ResponseEntity<String> response = usuarioCotroller.deleteUsuario("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    public void testSaveUsuarioException() {
        Usuario usuario = new Usuario();
        when(usuarioService.saveUsuario(any(Usuario.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        ResponseEntity<Usuario> response = usuarioCotroller.saveUsuario(usuario);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
