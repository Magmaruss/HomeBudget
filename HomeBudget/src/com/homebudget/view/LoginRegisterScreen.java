package com.homebudget.view;

import com.homebudget.control.CustomTooltip;
import com.homebudget.control.DynamicField;
import com.homebudget.control.DynamicPassword;
import com.homebudget.session.UserSession;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class LoginRegisterScreen extends StackPane implements EventHandler<ActionEvent> {
	
	private TabPane loginRegister;
	private Tab loginTab, registerTab;
	private GridPane loginContent, registerContent;
	
	// Fields for Login
	private DynamicField usernameField;
	private DynamicPassword passwordField;
	private Button loginButton;
	
	// Fields for Register
	private DynamicField usernameRegister;
	private DynamicPassword passwordRegister, passwordConfirmRegister;
	private Button registerButton;
	
	public LoginRegisterScreen(){
		this.setPrefSize(Main.getAppWidth(), Main.getAppHeight());
		this.setId("LoginRegisterBackground");
		
		loginRegister = new TabPane();
		loginRegister.setMaxSize(400, 300);
		loginRegister.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		loginRegister.setId("LoginTabPane");
		
		
		/*
		 *  LOGIN SECTION
		 */
		loginContent = new GridPane();
		loginContent.setAlignment(Pos.CENTER);
		loginContent.setVgap(5);
		
		usernameField = new DynamicField(true, 3, 20, "Nazwa u¿ytkownika");
		usernameField.setPrefSize(300, 50);
		loginContent.add(usernameField, 0, 0);
		
		passwordField = new DynamicPassword(true, 8, 64, "Has³o");
		passwordField.setPrefSize(300, 50);
		passwordField.setOnAction(this);
		loginContent.add(passwordField, 0, 1);
		
		loginButton = new Button("Zaloguj");
		loginButton.setPrefSize(300, 50);
		loginButton.setOnAction(this);
		loginContent.add(loginButton, 0, 2);
		
		/*
		 * REGISTER SECTION
		 */
		
		registerContent = new GridPane();
		registerContent.setAlignment(Pos.CENTER);
		registerContent.setVgap(5);
		
		usernameRegister = new DynamicField(true, 3, 20, "Nazwa u¿ytkownika");
		usernameRegister.setPrefSize(300, 50);
		registerContent.add(usernameRegister, 0, 0);
		
		passwordRegister = new DynamicPassword(true, 8, 64, "Has³o");
		passwordRegister.setPrefSize(300, 50);
		registerContent.add(passwordRegister, 0, 1);
		
		passwordConfirmRegister = new DynamicPassword(true, 8, 64, "PotwierdŸ has³o");
		passwordConfirmRegister.setPrefSize(300, 50);
		registerContent.add(passwordConfirmRegister, 0, 2);
		
		registerButton = new Button("Zarejestruj");
		registerButton.setPrefSize(300, 50);
		registerButton.setOnAction(this);
		registerContent.add(registerButton, 0, 3);
		
		
		/*
		 * !!! SECTIONS END !!!
		 */
		
		loginTab = new Tab("Zaloguj", loginContent);
		registerTab = new Tab("Utwórz konto", registerContent);

		loginRegister.getTabs().addAll(loginTab, registerTab);
		this.getChildren().add(loginRegister);
		
	}

	@Override
	public void handle(ActionEvent ae) {
		
		
		Object source = ae.getSource();
		
		if(source == loginButton || source == passwordField){
			CustomTooltip t = new CustomTooltip();
			if(usernameField.isCorrectFilled() && passwordField.isCorrectFilled()){
				
				int loginStatus = UserSession.login(usernameField.getText(), passwordField.getText());
				if(loginStatus == 0){
					t.hide();
					UserSession.updateLastLogin();
					//Przejœcie do aplikacji
					Main.change(new BudgetMainBookmark());
					
				}else if(loginStatus == -1){
					t.setText("Niepoprawna nazwa u¿ytkownika lub has³o");
					t.show(loginButton);
				}else if(loginStatus == -2){
					t.setText("Brak po³¹czenia z baz¹ - sprawdŸ po³¹czenie z internetem");
					t.show(loginButton);
				}else if(loginStatus == -3){
					t.setText("B³¹d aplikacji - spróbuj uruchomiæ ponownie aplikacjê");
					t.show(loginButton);
				}
				
			}else{
				
				t.setText("Niepoprawnie wype³nione pola");
				t.show(loginButton);
			}
		}
		
		if(source == registerButton){
			CustomTooltip t = new CustomTooltip();
			if(usernameRegister.isCorrectFilled() && passwordRegister.isCorrectFilled() && passwordConfirmRegister.isCorrectFilled()){
				if(passwordRegister.getText().equals(passwordConfirmRegister.getText())){
					t.hide();
					
					int registerStatus = UserSession.register(usernameRegister.getText(), passwordRegister.getText());
					if(registerStatus == 0){
						t.setText("Utworzono pomyœlnie - mo¿esz siê teraz zalogowaæ");
						usernameRegister.clearAndUnfocused();
						passwordRegister.clearAndUnfocused();
						passwordConfirmRegister.clearAndUnfocused();
						t.show(registerButton);
					}else if(registerStatus == -1){
						t.setText("Taki u¿ytkownik ju¿ istnieje");
						t.show(registerButton);
					}else if(registerStatus == -2){
						t.setText("Brak po³¹czenia z baz¹ - sprawdŸ po³¹czenie z internetem");
						t.show(registerButton);
					}else if(registerStatus == -3){
						t.setText("B³¹d aplikacji - spróbuj uruchomiæ ponownie aplikacjê");
						t.show(registerButton);
					}
					
				}else{
					t.setText("Has³a nie s¹ identyczne");
					t.show(registerButton);
				}
				
			}else{
				
				t.setText("Niepoprawnie wype³nione pola");
				t.show(registerButton);
			}
		}
		
	}

}
