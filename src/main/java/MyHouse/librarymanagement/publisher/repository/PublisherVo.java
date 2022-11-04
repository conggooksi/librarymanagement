package MyHouse.librarymanagement.publisher.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publisher")
@NoArgsConstructor
@Getter
public class PublisherVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;

    private String name;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}
