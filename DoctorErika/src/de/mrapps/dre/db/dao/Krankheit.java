package de.mrapps.dre.db.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "krankheit")
public class Krankheit {

	public static final String ID_FIELD_NAME = "id";

	@DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
	private Integer id;

	public Krankheit() {
		super();
		// TODO Auto-generated constructor stub
	}

	@DatabaseField(unique = true)
	private String bezeichnung;

	@DatabaseField()
	private String beschreibung;

	public Krankheit(String bezeichnung, String beschreibung) {
		super();
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

}
