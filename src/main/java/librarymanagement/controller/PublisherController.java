package librarymanagement.controller;

import librarymanagement.common.result.ResponseHandler;
import librarymanagement.domain.request.PublisherRequest;
import librarymanagement.domain.request.PublisherSearch;
import librarymanagement.domain.response.PublisherDetail;
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
        return ResponseHandler.generate()
                .data(publisherList)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/{publisher_id}")
    public ResponseEntity<?> getPublisherDetail(@PathVariable(value = "publisher_id") Long publisherId) {
        PublisherDetail publisher = publisherService.getPublisher(publisherId);

        return ResponseHandler.generate()
                .data(publisher)
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("/{publisher_id}")
    public ResponseEntity<?> modifyPublisher(@PathVariable(value = "publisher_id") Long publisherId, @RequestBody @Valid PublisherRequest publisherRequest) {
        Long publisher = publisherService.modifyPublisher(publisherId, publisherRequest);

        return ResponseHandler.generate()
                .data(publisher)
                .status(HttpStatus.OK)
                .build();
    }
}
