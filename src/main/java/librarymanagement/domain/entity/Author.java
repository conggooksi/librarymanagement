package librarymanagement.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@NoArgsConstructor
@Getter
public class Author extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "author_id", insertable = false, updatable = false)
    @Column(name = "author_id")
    private Long id;

//    @Column(insertable = false, updatable = false)
    @Column(name = "author_name")
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<BookAuthor> bookAuthorList = new ArrayList<>();

    @Builder(builderMethodName = "of", builderClassName = "of")
    public Author(Long id, String name, List<BookAuthor> bookAuthorList) {
        this.id = id;
        this.name = name;
        this.bookAuthorList = bookAuthorList;
    }

    @Builder(builderClassName = "createBuilder",builderMethodName = "createBuilder")
    public static Author create(String name) {
        return of()
                .name(name)
                .build();
    }
}
