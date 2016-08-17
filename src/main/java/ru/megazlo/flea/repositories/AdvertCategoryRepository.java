package ru.megazlo.flea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.megazlo.flea.entity.AdvertCategory;
import ru.megazlo.flea.repositories.custom.AdvertCategoryCustom;

/** Created by iGurkin on 17.08.2016. */
@Repository
public interface AdvertCategoryRepository extends JpaRepository<AdvertCategory, Long>, AdvertCategoryCustom {
}
