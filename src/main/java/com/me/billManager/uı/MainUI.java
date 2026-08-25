package com.me.billManager.uı;

import java.util.List;
import java.util.Stack;

import com.me.billManager.entity.Products;
import com.me.billManager.entity.Tables;
import com.me.billManager.repo.TableRepo;
import com.me.billManager.services.OrderService;
import com.me.billManager.services.ProductService;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainUI extends Application {

    private Stage primaryStage;
    private Stack<Scene> history = new Stack<>();
    private TableRepo tableRepo = new TableRepo();
    private ProductService productService = new ProductService();
    private OrderService orderService = new OrderService();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Bill Management");

        tableRepo.initDefaultTablesIfEmpty();
        productService.defaultProducts();
        showTableListScene();

        primaryStage.show();
    }

    private void showTableListScene() {
        BorderPane mainRoot = new BorderPane();
        
        FlowPane tablePane = new FlowPane();
        tablePane.setHgap(15);
        tablePane.setVgap(15);
        tablePane.setPadding(new Insets(20));

        List<Tables> tables = tableRepo.getAllTables();

        for (Tables table : tables) {
            Button btn = new Button("Table " + table.getTableNum());
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
                showOrderDetailScene(table);
            });
            
            tablePane.getChildren().add(btn);
        }

        mainRoot.setCenter(tablePane);

        Scene scene = new Scene(mainRoot, 800, 500); 
        primaryStage.setScene(scene);
    }

    private void showOrderDetailScene(Tables table) {
        history.push(primaryStage.getScene());

        BorderPane root2 = new BorderPane();
        root2.setPadding(new Insets(20));

        Button btnBack = new Button("⬅ Back ");
        btnBack.setStyle("-fx-font-size: 14px; -fx-cursor: hand;");
        btnBack.setOnAction(e -> {
            if (!history.isEmpty()) {
                primaryStage.setScene(history.pop());
            }
        });

        Label lblTitle = new Label("Table " + table.getTableNum() + " - Order Detail");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox topBar = new HBox(20, btnBack, lblTitle);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(0, 0, 15, 0));
        root2.setTop(topBar); 

        TextArea receiptArea = createTextArea(root2);

        FlowPane productPane = new FlowPane(10, 10);
        orderPane(table, productPane, receiptArea);
        root2.setCenter(productPane);

        Scene orderScene = new Scene(root2, 850, 500);
        primaryStage.setScene(orderScene);
    }
    
    private void orderPane(Tables table, Pane targetPane, TextArea receiptArea) {
        List<Products> productElements = productService.getAllProducts();
        
        for (Products productElement : productElements) {
            Button orderButton = new Button();
            orderButton.setText(productElement.getProductName() + "\n" + productElement.getOrderPrice() + " TL");
            orderButton.setPrefSize(150, 75);
            orderButton.setStyle(
                "-fx-background-color: #FF9800; " +       
                "-fx-text-fill: white; " +               
                "-fx-background-radius: 10px; " +          
                "-fx-font-size: 13px; " +                 
                "-fx-font-weight: bold; " + 
                "-fx-text-alignment: center; " +          
                "-fx-cursor: hand;"                        
            );
            
            orderButton.setOnAction(event -> {
                receiptArea.appendText(productElement.getProductName() + " - " + productElement.getOrderPrice() + " TL\n");
                
                orderService.orderManageSaveRepo(table, productElement);
            
               
            });
            
            targetPane.getChildren().add(orderButton);
        }
    }
    
    public TextArea createTextArea(BorderPane targetPane) {
        TextArea receiptArea = new TextArea();
        receiptArea.setEditable(false);
        receiptArea.setPrefWidth(240); 
        receiptArea.setWrapText(true);
        receiptArea.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 13px;");
        targetPane.setRight(receiptArea);
        return receiptArea;
    }
}