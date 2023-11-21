package com.sergio.vidiqvideos.repositories;

import com.sergio.vidiqvideos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findByUserNameContainsIgnoreCase(String userName);
}
