package librarymanagement.service.impl;

import librarymanagement.common.exception.ApiException;
import librarymanagement.common.exception.code.PublisherErrorCode;
import librarymanagement.domain.entity.Publisher;
import librarymanagement.domain.request.PublisherRequest;
import librarymanagement.domain.request.PublisherSearch;
import librarymanagement.domain.response.PublisherDetail;
import librarymanagement.domain.response.PublisherResponse;
import librarymanagement.repository.PublisherRepository;
import librarymanagement.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    @Transactional
    public Long addPublisher(PublisherRequest publisherRequest) {
        if(publisherRepository.existsByName(publisherRequest.getPublisherName())) {
            throw ApiException.builder()
                   .errorMessage(PublisherErrorCode.OVERLAP_PUBLISHER.getMessage())
                   .errorCode(PublisherErrorCode.OVERLAP_PUBLISHER.getCode())
                   .status(HttpStatus.BAD_REQUEST)
                   .build();
        } else {
            return publisherRepository.save(PublisherRequest.toEntity(publisherRequest)).getId();
        }
    }

    @Override
    public Page<PublisherResponse> getPublishers(PublisherSearch publisherSearch, Pageable pageable) {
        Page<Publisher> result = publisherRepository.findPublisher(publisherSearch, pageable);

        return result.map(PublisherResponse::toDto);
    }

    @Override
    @Transactional
    public Long modifyPublisher(Long publisherId, PublisherRequest publisherRequest) {
        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(PublisherErrorCode.NOT_FOUND_ID.getMessage())
                        .errorCode(PublisherErrorCode.NOT_FOUND_ID.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        if (publisherRepository.existsByIdNotAndName(publisherId, publisherRequest.getPublisherName())) {
            throw ApiException.builder()
                    .errorMessage(PublisherErrorCode.OVERLAP_PUBLISHER.getMessage())
                    .errorCode(PublisherErrorCode.OVERLAP_PUBLISHER.getCode())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        publisher.changeName(publisherRequest);

        return publisher.getId();
    }

    @Override
    public PublisherDetail getPublisher(Long publisherId) {
        Publisher publisher = publisherRepository.findByIdDetail(publisherId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(PublisherErrorCode.NOT_FOUND_ID.getMessage())
                        .errorCode(PublisherErrorCode.NOT_FOUND_ID.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return PublisherDetail.toDto(publisher);
    }
}
