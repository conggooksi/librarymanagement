package librarymanagement.controller;

import librarymanagement.domain.request.PublisherRequest;
import librarymanagement.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping("")
    public ResponseEntity<?> addPublisher(@RequestBody PublisherRequest publisherRequest) {
        publisherService.addPublisher(publisherRequest);

        return ResponseEntity.ok(null);
    }
}
