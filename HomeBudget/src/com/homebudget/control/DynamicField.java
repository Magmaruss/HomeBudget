package com.homebudget.control;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DynamicField extends TextField {
	
	private Label txt;
	private boolean correctFilled;
	private CustomTooltip tip;
	
	
	// Przestarza�y konstruktor
	public DynamicField(){
		//setDisable(true);
		correctFilled = false;
		txt = new Label("Dotknij");
		getChildren().add(txt);
		textProperty().addListener(e -> {
			
		});
		focusedProperty().addListener(e ->{
			if(focusedProperty().get() == true){
				setStyle("-fx-border-color: rgb(250,228,0)");
				getChildren().remove(txt);
			}else{
				setStyle("-fx-border-color: silver;");
				if(getLength()<1)getChildren().add(txt);
			}
		});
		
	}
	
	// Warto�� tekstowa lub liczba, przedzia� od jakiej warto�ci, przedzia� do jakiej warto�ci
	public DynamicField(boolean text, int from, int to, String labelText){

		// Generowanie Tooltipa informuj�cego o b��dzie
		tip = new CustomTooltip();
		if(text){
			tip.setText("Ilo�� znak�w " + from + "-" + to);
		}else{
			tip.setText("Przedzia� " + from + "-" + to);
		}
		
		// Label widoczny przed dotkni�ciem komponentu
		txt = new Label(labelText);
		txt.setStyle("-fx-text-fill: gray");
		getChildren().add(txt);
		
		// Listener dla zmieniaj�cej si� zawarto�ci tekstu
		textProperty().addListener(e -> {
			// Je�eli tekst lub je�eli liczba
			if(text){
				if(getLength()<from || getLength()>to){ // �le wype�nione pole, czerwona obram�wka i pokazanie tooltipu
					setStyle("-fx-border-color: red");
					if(!tip.isShowing()) tip.show(this);
					correctFilled = false;
				}else{
					setStyle("-fx-border-color: green");
					if(tip.isShowing()) tip.hide();
					correctFilled = true;
				}
			}else{
				if(getLength()>0 && getLength()<=5){ 
					if(Integer.parseInt(getText())<from || Integer.parseInt(getText())>to){
						setStyle("-fx-border-color: red");
						if(!tip.isShowing()) tip.show(this);
						correctFilled = false;
					}else{
						setStyle("-fx-border-color: green");
						if(tip.isShowing()) tip.hide();
						correctFilled = true;
					}
				}else if(getLength()>5){
					setText(getText().substring(0, getLength()-1)); // Ucina znaki dla liczb, �eby nie przekroczy� max warto�ci INTEGER
				}else{
					setStyle("-fx-border-color: rgb(250,228,0)");
					if(tip.isShowing()) tip.hide();
					correctFilled = false;
				}
			}
		});
		
		// Listener dla warto�ci aktywno�ci pola 
		focusedProperty().addListener(e ->{
			if(focusedProperty().get() == true){ // Je�eli aktywne to na ��to
				setStyle("-fx-border-color: rgb(250,228,0)");
				getChildren().remove(txt);
			}else{ // Je�eli nieaktywne to sprawd� poprawno�� i nadaj kolor
				if(correctFilled){
					setStyle("-fx-border-color: green;");
				}else{
					setStyle("-fx-border-color: red;");
				}
				if(tip.isShowing()) tip.hide();
				if(getLength()<1)getChildren().add(txt);
			}
			
			
		});
		
	}
	
	public CustomTooltip getCustomTooltip(){
		return tip;
	}
	
	public boolean isCorrectFilled(){
		return correctFilled;
	}
	
	public void clearAndUnfocused(){
		this.clear();
		tip.hide();
		setStyle("-fx-border-color: silver;");
		getChildren().add(txt);
	}
	
}
