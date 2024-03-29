package de.mrapps.dre.db.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import de.mrapps.dre.R;

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	// name of the database file for your application -- change to something
	// appropriate for your app
	private static final String DATABASE_NAME = "drerika.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 7;

	private RuntimeExceptionDao<Symptom, Integer> symptomRuntimeDao = null;
	private RuntimeExceptionDao<Krankheit, Integer> krankheitRuntimeDao = null;
	private RuntimeExceptionDao<SymptomProKrankheit, Integer> symptomProKrankheitRuntimeDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, SymptomProKrankheit.class);
			TableUtils.createTable(connectionSource, Symptom.class);
			TableUtils.createTable(connectionSource, Krankheit.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, SymptomProKrankheit.class,
					true);
			TableUtils.dropTable(connectionSource, Symptom.class, true);
			TableUtils.dropTable(connectionSource, Krankheit.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
	 * for our Symptom class. It will create it or just give the cached value.
	 * RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<Symptom, Integer> getSymptomDao() {
		if (symptomRuntimeDao == null) {
			symptomRuntimeDao = getRuntimeExceptionDao(Symptom.class);
		}
		return symptomRuntimeDao;
	}

	public RuntimeExceptionDao<Krankheit, Integer> getKrankheitDao() {
		if (krankheitRuntimeDao == null) {
			krankheitRuntimeDao = getRuntimeExceptionDao(Krankheit.class);
		}
		return krankheitRuntimeDao;
	}

	public RuntimeExceptionDao<SymptomProKrankheit, Integer> getSymptomProKrankheitDao() {
		if (symptomProKrankheitRuntimeDao == null) {
			symptomProKrankheitRuntimeDao = getRuntimeExceptionDao(SymptomProKrankheit.class);
		}
		return symptomProKrankheitRuntimeDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		symptomRuntimeDao = null;
		krankheitRuntimeDao = null;
	}
}