package pl.codecouple.omomfood.offerservice.offer.domain;


import org.springframework.data.repository.Repository;
import pl.codecouple.omomfood.offerservice.offer.exceptions.OfferNotFound;

import java.util.List;

/**
 * Created by CodeCouple.pl
 */
interface OfferRepository extends Repository<Offer, Long> {
    Offer save(Offer offer);
    List<Offer> findAll();
    Offer findById(long id);
    void deleteById(long id);
    default Offer findByIdOrThrow(long id) {
        Offer offer = findById(id);
        if (offer == null) {
            throw new OfferNotFound();
        }
        return offer;
    }
}
