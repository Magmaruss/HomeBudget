package com.homebudget.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class RealBookmark extends StackPane {
	
	public RealBookmark(){
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		this.getChildren().add(new Label("Zak³adka RZECZYWISTOŒÆ"));
	}
	
}
