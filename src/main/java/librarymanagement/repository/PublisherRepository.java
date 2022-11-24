package librarymanagement.repository;

import librarymanagement.domain.entity.Publisher;
import librarymanagement.repository.impl.PublisherCustomRepository;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long>, PublisherCustomRepository {

    boolean existsByName(String publisherName);

    boolean existsByIdNotAndName(Long publisherId, String publisherName);
}
