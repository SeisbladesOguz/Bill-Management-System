package com.me.billManager;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table
@Entity

public class Orders {
	
	
	
	public Orders() {
		
	}
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Long orderId;
	
	
	@ManyToOne
	@JoinColumn(name = "masa_id")
	Tables table;
	
	@ManyToOne
	@JoinColumn(name = "urun_id")
    Products product;
	
	@Column(name = "piece" , nullable = false , length = 100)
	private int piece;
	
	@Column(name = "purchase_price" , nullable = false , length = 100)
	private int purchasePrice;
	
	@Column(name = "sale_price" , nullable = false , length = 100)
	private int salePrice;
	
	@Column(name = "stiuation" , nullable = false , length = 100)
	private boolean stiuation;
	
	@Column(name = "profit" , nullable = false , length = 100)
	private int profit;
	
	public Orders( Long orderId , Tables table , Products product , int piece , int purchasePrice , int salePrice , boolean stiuation , int profit) {
		this.orderId = orderId;
		this.table = table;
		this.product = product;
		this.piece = piece;
		this.salePrice = salePrice;
		this.stiuation = stiuation;
		this.profit = profit;
	}
	
	
	


}
