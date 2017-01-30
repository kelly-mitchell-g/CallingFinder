package calling;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BlankForm extends Application {
	Stage primaryStage = new Stage();
	BorderPane pane = new BorderPane();
	Display displayPane = new Display(pane);
	BlankCalling blank = new BlankCalling();

	TextField tfEq = new TextField();
	TextField tfRs = new TextField();
	TextField tfFhe = new TextField();
	TextField tfIndexers = new TextField();
	TextField tfWm = new TextField();
	TextField tfTotal = new TextField();

	Label lbTotalC = new Label("     Total Number of Requested Callings: ");
	Label lbTotalCNum = new Label(" ");
	Text lbOverLoadError = new Text("");

	TextField tfSaveFileName = new TextField();

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		Text title = new Text("Blank Calling List");
		title.setScaleX(2);
		title.setScaleY(2);

		// center area
		GridPane mainArea = new GridPane();
		mainArea.setAlignment(Pos.CENTER);

		// leftpane textfield modifiers
		tfEq.setMaxWidth(40);
		tfEq.setText("2");
		tfRs.setMaxWidth(40);
		tfRs.setText("2");
		tfFhe.setMaxWidth(40);
		tfFhe.setText("12");
		tfIndexers.setMaxWidth(40);
		tfWm.setMaxWidth(40);
		tfTotal.setMaxWidth(40);

		// left pane
		GridPane left = new GridPane();
		Label numOf = new Label("Number of:");
		Label lbEq = new Label("Elders Quorms: (11 Callings Each)");
		Label lbRs = new Label("Releif Societies: (19 Callings Each)");
		Label lbFhe = new Label("Home Evening Groups: (2 Callings Each)");
		Label lbInd = new Label("Indexers: ");
		Label lbWm = new Label("Ward Missionaries: ");
		Label lbTotal = new Label("Desired Number of Callings: ");

		Text notReq = new Text("(Fill in NONE, 1, or 2 of the Following)");
		notReq.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		
		notReq.setTextAlignment(TextAlignment.CENTER);
		// add everything to the pane
		left.add(numOf, 0, 0);
		left.add(lbEq, 0, 1);
		left.add(tfEq, 1, 1);
		left.add(lbRs, 0, 2);
		left.add(tfRs, 1, 2);
		left.add(lbFhe, 0, 3);
		left.add(tfFhe, 1, 3);
		left.add(notReq, 0, 4);
		left.add(lbInd, 0, 5);
		left.add(tfIndexers, 1, 5);
		left.add(lbWm, 0, 6);
		left.add(tfWm, 1, 6);
		left.add(lbTotal, 0, 7);
		left.add(tfTotal, 1, 7);
		left.add(lbTotalC, 0, 8);
		left.add(lbTotalCNum, 1, 8);
		// add to main pane
		mainArea.add(left, 0, 0);
		GridPane.setMargin(left, new Insets(10));

		// right pane
		GridPane rightTop = new GridPane();
		Label lbSaveFileName = new Label("Save File Name: ");
		rightTop.add(lbSaveFileName, 0, 0);
		rightTop.add(tfSaveFileName, 0, 1);
		rightTop.add(new Label(".csv"), 1, 1);
		Label defaults = new Label(
				"\n\n\nDefault 200 callings,\nHalf of remaining callings will be Indexers\nand half will be Ward Missionaries");
		rightTop.add(defaults, 0, 2);
		rightTop.add(lbOverLoadError, 0, 3);
		lbOverLoadError.setFill(Color.RED);
		//lbOverLoadError.setScaleX(1.25);
		//lbOverLoadError.setScaleY(1.25);
		lbOverLoadError.setTranslateX(10);
		lbOverLoadError.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

		VBox right = new VBox();
		right.getChildren().add(rightTop);

		// add to main pane
		mainArea.add(new Line(0, 50, 0, 200), 3, 0);
		mainArea.add(right, 4, 0);
		GridPane.setMargin(right, new Insets(10));

		// button pane on bottom
		HBox buttonPane = new HBox(10);
		Button sum = new Button("Sum");
		Button preview = new Button("Preview");
		Button save = new Button("Save");
		Button back = new Button("Back");
		Button reset = new Button("Reset");
		buttonPane.getChildren().addAll(sum, preview, save, reset, back);
		buttonPane.setAlignment(Pos.CENTER);

		// overall
		pane.setCenter(mainArea);
		pane.setTop(title);
		BorderPane.setMargin(title, new Insets(10));
		BorderPane.setAlignment(title, Pos.CENTER);
		pane.setBottom(buttonPane);

		Scene scene = new Scene(displayPane.pane, 540, 500);
		primaryStage.setTitle("Calling Finder - Blank Calling List");
		primaryStage.setScene(scene);

		// handlers
		sum.setOnAction(e -> sumHandler());
		preview.setOnAction(e -> previewHandler());
		reset.setOnAction(e -> resetHandler());
		save.setOnAction(e -> saveHandler());
		back.setOnAction(e -> backHandler());
	}

	// handlers
	void sumHandler() {
		if (inputedFeilds() <= 2) {
			setFile();
			setTotal();
			lbOverLoadError.setText("");
		} else
			lbOverLoadError.setText("\nToo Many Inputs");
	}

	void previewHandler() {
		if (inputedFeilds() <= 2) {
			setFile();
			displayPane.setTxtArea(blank.getMasterFile());
			setTotal();
			lbOverLoadError.setText("");
		} else
			lbOverLoadError.setText("\nToo Many Inputs");
	}

	void saveHandler() {
		if(inputedFeilds() <= 2){
		MyFile fileOut = new MyFile();
		int sError = 1;
		fileOut.loadFile(blank.getMasterFile());
		sError = fileOut.saveFile(tfSaveFileName.getText() + ".csv");
		// sError = work.requested.saveFile(save.tfSaveFileName.getText() +
		// ".csv");
		if (sError == -1) {
			displayPane.status.setText("Save Error");
			displayPane.status.setTextFill(Color.RED);
		} else if (sError == 0) {
			displayPane.status.setText("Save Complete");
			displayPane.status.setTextFill(Color.BLACK);
			setTotal();
		}
		setFile();
			lbOverLoadError.setText("");
		} else
			lbOverLoadError.setText("\nToo Many Inputs");

	}

	void resetHandler() {
		tfEq.setText("2");
		tfRs.setText("2");
		tfFhe.setText("12");
		String[] blanker = { " " };
		displayPane.setTxtArea(blanker);
		tfIndexers.setText("");
		tfWm.setText("");
		tfTotal.setText("");
		tfSaveFileName.setText("");
		lbTotalCNum.setText("");
		lbOverLoadError.setText("");
	}

	void backHandler() {
		GUI gui = new GUI();
		try {
			gui.start(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void setFile() {
		blank.changeEQ(Integer.parseInt(tfEq.getText()));
		blank.changeRS(Integer.parseInt(tfRs.getText()));
		blank.changeHE(Integer.parseInt(tfFhe.getText()));
		if (tfWm.getText().trim().length() > 0)
			if (Integer.parseInt(tfWm.getText()) > 0)
				blank.changeWM(Integer.parseInt(tfWm.getText()));
		if (tfIndexers.getText().trim().length() > 0)
			if (Integer.parseInt(tfIndexers.getText()) > 0)
				blank.changeIDX(Integer.parseInt(tfIndexers.getText()));
		if (tfTotal.getText().trim().length() > 0)
			if (Integer.parseInt(tfTotal.getText()) > 0)
				blank.changeTotalNumCall(Integer.parseInt(tfTotal.getText()));
		blank.filePrep();

	}

	void setTotal() {
		int total = blank.getSubTotal();
		if (!(tfTotal.getText().trim().length() > 0)) {
			if (tfIndexers.getText().trim().length() > 0)
				total += Integer.parseInt(tfIndexers.getText());
			if (tfWm.getText().trim().length() > 0)
				total += Integer.parseInt(tfWm.getText());
		} else if (tfTotal.getText().trim().length() > 0)
			total = Integer.parseInt(tfTotal.getText());
		lbTotalCNum.setText(Integer.toString(total));
	}

	int inputedFeilds() {
		int total = 0;
		if (tfIndexers.getText().trim().length() > 0)
			total += 1;
		if (tfWm.getText().trim().length() > 0)
			total += 1;
		if (tfTotal.getText().trim().length() > 0)
			total += 1;
		return total;
	}
}