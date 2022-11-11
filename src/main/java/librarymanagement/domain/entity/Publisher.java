package librarymanagement.domain.entity;

import librarymanagement.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "publisher")
@NoArgsConstructor
@Getter
public class Publisher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publisher_id")
    private Long id;

    @Column(name = "publisher_name")
    private String name;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public Publisher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Publisher(String name) {
        this.name = name;
    }
}
