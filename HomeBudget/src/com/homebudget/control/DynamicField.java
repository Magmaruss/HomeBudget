package com.homebudget.control;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DynamicField extends TextField {
	
	private Label txt;
	private boolean correctFilled;
	private CustomTooltip tip;
	
	
	// Przestarza³y konstruktor
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
	
	// Wartoœæ tekstowa lub liczba, przedzia³ od jakiej wartoœci, przedzia³ do jakiej wartoœci
	public DynamicField(boolean text, int from, int to, String labelText){

		// Generowanie Tooltipa informuj¹cego o b³êdzie
		tip = new CustomTooltip();
		if(text){
			tip.setText("Iloœæ znaków " + from + "-" + to);
		}else{
			tip.setText("Przedzia³ " + from + "-" + to);
		}
		
		// Label widoczny przed dotkniêciem komponentu
		txt = new Label(labelText);
		txt.setStyle("-fx-text-fill: gray");
		getChildren().add(txt);
		
		// Listener dla zmieniaj¹cej siê zawartoœci tekstu
		textProperty().addListener(e -> {
			// Je¿eli tekst lub je¿eli liczba
			if(text){
				if(getLength()<from || getLength()>to){ // le wype³nione pole, czerwona obramówka i pokazanie tooltipu
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
					setText(getText().substring(0, getLength()-1)); // Ucina znaki dla liczb, ¿eby nie przekroczyæ max wartoœci INTEGER
				}else{
					setStyle("-fx-border-color: rgb(250,228,0)");
					if(tip.isShowing()) tip.hide();
					correctFilled = false;
				}
			}
		});
		
		// Listener dla wartoœci aktywnoœci pola 
		focusedProperty().addListener(e ->{
			if(focusedProperty().get() == true){ // Je¿eli aktywne to na ¿ó³to
				setStyle("-fx-border-color: rgb(250,228,0)");
				getChildren().remove(txt);
			}else{ // Je¿eli nieaktywne to sprawdŸ poprawnoœæ i nadaj kolor
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
