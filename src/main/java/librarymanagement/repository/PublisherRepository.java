package librarymanagement.repository;

import librarymanagement.domain.entity.Publisher;
import librarymanagement.domain.request.PublisherSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long>, PublisherCustomRepository {

    boolean existsByName(String publisherName);

    boolean existsByIdNotAndName(Long publisherId, String publisherName);
}
