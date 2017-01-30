package calling;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CallingList extends Application {
	Stage primaryStage = new Stage();
	RadioButton rbEmpty = new RadioButton("Empty Callings");
	RadioButton rbCalled = new RadioButton("Need Called");
	RadioButton rbSustain = new RadioButton("Need Sustained");
	RadioButton rbSetApart = new RadioButton("Need Set Apart");
	RadioButton rbOnline = new RadioButton("Need Entered Online");
	RadioButton rbReleased = new RadioButton("Need Released (coming soon)");
	
	SaveOptions save = new SaveOptions();
	TextField loadFileName = new TextField();
	TextField rosterFileName = new TextField();
	GridPane subLeft = new GridPane();
	
	Label ERROR = new Label("");
	
	BorderPane pane = new BorderPane();
	Display displayPane = new Display(pane);
	
	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		Text title = new Text("Calling List");
		title.setScaleX(2);
		title.setScaleY(2);
		
		//center area
		GridPane mainArea = new GridPane();
		mainArea.setAlignment(Pos.CENTER);
		
		//left pane
		VBox left = new VBox();
		//radio buttons
		ToggleGroup radioButtons = new ToggleGroup();
		
		rbEmpty.setToggleGroup(radioButtons);
		rbCalled.setToggleGroup(radioButtons);
		rbCalled.setSelected(true);
		rbSustain.setToggleGroup(radioButtons);
		rbSetApart.setToggleGroup(radioButtons);
		rbOnline.setToggleGroup(radioButtons);
		rbReleased.setToggleGroup(radioButtons);
		
		
		Label lbRosterFileName = new Label("Load Roster File Name: ");
		subLeft.add(lbRosterFileName, 0, 0);
		subLeft.add(rosterFileName, 0, 1);
		subLeft.add(new Label(".csv"), 1, 1);
		subLeft.setVisible(false);
		
		left.getChildren().addAll(rbEmpty,rbCalled,rbSustain,rbSetApart,rbOnline,rbReleased,subLeft);
		
		//add to main pane
		mainArea.add(left, 0, 0);
		GridPane.setMargin(left, new Insets(10));
		
		//create bottom right pane
		VBox rightBottom = new VBox();		
		rightBottom.getChildren().addAll(save.pane,ERROR);
		ERROR.setTextFill(Color.RED);
		ERROR.setScaleX(1.5);
		ERROR.setScaleY(1.5);
		ERROR.setAlignment(Pos.CENTER);
		ERROR.setTranslateX(20);
		
		//add to main pane
		//mainArea.add(mid, 2, 0);
		//mainArea.add(new Line(0,50,0,200), 1, 0);
		//mainArea.setHgap(5);
		
		//right pane
		GridPane rightTop = new GridPane();		
		Label lbLoadFileName = new Label("Load File Name: ");
		rightTop.add(lbLoadFileName, 0, 0);
		rightTop.add(loadFileName, 1, 0);
		rightTop.add(new Label(".csv"), 2, 0);		
		
		VBox right = new VBox();
		right.getChildren().add(rightTop);
		right.getChildren().add(rightBottom);
		//add to main pane
		mainArea.add(new Line(0,50,0,200), 3, 0);
		mainArea.add(right, 4, 0);
		GridPane.setMargin(right, new Insets(10));
		
		//button pane on bottom
		HBox buttonPane = new HBox(10);
		Button btGo = new Button("GO");
		Button btBack = new Button("Back");
		Button btReset = new Button("Reset");
		buttonPane.getChildren().addAll(btGo,btReset,btBack);
		buttonPane.setAlignment(Pos.CENTER);
		
		//overall
		pane.setCenter(mainArea);
		pane.setTop(title);
		BorderPane.setMargin(title, new Insets(10));
		BorderPane.setAlignment(title, Pos.CENTER);
		pane.setBottom(buttonPane);
		
		Scene scene = new Scene(displayPane.pane,500,400);
		primaryStage.setTitle("Calling Finder - Calling List");
		primaryStage.setScene(scene);

		//handlers
		btGo.setOnAction(e -> goHandler());
		btBack.setOnAction(e -> backHandler());
		btReset.setOnAction(e -> resetHandler());
		rbReleased.setOnAction(e -> subLeft.setVisible(true));//display roster file name
		rbEmpty.setOnAction(e -> subLeft.setVisible(false));
		rbCalled.setOnAction(e -> subLeft.setVisible(false));;
		rbSustain.setOnAction(e -> subLeft.setVisible(false));
		rbSetApart.setOnAction(e -> subLeft.setVisible(false));
		rbOnline.setOnAction(e -> subLeft.setVisible(false));
	}
	
	void goHandler(){
		int sError = 1;
		String inFileName = loadFileName.getText();
		CallingListBack work = new CallingListBack(inFileName);
		char choice = ' ';
		if(rbEmpty.isSelected())
			choice = 'e';
		else if (rbCalled.isSelected())
			choice = 'c';
		else if (rbSustain.isSelected())
			choice = 's';
		else if (rbSetApart.isSelected())
			choice = 't';
		else if (rbOnline.isSelected())
			choice = 'o';
		else if (rbReleased.isSelected())
			choice = 'r';
		switch (choice) {
		case 'e'://empty callings
			work.finder(work.inFile.file,1);
			break;
		case 'c':// find those who need to be called
			work.finder(work.inFile.file,2);
			break;
		case 's'://sustained
			work.finder(work.inFile.file, 3);
			break;
		case 't':// setapart
			work.finder(work.inFile.file, 4);
			break;
		case 'o':// online
			work.finder(work.inFile.file, 5);
			break;
		}
		char choice1 = ' ';
		if (save.rbDisplay.isSelected())
			choice1 = 'd';
		else if (save.rbSave.isSelected())
			choice1 = 's';
		else if (save.rbSaveDisplay.isSelected())
			choice1 = 'b';
		switch(choice1){
		case 'b':
			//String outFile = ;
			sError = work.requested.saveFile(save.tfSaveFileName.getText() + ".csv");
		case 'd'://display
			displayPane.setTxtArea(work.requested.getFile());
			break;
		case 's'://save
			sError = work.requested.saveFile(save.tfSaveFileName.getText() + ".csv");
			break;
		}
		if(work.returnError() == -1){
			ERROR.setText("File Read Error");
		}
		else
			ERROR.setText(" ");
		if(sError == -1){
			displayPane.status.setText("Save Error");
			displayPane.status.setTextFill(Color.RED);
		}
		else if(sError == 0){
			displayPane.status.setText("Save Complete");
			displayPane.status.setTextFill(Color.BLACK);
		}
	}
	void backHandler(){
		GUI gui = new GUI();
		try {
			gui.start(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void resetHandler(){
		rbCalled.setSelected(true);
		save.reset();
		loadFileName.setText("");
		String[] blanker = {" "};
		displayPane.setTxtArea(blanker);
		ERROR.setText(" ");
		displayPane.status.setText(" ");
		rosterFileName.setText("");
		subLeft.setVisible(false);
	}
}
