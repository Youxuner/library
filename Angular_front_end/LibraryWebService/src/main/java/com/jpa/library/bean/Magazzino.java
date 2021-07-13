package com.jpa.library.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "magazzino")
public class Magazzino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmagazzino")
	private int idmagazzino;
	
	@NotNull
	@Column(name = "qty")
	private int qty;
	
	@NotNull
	@Column(name = "statoStock")
	private String statoStock;
	
	/*
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "isbn")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Libro libro;
	*/
	
	public Magazzino() {}

	public Magazzino(int idmagazzino, int qty, String statoStock) {
		this.idmagazzino = idmagazzino;
		this.qty = qty;
		this.statoStock = statoStock;
	}

	public int getIdmagazzino() {
		return idmagazzino;
	}

	public void setIdmagazzino(int idmagazzino) {
		this.idmagazzino = idmagazzino;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getStatoStock() {
		return statoStock;
	}

	public void setStatoStock(String statoStock) {
		this.statoStock = statoStock;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idmagazzino;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Magazzino other = (Magazzino) obj;
		if (idmagazzino != other.idmagazzino)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Magazzino [idmagazzino=" + idmagazzino + ", qty=" + qty + ", statoStock=" + statoStock + "]";
	}
	
	/*
	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	@Override
	public String toString() {
		return "Magazzino [idmagazzino=" + idmagazzino + ", qty=" + qty + ", statoStock=" + statoStock + ", libro="
				+ libro + "]";
	}
	*/
	
}
