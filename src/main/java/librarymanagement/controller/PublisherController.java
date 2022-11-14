package librarymanagement.controller;

import librarymanagement.common.result.ResponseHandler;
import librarymanagement.domain.request.PublisherRequest;
import librarymanagement.domain.request.PublisherSearch;
import librarymanagement.domain.response.PublisherResponse;
import librarymanagement.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping("")
    public ResponseEntity<?> addPublisher(@RequestBody PublisherRequest publisherRequest) {
        Long publisherId = publisherService.addPublisher(publisherRequest);

        return ResponseHandler.generate()
            .data(publisherId)
            .status(HttpStatus.OK)
            .build();
    }

    @GetMapping("")
    public ResponseEntity<?> getPublishers(PublisherSearch publisherSearch, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PublisherResponse> publisherList = publisherService.getPublishers(publisherSearch, pageable);
        return new ResponseEntity<>(publisherList, HttpStatus.OK);
    }

    @GetMapping("/{publisher_id}")
    public ResponseEntity<?> getPublisher(@PathVariable(value = "publisher_id") Long publisherId) {
        return ResponseHandler.generate()
                .data(publisherService.getPublisher(publisherId))
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("/{publisher_id}")
    public ResponseEntity<?> modifyPublisher(@PathVariable(value = "publisher_id") Long publisherId, @RequestBody @Valid PublisherRequest publisherRequest) {
        return ResponseHandler.generate()
                .data(publisherService.modifyPublisher(publisherId, publisherRequest))
                .status(HttpStatus.OK)
                .build();
    }
}
