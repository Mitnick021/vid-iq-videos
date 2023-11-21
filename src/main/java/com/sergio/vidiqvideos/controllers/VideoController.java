package com.sergio.vidiqvideos.controllers;

import com.sergio.vidiqvideos.models.Video;
import com.sergio.vidiqvideos.services.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/video")
@AllArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/guardar")
    public ResponseEntity<Video> saveVideo(Video video) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(videoService.saveVideo(video));
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("/{id}")
    public Video getVideo(@PathVariable("id") String id) {
        return videoService.getVideo(id);
    }

    @GetMapping("/findByTituloOrCategoria")
    public List<Video> getVideoByTituloOrCategoria(String titulo, String categoria) {
        return videoService.getVideoByTituloOrCategoria(titulo, categoria);
    }

    @PutMapping("/updateVideo")
    public ResponseEntity<Video> updateVideo(Video video) {
        try{
            return ResponseEntity.ok(videoService.updateVideo(video));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @DeleteMapping("eliminar/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteVideo(@PathVariable("id") String id) {
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(videoService.deleteVideo(id));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
