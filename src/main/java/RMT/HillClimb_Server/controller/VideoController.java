package RMT.HillClimb_Server.controller;

import RMT.HillClimb_Server.service.VideoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
@Getter
@Setter
@CrossOrigin(origins = "http://localhost:8000/")
public class VideoController {

    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/background")
    public ResponseEntity<String> downloadVideo() {
        String backgroundPath = videoService.loadBackground();
        return new ResponseEntity<>(backgroundPath, HttpStatus.OK);
    }
}