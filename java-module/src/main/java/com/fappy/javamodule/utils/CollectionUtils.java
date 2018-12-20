package com.fappy.javamodule.utils;

import java.util.List;

public class CollectionUtils {

	public static int findIndexOfIdInIds(long id, List<Long> ids) {
		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i) == id) {
				return i;
			}
		}
		throw new IndexOutOfBoundsException();
	}
	
	public static boolean isIdExistsInIds(long id, List<Long> ids) {
		return ids.stream()
				.filter(idFromStream -> idFromStream == id)
				.findFirst().isPresent();
	}
}
