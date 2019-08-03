package kz.zhanbolat.web.application.service;

import java.util.ArrayList;
import java.util.List;

import kz.zhanbolat.web.domain.entity.Entity;

public class PageService {
	public static final int DEFAULT_PAGE_SIZE = 5;
	
	public List<? extends Entity> convertToPageFormat(int pageId, List<? extends Entity> entities) {
		List<Entity> convertedEntities = new ArrayList<>();
		int size = pageId * DEFAULT_PAGE_SIZE;
		int diff = 0;
		if (entities.size() < size) {
			diff = size - entities.size();
		}
		for (int i = size - DEFAULT_PAGE_SIZE; i < size - diff; i++) {
			convertedEntities.add(entities.get(i));
		}
		
		return convertedEntities;
	}
	
}
