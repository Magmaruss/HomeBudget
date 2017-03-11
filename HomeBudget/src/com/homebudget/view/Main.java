package com.homebudget.view;
	
import java.util.Stack;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{
	
	private final static int APP_WIDTH = 960;
	private final static int APP_HEIGHT = 540;
	private static Stack<Node> path = new Stack<Node>(); // Main path stack
	private static GridPane root;
	private LoginRegisterScreen firstScreen;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		root = new GridPane();
		root.setId("MainFrame");
		Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);
		
		// Header of window
		root.getChildren().add(new CustomFrameHeader());
		
		firstScreen = new LoginRegisterScreen();
		path.add(firstScreen);
		root.add(firstScreen, 0, 1);
		
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	/*
	 * Return the project main path
	 */
	public static String getProjectPath(){
		String path = Main.class.getResource("").toString();
		return path.substring(0, path.length()-6);
	}
	
	/*
	 * Return frame width
	 */
	public static int getAppWidth() {
		return APP_WIDTH;
	}
	
	/*
	 * Return frame height
	 */
	public static int getAppHeight() {
		return APP_HEIGHT;
	}
	
	/*
	 * Return bookmark height
	 */
	public static int getBookmarkHeight() {
		return APP_HEIGHT-50;
	}
	
	/*
	 * Jump to next Node / Changing the application scene
	 */
	public static void jump(Node to){
		root.getChildren().remove(path.peek());
		path.push(to);
		root.add(to, 0, 1);
		to.toBack();
	}
	
	/*
	 * Back to the previous Node
	 */
	public static void back(){
		if(getPathSize()>1){
			root.getChildren().remove(path.pop());
			root.add(path.peek(), 0, 1);
			path.peek().toBack();
		}
	}
	
	public static void change(Node to){
		root.getChildren().remove(path.pop());
		path.push(to);
		root.add(to, 0, 1);
		to.toBack();
	}
	
	/*
	 * Return path size
	 */
	public static int getPathSize(){
		return path.size();
	}
	
}
