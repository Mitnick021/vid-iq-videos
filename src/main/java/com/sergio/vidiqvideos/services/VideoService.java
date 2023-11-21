package com.sergio.vidiqvideos.services;

import com.sergio.vidiqvideos.models.Video;
import com.sergio.vidiqvideos.repositories.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video getVideo(String id) {
        return videoRepository.findById(id).orElse(null);
    }

    public List<Video> getVideoByTituloOrCategoria(String titulo, String categoria) {
        return videoRepository.findVideoByTituloContainsIgnoreCase(titulo);
    }

    public Video updateVideo(Video video) {
        Video existingVideo = videoRepository.findById(video.getIdCanal()).orElse(null);
        Objects.requireNonNull(existingVideo).setTitulo(video.getTitulo());
        Objects.requireNonNull(existingVideo).setDescripcion(video.getDescripcion());
        Objects.requireNonNull(existingVideo).setDuracion(video.getDuracion());
        Objects.requireNonNull(existingVideo).setCategoria(video.getCategoria());
        return videoRepository.save(video);
    }

    public String deleteVideo(String id) {
        Optional<Video> optionalVideo = videoRepository.findById(id);

        if (optionalVideo.isPresent()) {
            Video video = optionalVideo.get();
            videoRepository.deleteById(video.getIdCanal());
            return "Video con id = " + id + " eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video con id = " + id + " no encontrado");
        }
    }
}
