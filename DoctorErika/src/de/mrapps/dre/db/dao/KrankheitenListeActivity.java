package de.mrapps.dre.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import de.mrapps.dre.R;

public class KrankheitenListeActivity extends Activity {

	private final String LOG_TAG = getClass().getSimpleName();
	private DatabaseHelper helper;

	private DatabaseHelper getHelper() {
		if (helper == null) {

			helper = new DatabaseHelper(getApplicationContext());
		}
		return helper;
	}

	ListView symptomListView;
	ListView krankheitenListView;
	ArrayAdapter<String> symptomAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_krankheit);

		krankheitenListView = (ListView) findViewById(R.id.krankheitenListe);
		symptomListView = (ListView) findViewById(R.id.symptomListe);

		symptomAdapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1);
		prepareListData();
	}

	private PreparedQuery<Symptom> makeSymptomForKrankheitQuery()
			throws SQLException {
		QueryBuilder<SymptomProKrankheit, Integer> symptomProKrankheitQb = getHelper()
				.getSymptomProKrankheitDao().queryBuilder();
		// this time selecting for the user-id field
		symptomProKrankheitQb
				.selectColumns(SymptomProKrankheit.SYMPTOM_ID_FIELD_NAME);
		SelectArg postSelectArg = new SelectArg();
		symptomProKrankheitQb.where().eq(
				SymptomProKrankheit.KRANKHEIT_ID_FIELD_NAME, postSelectArg);

		// build our outer query
		QueryBuilder<Symptom, Integer> symptomQb = getHelper().getSymptomDao()
				.queryBuilder();
		// where the user-id matches the inner query's user-id field
		symptomQb.where().in(Krankheit.ID_FIELD_NAME, symptomProKrankheitQb);
		return symptomQb.prepare();
	}

	private PreparedQuery<Symptom> symptomForKrankheitQuery = null;
	private final PreparedQuery<Krankheit> usersForPostQuery = null;

	private List<Symptom> lookupUsersForPost(Krankheit post)
			throws SQLException {
		if (symptomForKrankheitQuery == null) {
			symptomForKrankheitQuery = makeSymptomForKrankheitQuery();
		}
		symptomForKrankheitQuery.setArgumentHolderValue(0, post);
		return getHelper().getSymptomDao().query(symptomForKrankheitQuery);
	}

	private void prepareListData() {

		List<Krankheit> krankheiten = getHelper().getKrankheitDao()
				.queryForAll();
		ArrayList<String> header = new ArrayList<>(krankheiten.size());
		for (Krankheit krankheit : krankheiten) {
			header.add(krankheit.getBezeichnung());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				header);
		krankheitenListView.setAdapter(adapter);

		krankheitenListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent,
							final View view, int position, long id) {
						final String item = (String) parent
								.getItemAtPosition(position);
						Krankheit gefunden = getHelper().getKrankheitDao()
								.queryForEq("bezeichnung", item).get(0);

						symptomAdapter.clear();

						List<Symptom> symptomeFuerKrankheit;
						try {
							symptomeFuerKrankheit = lookupUsersForPost(gefunden);
							StringBuilder b = new StringBuilder();
							for (Symptom ele : symptomeFuerKrankheit) {
								symptomAdapter.add(ele.getBezeichnung());
								b.append(ele.getBezeichnung() + "\n");
							}

							Toast.makeText(getApplicationContext(), b,
									Toast.LENGTH_LONG).show();
							symptomAdapter.notifyDataSetChanged();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Toast.makeText(
								getApplicationContext(),
								gefunden.getId() + " "
										+ gefunden.getBezeichnung() + " "
										+ gefunden.getBeschreibung(),
								Toast.LENGTH_LONG).show();

					}

				});

	}
}
