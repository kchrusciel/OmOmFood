package pl.codecouple.omomfood.offerservice.offer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

class InMemoryOfferRepository implements OfferRepository{
    private ConcurrentHashMap<Long, Offer> map = new ConcurrentHashMap<>();
    private long counter;

    @Override
    public Offer save(Offer offer) {
        requireNonNull(offer);
        map.put(counter++, offer);
        return offer;
    }

    @Override
    public List<Offer> findAll() {
        return new ArrayList<>(map.values());
    }
}
