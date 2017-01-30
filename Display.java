package calling;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class Display{
	VBox pane = new VBox();
	Label status = new Label();
	//text area
	TextArea myDisplay = new TextArea("");
	
	Display(Pane paneIn){
		pane.getChildren().add(paneIn);
		Line line = new Line(0,5,2000,5);
		pane.getChildren().add(line);
		pane.getChildren().add(status);
		pane.getChildren().add(myDisplay);
		myDisplay.setEditable(false);
		VBox.setMargin(myDisplay, new Insets(25,0,0,0));
	}
 
	//change txt area
	void setTxtArea(String[] in){
		String d = new String();
		for(int i = 0; i < in.length; i++){
			if(in[i] != null)
				d += in[i] + "\n";
			else {
				d += "Nothing Else";
				break;
			}
		}
		myDisplay.setText(d);
	}
	
}