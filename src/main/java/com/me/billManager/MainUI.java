package com.me.billManager;



import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;	

public class MainUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		primaryStage.setTitle("Bill Management");
		
		FlowPane root  = new FlowPane();
		
		

		TableRepo repo = new TableRepo();
		
		repo.initDefaultTablesIfEmpty();
		
		List<Tables> tables = repo.getAllTables();
		
		for(Tables table : tables) {
			
			Button btn = new Button("Table" + table.getTableNum());
			
			 btn.setPrefSize(110, 85);
			 
			 btn.setStyle(
		                "-fx-background-color: #2196F3; " +
		                "-fx-text-fill: white; " +
		                "-fx-background-radius: 12px; " +
		                "-fx-font-size: 16px; " +
		                "-fx-font-weight: bold; " +
		                "-fx-cursor: hand;" 

		            );
			 
			 btn.setOnAction(event -> {
				    System.out.println("Table " + table.getTableNum() + "  (ID: " + table.getTableId() + ")");
				}); 
			 
			  root.getChildren().add(btn);

		}
		
		Scene scene = new Scene(root , 700 , 400);
		
		root.setHgap(15); 
		root.setVgap(15); 
		
		root.setPadding(new Insets(20));
		
	
		
		primaryStage.setScene(scene);
		
		
		
		primaryStage.show();
		
		
		
		
		
	}


}