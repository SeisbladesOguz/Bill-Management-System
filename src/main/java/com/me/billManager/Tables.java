package com.me.billManager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name = "tables")

public class Tables {
	
	public Tables() {
		
	}
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long tableId;
	
	
	@Column (name = "table_num"  , nullable = false ,   length =  50 )
	private int tableNum;
	
	public Tables(int tableNum) {
		this.tableNum = tableNum;
	}
	
	public Long getTableId() {
		return this.tableId;
	}
	
	public int getTableNum() {
		return this.tableNum;
	}
	
	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}
	
    public Tables( Long tableId, int tableNum ) {
    	this.tableId = tableId;
    	this.tableNum = tableNum;
    }

}
