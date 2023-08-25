package RMT.HillClimb_Server.service.implementation;

import RMT.HillClimb_Server.service.VideoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class VideoServiceImplementation implements VideoService {
    private String backgroundVideo = "Background.mp4";
    private final ResourceLoader resourceLoader;

    @Autowired
    public VideoServiceImplementation(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public String loadBackground() {
        String backgroundPath = "/videos/" + backgroundVideo;
            return backgroundPath;
    }
}
