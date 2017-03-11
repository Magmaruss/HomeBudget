package com.homebudget.control;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;

public class CustomTooltip extends Popup{
	
	private Label txt;
	private StackPane pane;
	private double deltaY; // Przyrost Y w stosunku do oryginalnej pozycji
	private double showY; // Y dla oryginalnego po³o¿enia
	
	public CustomTooltip(){
		pane = new StackPane();
		deltaY = 0;
		showY = 0;
		pane.setStyle("-fx-background-color: black; -fx-background-radius: 5px; -fx-padding: 5px 5px 5px 5px; -fx-opacity: 0.8;");
		txt = new Label();
		txt.setTextFill(Color.WHITESMOKE);
		txt.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
		pane.getChildren().add(txt);
		getContent().add(pane);
		setAutoHide(true);
	}
	
	
	
	public void setText(String text){
		txt.setText(text);
	}
	
	public StackPane getPane(){
		return pane;
	}
	
	// Wyœwietlanie Popupa poni¿ej komponentu + ewentualna poprawka pozycji y
	public void show(Node ownerNode){
		//show(ownerNode, ((Stage)ownerNode.getScene().getWindow()).getX()+ ((Scene)ownerNode.getScene()).getX() + ownerNode.getLocalToSceneTransform().getMxx(), ((Stage)ownerNode.getScene().getWindow()).getY() + ((Scene)ownerNode.getScene()).getY() + ownerNode.getLayoutY() + ownerNode.getLayoutBounds().getHeight() + y + deltaY);
		Bounds bounds = ownerNode.localToScreen(ownerNode.getBoundsInLocal());
		
		show(ownerNode, bounds.getMinX(), bounds.getMaxY()+2);
	}
	
	// Zmiana po³o¿enia Popupa
	public void translatePopupY(Node ownerNode, double y){
		deltaY = y;
		if(isShowing()){
			hide();
			show(ownerNode, ownerNode.getScene().getWindow().getX()+ ownerNode.getScene().getX() + ownerNode.getLayoutX(), ownerNode.getScene().getWindow().getY() + ownerNode.getScene().getY() + ownerNode.getLayoutY() + ownerNode.getLayoutBounds().getHeight() + showY + deltaY);
			
		}
	}
	
}
