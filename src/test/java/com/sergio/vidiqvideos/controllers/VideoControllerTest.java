package com.sergio.vidiqvideos.controllers;

import com.sergio.vidiqvideos.models.Video;
import com.sergio.vidiqvideos.services.VideoService;
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

public class VideoControllerTest {

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveVideo() {
        Video video = new Video();
        when(videoService.saveVideo(any(Video.class))).thenReturn(video);

        ResponseEntity<Video> response = videoController.saveVideo(video);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(video, response.getBody());
    }

    @Test
    public void testGetVideo() {
        Video video = new Video();
        when(videoService.getVideo(anyString())).thenReturn(video);

        Video response = videoController.getVideo("1");

        assertEquals(video, response);
    }


    @Test
    public void testUpdateVideo() {
        Video video = new Video();
        when(videoService.updateVideo(any(Video.class))).thenReturn(video);

        ResponseEntity<Video> response = videoController.updateVideo(video);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(video, response.getBody());
    }

    @Test
    public void testDeleteVideo() {
        String message = "Video deleted successfully";
        when(videoService.deleteVideo(anyString())).thenReturn(message);

        ResponseEntity<String> response = videoController.deleteVideo("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    public void testSaveVideoException() {
        Video video = new Video();
        when(videoService.saveVideo(any(Video.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        ResponseEntity<Video> response = videoController.saveVideo(video);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
