package com.jpa.library.bean;

public class MagazzinoDTO {
	private int qty;
	private String statoStock;
	
	public MagazzinoDTO() {}
	
	public MagazzinoDTO(Magazzino m) {
		this.qty = m.getQty();
		this.statoStock = m.getStatoStock();
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
	public String toString() {
		return "MagazzinoDTO [qty=" + qty + ", statoStock=" + statoStock + "]";
	}
	
}
