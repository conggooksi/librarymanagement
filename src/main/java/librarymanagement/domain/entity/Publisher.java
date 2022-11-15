package librarymanagement.domain.entity;

import librarymanagement.common.entity.BaseEntity;
import librarymanagement.domain.request.PublisherRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publisher")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Publisher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publisher_id")
    private Long id;

    @Column(name = "publisher_name")
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public Publisher(Long id, String name, List<Book> bookList) {
        this.id = id;
        this.name = name;
        this.bookList = bookList;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Publisher(String name) {
        this.name = name;
    }

    public void changeName(PublisherRequest publisherRequest) {
        this.name = publisherRequest.getPublisherName();
    }
}
