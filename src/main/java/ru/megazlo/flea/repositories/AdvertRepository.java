package ru.megazlo.flea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.megazlo.flea.entity.Advert;
import ru.megazlo.flea.repositories.custom.AdvertCustom;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long>, AdvertCustom {

}
