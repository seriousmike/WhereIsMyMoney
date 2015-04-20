package ru.seriousmike.whereismymoney.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.seriousmike.whereismymoney.db.TblExpenseTypes;
import ru.seriousmike.whereismymoney.db.TblExpenses;
import ru.seriousmike.whereismymoney.db.TblTags;

/**
 * Created by SeriousM on 21.04.2015.
 * Синглтон-хэлпер для общения с БД
 */
public class DbHelper extends SQLiteOpenHelper {

	private static final String TAG = "sm_H_DbHelper";

	private static final String DB_NAME = "money_loss_investigator";
	private static final int DB_VER = 1;

	private static DbHelper sInstance;

	private DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VER);
	}

	public static synchronized DbHelper getInstance(Context context) {
		if(sInstance == null) {
			sInstance = new DbHelper(context.getApplicationContext());
		}
		return sInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TblTags.getCreateTableString());
		db.execSQL(TblExpenseTypes.getCreateTableString());
		db.execSQL(TblExpenses.getCreateTableString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
