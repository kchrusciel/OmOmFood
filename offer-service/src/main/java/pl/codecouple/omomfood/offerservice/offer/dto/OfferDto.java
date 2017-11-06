package pl.codecouple.omomfood.offerservice.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDto {
    private long id;
    private String title;
    private String content;
    private long userId;
}
