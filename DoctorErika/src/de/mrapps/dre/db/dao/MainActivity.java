package de.mrapps.dre.db.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import de.mrapps.dre.R;

public class MainActivity extends ActionBarActivity {
	private final class ImportListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			RuntimeExceptionDao<Symptom, Integer> symptomDao = getHelper()
					.getSymptomDao();

			List<Symptom> list = symptomDao.queryForAll();
			// our string builder for building the content-view
			StringBuilder sb = new StringBuilder();
			sb.append("got ").append(list.size()).append(" entries in ")
					.append("\n");
			Toast.makeText(getApplicationContext(), sb, Toast.LENGTH_SHORT)
					.show();
			// if we already have items in the database

			doSampleDatabaseStuff("create");

		}
	}

	private final String LOG_TAG = getClass().getSimpleName();
	private DatabaseHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button b1 = (Button) findViewById(R.id.addButton);

		b1.setOnClickListener(new ImportListener());
		Button krankheiten = (Button) findViewById(R.id.buttonKrankheiten);
		krankheiten.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						KrankheitenListeActivity.class);
				startActivity(i);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void doSampleDatabaseStuff(String action) {
		RuntimeExceptionDao<Symptom, Integer> symptomDao = getHelper()
				.getSymptomDao();

		List<Symptom> list = symptomDao.queryForAll();
		StringBuilder sb = new StringBuilder();
		sb.append("got ").append(list.size()).append(" entries in ")
				.append(action).append("\n");

		Toast.makeText(getApplicationContext(), sb, Toast.LENGTH_SHORT).show();

		List<String> symptomBezeichnungen = Arrays.asList(new String[] {
				"Afterblutung", "Anämie (Blutarmut)", "Angst",
				"Appetitlosigkeit", "Atemnot (Dyspnoe)", "Augenschmerzen",
				"Ausbleiben der Regelblutung (Amenorrhö)",
				"Ausfluss bei der Frau", "Ausfluss beim Mann",
				"Bauchschmerzen, akuter Bauch",
				"Beinschwellungen, dicke Beine und Füße",
				"Blasenschwäche (Inkontinenz, Harninkontinenz)",
				"Blähungen, Luft im Bauch", "Blasse Haut (Blässe)",
				"Blut am After / aus dem Darm", "Blutarmut (Anämie)",
				"Blut im Urin",
				"Blutungen außerhalb der Regel, Zwischenblutungen",
				"Braune Flecken", "Brennen beim Wasserlassen",
				"Brustschmerzen",
				"Chronischer Schulterschmerz, steife Schulter",
				"Darmblutung, Blut am After",
				"Darmträgheit (Verstopfung, Obstipation)",
				"Depressive Verstimmung", "Dicker Hals (Schwellung am Hals)",
				"Dickes Bein, geschwollene Beine und Füße",
				"Durchfall (Diarrhö)", "Durst, vermehrter",
				"Dysphagie (Schluckstörung)", "Ellbogenschmerzen",
				"Erbrechen, Übelkeit", "Epistaxis (Nasenbluten)",
				"Fazialislähmung", "Fersenschmerz", "Fieber",
				"Gedächtnisprobleme, Hirnleistungsschwäche",
				"Gelbsucht (Ikterus)", "Gelenkschmerzen",
				"Gesäß- und Kreuzschmerzen",
				"Geschwollene Beine und Füße, dickes Bein", "Gesichtslähmung",
				"Gesichts- und Lidödem", "Gesichtsschmerzen",
				"Gewichtsverlust", "Haarausfall", "Halluzinationen",
				"Halsschmerzen", "Halsschwellung",
				"Harninkontinenz (Blasenschwäche, Inkontinenz)",
				"Hautausschlag", "Hauttrockenheit", "Heiserkeit", "Heißhunger",
				"Hexenschuss", "Hirnleistungsschwäche, Gedächtnisprobleme",
				"Hüftschmerzen", "Husten",
				"Hyperhidrose (übermäßiges Schwitzen)", "Ikterus (Gelbsucht)",
				"Inkontinenz", "Juckreiz", "Kalte Füße", "Kalte Hände",
				"Knieschmerzen", "Knoten in der Brust",
				"Knoten und Schwellungen an den Händen", "Kopfschmerzen",
				"Kreuz- und Gesäßschmerzen", "Kribbeln, Taubheitsgefühle",
				"Leistenschmerzen",
				"Lidschwellung, Lid- und Gesichtsödem, Lidgeschwulst",
				"Luft im Bauch, Blähungen", "Lymphknoten am Hals",
				"Mastodynie (schmerzende Brüste)", "Müdigkeit", "Mundgeruch",
				"Mundtrockenheit", "Muskelzittern (Tremor)", "Nachtschweiß",
				"Nackenschmerzen, steifer Hals", "Nasenbluten (Epistaxis)",
				"Obstipation (Verstopfung, Darmträgheit)",
				"Ohnmacht (Synkope)", "Ohrenschmerzen",
				"Ohrgeräusche (Tinnitus)", "Rotes (trockenes) Auge",
				"Rückenschmerzen", "Schlafstörungen", "Schluckauf",
				"Schluckstörung (Dysphagie)",
				"Schmerzen beim Sex – Ursachen bei Frauen",
				"Schmerzen beim Sex – Ursachen bei Männern",
				"Schmerzende Brüste (Mastodynie)", "Schmerzen im Gesäß, Kreuz",
				"Schnupfen (Rhinitis)", "Schulterschmerz, steife Schulter",
				"Schwellung am Hals, dicker Hals",
				"Schwellungen und Knoten an den Händen", "Schwerhörigkeit",
				"Schwindel", "Schwitzen (Hyperhidrose)",
				"Schwitzen, nächtliches", "Sehstörungen", "Sinnestäuschungen",
				"Sodbrennen", "Starker Durst",
				"Steife Schulter, chronischer Schulterschmerz",
				"Steifer Hals, Nackenschmerzen", "Taubheitsgefühle, Kribbeln",
				"Tinnitus (Ohrgeräusche)", "Trockene Haut", "Trockener Mund",
				"Übelkeit (Erbrechen)", "Unterleibsschmerzen",
				"Verstopfung (Obstipation, Darmträgheit)", "Wadenkrämpfe",
				"Zahnschmerzen", "Zittern (Muskelzittern, Tremor)",
				"Zungenbrennen",
				"Zwischenblutungen, Blutungen außerhalb der Regel" });

		for (String bez : symptomBezeichnungen) {

			Symptom symptom = new Symptom(bez, "keine Beschreibung bis jetzt");
			// Symptom find = symptomDao.queryForEq("bezeichnung", bez).get(0);
			// if (null == find) {
			symptomDao.create(symptom);
			// }

		}

		sb.append("nach insert: ").append(symptomDao.queryForAll().size())
				.append(" entries in ").append(action).append("\n");

		sb.append(" countof: ").append(symptomDao.countOf())
				.append(" entries in ").append(action).append("\n");
		Toast.makeText(getApplicationContext(), sb, Toast.LENGTH_SHORT).show();

		Krankheit krankheit1 = new Krankheit("BeispielKrankheit1",
				"das ist ein Beispiel1");
		Krankheit krankheit2 = new Krankheit("BeispielKrankheit2",
				"das ist ein Beispiel2");
		Krankheit krankheit3 = new Krankheit("BeispielKrankheit3",
				"das ist ein Beispiel3");

		List<Krankheit> krankheiten = new ArrayList<Krankheit>();
		krankheiten.add(krankheit1);
		krankheiten.add(krankheit2);
		krankheiten.add(krankheit3);

		for (Krankheit krankheit : krankheiten) {
			// Krankheit find = getHelper().getKrankheitDao()
			// .queryForEq("bezeichnung", krankheit.getBezeichnung())
			// .get(0);
			// if (find == null) {
			getHelper().getKrankheitDao().create(krankheit);
			// }
		}

		Symptom s1 = symptomDao.queryForEq("bezeichnung", "Sehst�rungen")
				.get(0);
		Symptom s2 = symptomDao.queryForEq("bezeichnung", "Trockene Haut").get(
				0);
		Symptom s3 = symptomDao.queryForEq("bezeichnung", "Ohrenschmerzen")
				.get(0);
		Symptom s4 = symptomDao.queryForEq("bezeichnung", "Halsschwellung")
				.get(0);

		ArrayList<SymptomProKrankheit> spks = new ArrayList<SymptomProKrankheit>();
		spks.add(new SymptomProKrankheit(s1, krankheit1, 10));
		spks.add(new SymptomProKrankheit(s1, krankheit2, 10));
		spks.add(new SymptomProKrankheit(s2, krankheit2, 30));
		spks.add(new SymptomProKrankheit(s3, krankheit2, 50));
		spks.add(new SymptomProKrankheit(s2, krankheit3, 80));
		spks.add(new SymptomProKrankheit(s4, krankheit3, 70));
		spks.add(new SymptomProKrankheit(s3, krankheit3, 90));

		for (SymptomProKrankheit symptomProKrankheit : spks) {
			// try {
			// SymptomProKrankheit spk = getHelper()
			// .getSymptomProKrankheitDao()
			// .queryBuilder()
			// .where()
			// .eq("krankheit",
			// symptomProKrankheit.getKrankheit().getId())
			// .and()
			// .eq("symptom", symptomProKrankheit.getSymptom().getId())
			// .queryForFirst();
			// if (spk != null) {
			getHelper().getSymptomProKrankheitDao().create(symptomProKrankheit);
			// }
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}

	}

	private DatabaseHelper getHelper() {
		if (helper == null) {

			helper = new DatabaseHelper(getApplicationContext());
		}
		return helper;
	}
}
