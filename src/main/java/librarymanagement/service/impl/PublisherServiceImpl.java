package librarymanagement.service.impl;

import librarymanagement.domain.entity.Publisher;
import librarymanagement.domain.request.PublisherRequest;
import librarymanagement.repository.PublisherRepository;
import librarymanagement.service.PublisherService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    @Transactional
    public void addPublisher(PublisherRequest publisherRequest) {

        try {
            if (!publisherRepository.existsByName(publisherRequest.getPublisherName())) {
                Publisher publisher = publisherRequest.toEntity(publisherRequest);
                publisherRepository.save(publisher);
            }
        } catch (Exception e) {

        }
    }
}
