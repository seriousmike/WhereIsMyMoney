package ru.seriousmike.whereismymoney.db;

import android.content.ContentValues;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SeriousM on 21.04.2015.
 * содержит методы для работы с таблицей расходов БД
 */
public class TblExpenses {

	public static final String TABLE_NAME = "expenses";

	public static final String FLD_ID = "_id";
	public static final String FLD_COST = "cost";
	public static final String FLD_TIMESTAMP = "timestamp";
	public static final String FLD_YYYYMMDD = "yyyymmdd";
	public static final String FLD_COMMENT = "comment";
	public static final String FLD_TYPE_ID = "expense_type_id";

	public static final int NODATA = -1;


	/**
	 * Возвращает текст SQL-запроса на создание таблицы
	 * @return create table sql-query
	 */
	public static String getCreateTableString() {
		return "CREATE TABLE "+TABLE_NAME+" (" +
				FLD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				FLD_COST+" REAL, "+
				FLD_TIMESTAMP+" INTEGER, " +
				FLD_YYYYMMDD+" INTEGER, " +
				FLD_COMMENT+" TEXT, " +
				FLD_TYPE_ID+" INTEGER, " +
				"FOREIGN_KEY("+FLD_TYPE_ID+") REFERENCES "+TblExpenseTypes.TABLE_NAME+" ("+TblExpenseTypes.FLD_ID+")"+
				")";
	}

	public static ContentValues getCV(float cost, long typeId, Date date, String comment) {
		ContentValues cv = new ContentValues();
		cv.put(FLD_COST, cost);
		cv.put(FLD_TYPE_ID, typeId);
		cv.put(FLD_TIMESTAMP, date.getTime());
		cv.put(FLD_YYYYMMDD, new SimpleDateFormat("yyyyMMdd").format(date) );
		cv.put(FLD_COMMENT, comment);
		return cv;
	}


}
