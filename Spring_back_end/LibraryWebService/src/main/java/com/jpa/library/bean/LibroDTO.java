package com.jpa.library.bean;

public class LibroDTO {
	private int isbn;
	private String titolo;
	private String descrizione;
	private double prezzo;
	private int nCopie;
	private String categoria;
	
	public LibroDTO() {}
	
	public LibroDTO(Libro l) {
		this.isbn = l.getIsbn();
		this.titolo = l.getTitolo();
		this.descrizione = l.getDescrizione();
		this.prezzo = l.getPrezzo();
		this.nCopie = l.getnCopie();
		this.categoria = l.getCategoria().getCategoria();
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "LibroDTO [isbn=" + isbn + ", titolo=" + titolo + ", descrizione=" + descrizione + ", prezzo=" + prezzo
				+ ", nCopie=" + nCopie + ", categoria=" + categoria + "]";
	}
}
