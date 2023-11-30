package com.sergio.vidiqvideos.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="videos")
@Schema(description = "Modelo de Video")
public class Video {

    @Id
    @Schema(description = "Identificador del video", example = "1")
    private String idCanal;

    @Column(name="titulo")
    @Schema(description = "Titulo del video", example = "Video de prueba")
    private String titulo;

    @Column(name="descripcion")
    @Schema(description = "Descripcion del video", example = "Este video entra en la categoria de prueba")
    private String descripcion;

    @Column(name="duracion")
    @Schema(description = "Duracion del video", example = "120 segundos")
    private Integer duracion;

    @Column(name="categoria")
    @Schema(description = "Categoria del video", example = "Prueba")
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
