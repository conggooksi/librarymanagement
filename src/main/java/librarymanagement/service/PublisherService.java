package librarymanagement.service;

import librarymanagement.domain.request.PublisherRequest;

public interface PublisherService {
    void addPublisher(PublisherRequest publisherRequest) throws Exception;
}
