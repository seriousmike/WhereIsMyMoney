package ru.seriousmike.whereismymoney.db;

import android.content.ContentValues;

/**
 * Created by SeriousM on 21.04.2015.
 * Содержит методы для работы с таблицей тэгов расходов БД.
 * Тэги привязываются по желанию к типам расходов для более удобной группировки
 */
public class TblTags {
	public static final String TABLE_NAME = "expense_type_tag";

	public static final String FLD_ID = "_id";
	public static final String FLD_NAME = "name";

	/**
	 * Возвращает текст SQL-запроса на создание таблицы
	 * @return create table sql-query
	 */
	public static String getCreateTableString() {
		return "CREATE TABLE "+TABLE_NAME+" (" +
				FLD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				FLD_NAME+" TEXT "+
				")";
	}

	public static ContentValues getCV(String name) {
		ContentValues cv = new ContentValues();
		cv.put(FLD_NAME, name);
		return cv;
	}

}
