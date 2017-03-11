package com.homebudget.view;

import com.homebudget.session.UserSession;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BudgetMainBookmark extends GridPane implements EventHandler<ActionEvent> {
	
	private Label[] userInfo = new Label [2];
	private BorderPane userStatus;
	private HBox userInfoBox;
	private VBox navigationBox;
	private Button logoutButton;
	private Button[] bMenu = new Button [4];
	private int buttonActive = 0;
	
	public BudgetMainBookmark(){
		
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.setPrefSize(Main.getAppWidth(), Main.getBookmarkHeight());
		
		/*
		 *  Status u¿ytkownika
		 */
		
		userStatus = new BorderPane();
		userStatus.setPrefSize(Main.getAppWidth(), (Main.getAppHeight()-Main.getBookmarkHeight())/4*3);
		userStatus.setId("UserStatusBar");
		
		// LEFT
		userInfo[0] = new Label("Witaj " + UserSession.getUsername() + "!");
		userInfo[0].setAlignment(Pos.CENTER_LEFT);
		userInfo[0].setMaxHeight(Double.MAX_VALUE);
		userStatus.setLeft(userInfo[0]);
		
		// RIGHT
		userInfo[1] = new Label("Ostatnie Twoje logowanie: " + UserSession.getLastLogin());
		userInfo[1].setStyle("-fx-font-size: 14px");
		
		logoutButton = new Button("Wyloguj");
		logoutButton.setPrefHeight(userStatus.getLayoutBounds().getMaxY());
		logoutButton.setStyle("-fx-font-size: 14px;");
		logoutButton.setOnAction(this);
		
		
		
		userInfoBox = new HBox(10);
		userInfoBox.setMaxHeight(Double.MAX_VALUE);
		userInfoBox.setAlignment(Pos.CENTER_RIGHT);
		userInfoBox.getChildren().addAll(userInfo[1], logoutButton);
		
		
		userStatus.setRight(userInfoBox);
		
		this.add(userStatus, 0, 0, 4, 1);
		
		/*
		 * Nawigacja po zak³adkach
		 */
		navigationBox = new VBox();
		
		double buttonHeight = (Main.getBookmarkHeight()-userStatus.getBoundsInLocal().getMaxY())/bMenu.length;
		
		String[] buttonImages = {"Summary.png","Plan.png","Journal.png","Irregular.png"};
		String[] buttonNames = {"Podsumowanie","Planowanie","Dziennik","Wydatki nieregularne"};
		
		for(int i=0; i<4; i++){
			bMenu[i] = new Button(buttonNames[i]);
			bMenu[i].setPrefSize(200, buttonHeight);
			bMenu[i].setContentDisplay(ContentDisplay.TOP);
			bMenu[i].setOnAction(this);
			bMenu[i].setGraphic(new ImageView(new Image(Main.getProjectPath() + "/images/" + buttonImages[i])));
			bMenu[i].getStyleClass().add("navigationButton");
			navigationBox.getChildren().add(bMenu[i]);
		}
		
		this.add(navigationBox, 0, 1, 1, 4);
		
		this.add(new SummaryBookmark(), 1, 1, 3, 4);

	}

	@Override
	public void handle(ActionEvent ae) {
		
		Object source = ae.getSource();
		
		if(source == logoutButton){
			UserSession.logout();
			Main.change(new LoginRegisterScreen());
		}
		
		int lastElement = this.getChildren().size()-1;
		
		if(source == bMenu[0]){
			if(buttonActive != 0){
				this.getChildren().remove(lastElement);
				this.add(new SummaryBookmark(), 1, 1, 3, 4);
				buttonActive = 0;
			}
		}
		
		if(source == bMenu[1]){
			if(buttonActive != 1){
				this.getChildren().remove(lastElement);
				this.add(new PlanBookmark(), 1, 1, 3, 4);
				buttonActive = 1;
			}
		}
		
		if(source == bMenu[2]){
			if(buttonActive != 2){
				this.getChildren().remove(lastElement);
				this.add(new RealBookmark(), 1, 1, 3, 4);
				buttonActive = 2;
			}
		}
		
		if(source == bMenu[3]){
			if(buttonActive != 3){
				this.getChildren().remove(lastElement);
				this.add(new IrregularBookmark(), 1, 1, 3, 4);
				buttonActive = 3;
			}
		}
		
	}
	
}
