package ru.seriousmike.whereismymoney.utils;

import java.util.List;

/**
 * Created by SeriousM on 21.04.2015.
 */
public class ProjHelper {

	public static String implode(List<Long> list, String glue) {
		StringBuilder builder = new StringBuilder();

		if(list.isEmpty()) {
			return "";
		}

		builder.append( list.remove(0) );
		for(Long l : list) {
			builder.append(glue);
			builder.append(l);
		}

		return builder.toString();
	}

}
