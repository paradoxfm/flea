package ru.megazlo.flea.repositories.custom;

import ru.megazlo.flea.entity.Advert;

import java.util.List;

public interface AdvertCustom {

	List<Advert> findTopLastAdvert();

	List<Advert> findAdvert(String textSearch);
}
