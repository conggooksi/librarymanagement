package librarymanagement.domain.request;

import librarymanagement.domain.entity.Publisher;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PublisherRequest {

    @NotNull
    String publisherName;

    public static Publisher toEntity(PublisherRequest publisherRequest) {
        return Publisher.createBuilder()
                .name(publisherRequest.publisherName)
                .build();
    }
}
