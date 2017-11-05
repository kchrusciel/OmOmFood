package pl.codecouple.omomfood.offerservice.offer.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OfferDto {

    private String title;
    private String content;
    private long userId;
}
