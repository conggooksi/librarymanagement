package librarymanagement.service;

import librarymanagement.domain.entity.Publisher;
import librarymanagement.domain.request.PublisherRequest;
import librarymanagement.domain.request.PublisherSearch;
import librarymanagement.domain.response.PublisherDetail;
import librarymanagement.domain.response.PublisherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublisherService {
    Long addPublisher(PublisherRequest publisherRequest);

    Page<PublisherResponse> getPublishers(PublisherSearch publisherSearch, Pageable pageable);

    Long modifyPublisher(Long publisherId, String publisherName);

    PublisherDetail getPublisher(Long publisherId);
}
