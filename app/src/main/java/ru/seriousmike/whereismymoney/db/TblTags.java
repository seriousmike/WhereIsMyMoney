package ru.seriousmike.whereismymoney.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ru.seriousmike.whereismymoney.data.ExpenseTag;

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


	public static Cursor getCursor(SQLiteDatabase db, Long id, String name) {
		String selection = "";
		String[] args = null;
		if(id!=null) {
			selection += FLD_ID+" = "+id+" ";
		}
		if(name != null) {
			selection += FLD_NAME + " = ?";
			args = new String[] {name};
		}

		if(id==null && name == null) {
			selection = null;
		}
		return db.query(TABLE_NAME, null, selection, args, null, null, FLD_NAME);
	}

	public static ExpenseTag createTagFromCursor(Cursor c) {
		return new ExpenseTag(
				c.getLong( c.getColumnIndex( FLD_ID ) ),
				c.getString( c.getColumnIndex( FLD_NAME ) )
		);
	}



}
