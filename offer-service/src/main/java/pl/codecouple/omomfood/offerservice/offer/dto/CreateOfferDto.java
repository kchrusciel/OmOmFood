package pl.codecouple.omomfood.offerservice.offer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOfferDto {
    private String title;
    private String content;
    private long userId;
}
