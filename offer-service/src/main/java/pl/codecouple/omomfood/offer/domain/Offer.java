package pl.codecouple.omomfood.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.codecouple.omomfood.offer.dto.OfferDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by CodeCouple.pl
 */
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String content;
    private long authorId;

    OfferDto dto() {
        return OfferDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .authorId(authorId)
                .build();
    }
}
