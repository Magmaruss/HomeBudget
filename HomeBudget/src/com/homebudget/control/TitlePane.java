package com.homebudget.control;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TitlePane extends StackPane{
	
	private Label tLabel;
	
	// Pane z odpowiednikiem TitledBorder
	public TitlePane(String title){
		setId("TitledPane");
		tLabel = new Label(" "+title+" ");
		tLabel.setTextFill(Color.SILVER);
		
		setAlignment(tLabel, Pos.TOP_LEFT);
		
		tLabel.getStyleClass().add("txt-label");
		getChildren().add(tLabel);
		
	}
	
	public Label getLabel(){
		return tLabel;
	}
}
