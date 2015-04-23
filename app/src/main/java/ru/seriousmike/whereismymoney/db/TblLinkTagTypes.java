package ru.seriousmike.whereismymoney.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.seriousmike.whereismymoney.utils.ProjHelper;

/**
 * Created by SeriousM on 21.04.2015.
 * Содержит методы для работы с таблицей связей many-to-many тэгов и типов расходов в БД.
 */
public class TblLinkTagTypes {

	public static final String TABLE_NAME = "expense_type_tag_link";

	public static final String FLD_ID = "_id";
	public static final String FLD_TYPE_ID = TblExpenseTypes.TABLE_NAME+"_id";
	public static final String FLD_TAG_ID = TblTags.TABLE_NAME+"_id";

	/**
	 * Возвращает текст SQL-запроса на создание таблицы
	 * @return create table sql-query
	 */
	public static String getCreateTableString() {
		return "CREATE TABLE "+TABLE_NAME+" (" +
				FLD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				FLD_TAG_ID +" INTEGER, " +
				FLD_TYPE_ID +" INTEGER, " +
				"FOREIGN_KEY("+ FLD_TAG_ID +") REFERENCES "+ TblTags.TABLE_NAME+" ("+ TblTags.FLD_ID+")"+
				"FOREIGN_KEY("+ FLD_TYPE_ID +") REFERENCES "+ TblExpenseTypes.TABLE_NAME+" ("+ TblExpenseTypes.FLD_ID+")"+
				")";
	}

	public static ContentValues getCV(long typeId, long tagId) {
		ContentValues cv = new ContentValues();
		cv.put(FLD_TYPE_ID, typeId);
		cv.put(FLD_TAG_ID, tagId);
		return cv;
	}

	public static int getCount(SQLiteDatabase db, Long typeId, Long tagId) {
		String selection = null;
		if(typeId != null || tagId != null) {
			selection = "";
			if(typeId!=null) {
				selection += FLD_TYPE_ID+"="+typeId;
			}
			if(tagId!=null) {
				selection += " "+FLD_TAG_ID+"="+tagId;
			}
		}
		Cursor c = db.query(TABLE_NAME, new String[] {"COUNT(*)"}, selection, null, null, null, null);
		return c.getInt(0);
	}

	public static List<Long> getTypeIdsByTagId(SQLiteDatabase db, long tagId) {
		List<Long> ids = new ArrayList<>();
		Cursor c = db.query(TABLE_NAME, new String[]{FLD_TYPE_ID}, FLD_TAG_ID+" = ?", new String[]{tagId+""}, null, null, null);
		c.moveToFirst();
		do {
			ids.add( c.getLong( 0 ) );
			c.moveToNext();
		} while (!c.isAfterLast());
		c.close();
		return ids;
	}

	public static List<Long> getTypeIdsByTagIds(SQLiteDatabase db, List<Long> tagIds) {
		List<Long> ids = new ArrayList<>();
		if(!tagIds.isEmpty()) {
			Cursor c = db.query(TABLE_NAME, new String[] {FLD_TYPE_ID}, FLD_TAG_ID+" IN ( ? )", new String[] {ProjHelper.implode(tagIds, ",")}, null, null, null);
			c.moveToFirst();
			do {
				ids.add( c.getLong( 0 ) );
				c.moveToNext();
			} while (!c.isAfterLast());
			c.close();
		}
		return ids;
	}

	public static List<Long> getTagIdsByTypeId(SQLiteDatabase db, long typeId) {
		List<Long> ids = new ArrayList<>();
		Cursor c = db.query(TABLE_NAME, new String[]{FLD_TAG_ID}, FLD_TYPE_ID+" = ?", new String[]{typeId+""}, null, null, null);
		c.moveToFirst();
		do {
			ids.add( c.getLong( 0 ) );
			c.moveToNext();
		} while (!c.isAfterLast());
		c.close();
		return ids;
	}

	public static List<Long> getTagIdsByTypeIds(SQLiteDatabase db, List<Long> typeIds) {
		List<Long> ids = new ArrayList<>();
		if(!typeIds.isEmpty()) {
			Cursor c = db.query(TABLE_NAME, new String[] {FLD_TAG_ID}, FLD_TYPE_ID+" IN ( ? )", new String[] {ProjHelper.implode(typeIds, ",")}, null, null, null);
			c.moveToFirst();
			do {
				ids.add( c.getLong( 0 ) );
				c.moveToNext();
			} while (!c.isAfterLast());
			c.close();
		}
		return ids;
	}
}
