package com.homebudget.view;

import com.homebudget.control.TitlePane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlanBookmark extends StackPane {
	
	private ChoiceBox<String> dataBox, categoryBox, subcategoryBox, dataTwoBox;
	private TextField amount;
	private Button confirm;
	
	public PlanBookmark(){
		this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		////Zak³adka Wydatki oraz Zak³adka Przychody////
		
			TabPane tabPane = new TabPane();
			tabPane.getStyleClass().add("tabPaneInPlanBookmark");
	        Label zak = new Label("Zak³adka PLANOWANIE w przychodach");
	       
	        Tab expensesPanel = new Tab();	//wydatki
	        Tab revenuesPanel = new Tab();	//przychody
	        
			revenuesPanel.setText("Przychody");
			revenuesPanel.setClosable(false);
    		revenuesPanel.setContent(zak);
    		
			tabPane.getTabs().add(expensesPanel);//tab1
			tabPane.getTabs().add(revenuesPanel);//tab2			
			
			//Etykiety nazw  dla ChoiceBox
			HBox layoutLabel = new HBox(25);
			layoutLabel.setPadding(new Insets(5, 0, 0, 65));

	        String[] NameForChoiceBox =  {"Data","Kategorie","Podkategorie","Kwota"};
	       
	        for(int n=0; n<=3; n++){
	        	
	            	Label lN = new Label(NameForChoiceBox[n]);
	            	lN.setPrefSize(100, 30);
	            	lN.setAlignment(Pos.CENTER);
	            	lN.setStyle("-fx-text-fill: gray; -fx-font-size: 12px;");
	            	layoutLabel.getChildren().add(lN);
				
	        }
			
			HBox layoutChoiceBox = new HBox(10);
			layoutChoiceBox.setPadding(new Insets(0, 15, 0, 15));
	     

			dataBox = new ChoiceBox<String>();
			dataBox.setPrefSize(115,30);
			
			categoryBox = new ChoiceBox<String>();
			categoryBox.setPrefSize(115,30);
			
			subcategoryBox = new ChoiceBox<String>();
			subcategoryBox.setPrefSize(115,30);
			
			amount = new TextField();
			amount.setPrefSize(115,30);
			
			confirm= new Button("Zatwierdz");
			confirm.setPrefSize(115,30);
			
			layoutChoiceBox.getChildren().addAll(dataBox,categoryBox,subcategoryBox,amount, confirm);
			layoutChoiceBox.setAlignment(Pos.CENTER);
			
	        TitlePane p1 = new TitlePane("Zaplanuj");
	        p1.setPrefHeight(120);
	        p1.getChildren().addAll(layoutLabel,layoutChoiceBox);
	        
	        // dodajemy datê i drzewo
	        HBox layoutChoiceAndTree = new HBox(20);
	        layoutChoiceAndTree.setPadding(new Insets(5, 0, 0, 15));
	        
	        dataTwoBox = new ChoiceBox<String>();
	        dataTwoBox.setPrefSize(115,30);
	        
	        //treeView
	        String catJava = new String("Java");
	        String catJSP = new String("Jsp");
	        String catSpring = new String("Spring");
	       
	        TreeItem<String> rootItem = new TreeItem<String>(catJava);
	        rootItem.setExpanded(true);
	 
	        // JSP Item
	        TreeItem<String> itemJSP = new TreeItem<String>(catJSP);
	 
	        // Spring Item
	        TreeItem<String> itemSpring = new TreeItem<String>(catSpring);
	 
	        // Add to Root
	        rootItem.getChildren().addAll(itemJSP, itemSpring);
	 
	        TreeView<String> tree = new TreeView<String>(rootItem);
	        tree.setPrefWidth(590);
	        
	        layoutChoiceAndTree.getChildren().addAll(dataTwoBox, tree); //dodana data i tree
	        
	        TitlePane p2 = new TitlePane("Pozycje zaplanowane");
	        p2.setPrefHeight(282);
	        p2.getChildren().addAll(layoutChoiceAndTree);// dodanie do titlePane daty i tree
	        
	        VBox layoutTitleP = new VBox();//titlePane miesci siê layoucie VBox
	        
	        layoutTitleP.getChildren().add(p1);
	        layoutTitleP.getChildren().add(p2);

	        expensesPanel.setText("Wydatki");
	        expensesPanel.setClosable(false);
			expensesPanel.setContent(layoutTitleP);
			
			this.getChildren().add(tabPane);
	}
	
}
