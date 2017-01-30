package calling;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SaveOptions {
	VBox pane = new VBox();
	ToggleGroup radioButton = new ToggleGroup();
	RadioButton rbSave = new RadioButton("Save File Only");
	RadioButton rbDisplay = new RadioButton("Display Only");
	RadioButton rbSaveDisplay = new RadioButton("Save and Display");
	Label saveFileName = new Label("Save File Name: ");
	TextField tfSaveFileName = new TextField();
	GridPane fileName = new GridPane();
	Label defaultName = new Label("");
	
	SaveOptions() {
		fileName.add(saveFileName, 0, 0);
		fileName.add(tfSaveFileName, 1, 0);
		fileName.add(new Label(".cvs"), 2, 0);
		rbSave.setToggleGroup(radioButton);		
		rbDisplay.setToggleGroup(radioButton);	
			rbDisplay.setSelected(true);
		rbSaveDisplay.setToggleGroup(radioButton);
		pane.getChildren().addAll(rbSave,rbDisplay,rbSaveDisplay,fileName,defaultName);
		
	}
	void reset(){
		tfSaveFileName.setText("");
		rbDisplay.setSelected(true);
	}
}
