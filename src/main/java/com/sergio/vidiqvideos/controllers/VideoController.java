package com.sergio.vidiqvideos.controllers;

import com.sergio.vidiqvideos.models.Video;
import com.sergio.vidiqvideos.services.VideoService;
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
@RequestMapping("/video")
@AllArgsConstructor
@Tag(name = "Videos", description = "API REST para realizar operaciones CRUD con videos")
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/guardar")
    @Operation(summary = "Entrypoint para guardar un video")
    @ApiResponse(responseCode = "201", description = "el Video se ha guardado correctamente",
            content = @Content(schema = @Schema(implementation = Video.class)))
    public ResponseEntity<Video> saveVideo(Video video) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(videoService.saveVideo(video));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Entrypoint para obtener un video por su id")
    @Parameter(name = "identificador del video", example = "1")
    @ApiResponse(responseCode = "200", description = "el Video se ha obtenido correctamente")
    public Video getVideo(@PathVariable("id") String id) {
        return videoService.getVideo(id);
    }

    @GetMapping("/findByTituloOrCategoria")
    @Operation(summary = "Entrypoint para obtener un video por su titulo o categoria")
    @Parameter(name = "titulo del video", example = "Video de prueba")
    @ApiResponse(responseCode = "200", description = "el Video se ha obtenido correctamente")
    public List<Video> getVideoByTituloOrCategoria(String titulo, String categoria) {
        return videoService.getVideoByTituloOrCategoria(titulo, categoria);
    }

    @PutMapping("/updateVideo")
    @Operation(summary = "Entrypoint para actualizar un video")
    @ApiResponse(responseCode = "200", description = "el Video se ha actualizado correctamente",
            content = @Content(schema = @Schema(implementation = Video.class)))
    public ResponseEntity<Video> updateVideo(Video video) {
        try {
            return ResponseEntity.ok(videoService.updateVideo(video));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @DeleteMapping("eliminar/{id}")
    @Operation(summary = "Entrypoint para eliminar un video")
    @Parameter(name = "identificador del video", example = "1")
    @ApiResponse(responseCode = "204", description = "el Video se ha eliminado correctamente")
    @ResponseBody
    public ResponseEntity<String> deleteVideo(@PathVariable("id") String id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(videoService.deleteVideo(id));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
