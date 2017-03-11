package com.homebudget.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CustomFrameHeader extends BorderPane {
	
	private Button close, minimize;
	private HBox buttonContainer; 
	private ImageView logoImage;
	private static double xOffset, yOffset;
	
	public CustomFrameHeader(){
		this.setId("FrameHeader");
		this.setPrefSize(Main.getAppWidth(), 50);
		
		
		/*
		 * Dragging event
		 */
		this.setOnMousePressed(e -> {
			xOffset = ((Stage)this.getScene().getWindow()).getX() - e.getScreenX();
            yOffset = ((Stage)this.getScene().getWindow()).getY() - e.getScreenY();
		});
		
		this.setOnMouseDragged(e -> {
			((Stage)this.getScene().getWindow()).setX(e.getScreenX() + xOffset);
			((Stage)this.getScene().getWindow()).setY(e.getScreenY() + yOffset);
		});
		
		close = new Button();
		close.getStyleClass().add("close");
		close.setPrefSize(35, 30);
		close.setOnAction(e -> Platform.exit());
		
		minimize = new Button();
		minimize.getStyleClass().add("minimize");
		minimize.setPrefSize(35,30);
		minimize.setOnAction(e -> ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true));
		
		logoImage = new ImageView(new Image(Main.getProjectPath()+ "/images/LogoHeader.png"));
		this.setLeft(logoImage);
		
		buttonContainer = new HBox(1);
		buttonContainer.setPadding(new Insets(0,20,0,0));
		buttonContainer.getChildren().addAll(minimize, close);
		
		this.setRight(buttonContainer);
	}
	
}
