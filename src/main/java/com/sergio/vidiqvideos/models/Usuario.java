package com.sergio.vidiqvideos.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="usuarios")
@Schema(description = "Modelo de Usuario")
public class Usuario {

    @Id
    @Schema(description = "Identificador del usuario", example = "1")
    private String id;

    @Column(name="user_name")
    @Schema(description = "Nombre de usuario", example = "sergio")
    private String userName;

    @Column(name="email")
    @Schema(description = "Correo electronico del usuario", example = "sergio@gmail.com")
    private String email;

    @Column(name="password")
    @Schema(description = "Contraseña del usuario", example = "******")
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Video> videos;
}
