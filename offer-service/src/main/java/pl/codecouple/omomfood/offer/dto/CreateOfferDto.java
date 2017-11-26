package pl.codecouple.omomfood.offer.dto;

import lombok.*;

/**
 * Created by CodeCouple.pl
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOfferDto {
    private String title;
    private String content;
    private long authorId;
}
