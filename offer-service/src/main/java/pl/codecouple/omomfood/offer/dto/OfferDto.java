package pl.codecouple.omomfood.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by CodeCouple.pl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDto {
    private long id;
    private String title;
    private String content;
    private long authorId;
}
