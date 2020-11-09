package ru.svlit.feature.coronadash.adapter.persistance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryStatsRepository extends MongoRepository<CountryStat, String> {
}
