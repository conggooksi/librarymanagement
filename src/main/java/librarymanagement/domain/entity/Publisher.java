package librarymanagement.domain.entity;

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

}
