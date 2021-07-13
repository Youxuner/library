package com.jpa.library.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "autore")
public class Autore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAutore")
	private int idAutore;
	
	@NotNull
	@Column(name = "nome")
	private String nome;
	
	@NotNull
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "nazioneResidenza")
	private String nazioneResidenza;
	
	public Autore() {}

	public Autore(int idAutore, String nome, String cognome, String nazioneResidenza) {
		this.idAutore = idAutore;
		this.nome = nome;
		this.cognome = cognome;
		this.nazioneResidenza = nazioneResidenza;
	}

	public boolean passCheck()
	{
		if (this.nome == null || this.cognome == null)
			return false;
		
		return true;
	}
	
	public int getIdAutore() {
		return idAutore;
	}

	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazioneResidenza() {
		return nazioneResidenza;
	}

	public void setNazioneResidenza(String nazioneResidenza) {
		this.nazioneResidenza = nazioneResidenza;
	}

	@Override
	public String toString() {
		return "Autore [idAutore=" + idAutore + ", nome=" + nome + ", cognome=" + cognome + ", nazioneResidenza="
				+ nazioneResidenza + "]";
	}
}
