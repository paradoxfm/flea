package ru.megazlo.flea.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.megazlo.flea.entity.Advert;
import ru.megazlo.flea.entity.AdvertCategory;
import ru.megazlo.flea.model.AdvertCategoryEdit;
import ru.megazlo.flea.model.AdvertListView;
import ru.megazlo.flea.repositories.AdvertCategoryRepository;
import ru.megazlo.flea.repositories.AdvertRepository;
import ru.megazlo.flea.services.AdvertService;

import java.util.List;

/** Created by iGurkin on 17.08.2016. */
@Service
public class AdvertServiceImpl implements AdvertService {

	@Autowired
	private ModelMapper mMap;

	@Autowired
	private AdvertCategoryRepository catRepository;

	public void addCategory(AdvertCategoryEdit catEdit) {
		AdvertCategory cat = mMap.map(catEdit, AdvertCategory.class);
		cat = catRepository.save(cat);
		catRepository.insertCatTree(cat);
	}

	private String getConvertToTsQuery(String queryString) {
		final String[] strings = queryString.split("[\\W]");
		return String.join("&", strings);
	}

	public List<AdvertListView> getTopAdverts() {
		return null;
	}

	public List<AdvertListView> getSearshAdverts() {
		Advert ent = new Advert();
		final AdvertListView advertView = mMap.map(ent, AdvertListView.class);
		return null;
	}
}
