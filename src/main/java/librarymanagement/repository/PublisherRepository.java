package librarymanagement.repository;

import librarymanagement.domain.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

    boolean existsByName(String publisherName);
}
