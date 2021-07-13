package com.jpa.library.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import com.sun.istack.NotNull;

@Entity
@Table(name = "libro")
public class Libro {

	@Id
	@Column(name = "isbn")
	private int isbn;
	
	@NotNull
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@NotNull
	@Column(name = "prezzo")
	private double prezzo;
	
	@NotNull
	@Column(name = "nCopie")
	private int nCopie;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "libro_autore", joinColumns = @JoinColumn(name = "idlibro"),
			inverseJoinColumns = @JoinColumn(name = "idautore"))
	private Autore autore;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "libro_categoria", joinColumns = @JoinColumn(name = "idlibro"),
			inverseJoinColumns = @JoinColumn(name = "categoria"))
	private Categoria categoria;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "isbn")
	private List<Magazzino> magazzinoState = new ArrayList<>();

	public Libro() {}
	
	public Libro(int isbn, String titolo, String descrizione, double prezzo, int nCopie) {
		this.isbn = isbn;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.nCopie = nCopie;
	}
	
	public boolean passCheck()
	{
		if (this.isbn <= 0 || this.titolo == null || this.prezzo < 0.0 || this.nCopie <= 0
				|| this.autore == null || this.categoria == null)
			return false;
		
		if (!this.autore.passCheck() || !this.categoria.passCheck())
			return false;
		
		return true;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getnCopie() {
		return nCopie;
	}

	public void setnCopie(int nCopie) {
		this.nCopie = nCopie;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Magazzino> getMagazzinoState() {
		return magazzinoState;
	}

	public void setMagazzinoState(List<Magazzino> magazzinoState) {
		this.magazzinoState = magazzinoState;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titolo=" + titolo + ", descrizione=" + descrizione + ", prezzo=" + prezzo
				+ ", nCopie=" + nCopie + ", categoria=" + categoria + "]";
	}
	
}

/*
 * {
    "isbn": 111222,
    "titolo": "Hello World",
    "descrizione": "Hello",
    "prezzo": 54.0,
    "nCopie": 40,
    "categoria": {"categoria": "Default"},
    "autore": {
        "nome": "Alessio",
        "cognome": "Zhang",
        "nazioneResidenza": "Italy"
    },
    "magazzinoState": [
        {
            "qty": 50,
            "statoStock": "richiesto"
        }
    ]
	}
*/
