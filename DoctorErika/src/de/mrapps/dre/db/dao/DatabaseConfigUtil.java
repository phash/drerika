package de.mrapps.dre.db.dao;

import java.io.IOException;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	public static void main(String[] args) {
		try {
			writeConfigFile("ormlite_config.txt");
		} catch (java.sql.SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
