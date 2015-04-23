package ru.seriousmike.whereismymoney.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.seriousmike.whereismymoney.data.ExpenseTag;
import ru.seriousmike.whereismymoney.data.ExpenseType;
import ru.seriousmike.whereismymoney.db.TblExpenseTypes;
import ru.seriousmike.whereismymoney.db.TblExpenses;
import ru.seriousmike.whereismymoney.db.TblLinkTagTypes;
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

	// --- ТИПЫ ТРАТ ---------------------------------------------------------
	public long insertExpType(String typeName) {
		return getWritableDatabase().insert(TblExpenseTypes.TABLE_NAME, null, TblExpenseTypes.getCV(typeName));
	}

	public ExpenseType getExpTypeByName(String typeName) {
		Cursor c = TblExpenseTypes.getCursor(getReadableDatabase(), null, typeName);
		if(c.getCount()>0) {
			c.moveToFirst();
			return TblExpenseTypes.createTypeFromCursor(c);
		}
		return null;
	}


	// --- ТЭГИ ТРАТ -------------------------------------------------------
	public long insertExpTag(String tagName) {
		return getWritableDatabase().insert(TblTags.TABLE_NAME, null, TblTags.getCV(tagName));
	}

	public ExpenseTag getExpTagByName(String tagName) {
		Cursor c = TblTags.getCursor(getReadableDatabase(), null, tagName);
		if(c.getCount()>0) {
			c.moveToFirst();
			return TblTags.createTagFromCursor(c);
		}
		return null;
	}


	// -- связи
	public long linkTypeTag(long typeId, long tagId) {
		if(TblLinkTagTypes.getCount(getReadableDatabase(), typeId, tagId)==0) {
			return getWritableDatabase().insert(TblLinkTagTypes.TABLE_NAME, null, TblLinkTagTypes.getCV(typeId, tagId));
		}
		return -1;
	}
}
