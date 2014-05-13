package de.mrapps.dre.db.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "symptomprokrankheit")
public class SymptomProKrankheit {

	public static final String SYMPTOM_ID_FIELD_NAME = "symptom_id";

	public static final String KRANKHEIT_ID_FIELD_NAME = "krankheit_id";

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField(foreign = true, columnName = SYMPTOM_ID_FIELD_NAME)
	private Symptom symptom;

	@DatabaseField(foreign = true, columnName = KRANKHEIT_ID_FIELD_NAME)
	private Krankheit krankheit;

	@DatabaseField
	private Integer wahrscheinlichkeit;

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Krankheit getKrankheit() {
		return krankheit;
	}

	public void setKrankheit(Krankheit krankheit) {
		this.krankheit = krankheit;
	}

	public Integer getWahrscheinlichkeit() {
		return wahrscheinlichkeit;
	}

	public void setWahrscheinlichkeit(Integer wahrscheinlichkeit) {
		this.wahrscheinlichkeit = wahrscheinlichkeit;
	}

	public SymptomProKrankheit(Symptom symptom, Krankheit krankheit,
			Integer wahrscheinlichkeit) {
		super();
		this.symptom = symptom;
		this.krankheit = krankheit;
		this.wahrscheinlichkeit = wahrscheinlichkeit;
	}

	public SymptomProKrankheit() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
