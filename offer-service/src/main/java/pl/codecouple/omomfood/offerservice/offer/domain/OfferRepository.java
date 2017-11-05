package pl.codecouple.omomfood.offerservice.offer.domain;


import org.springframework.data.repository.Repository;

import java.util.List;

interface OfferRepository extends Repository<Offer, Long> {
    Offer save(Offer offer);
    List<Offer> findAll();
}
