package calling;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) {
		ButtonPane buttonPane = new ButtonPane();
			
		Text title = new Text("Welcome");
		title.setScaleX(2);
		title.setScaleY(2);
		
		GridPane txt = new GridPane();
		txt.getChildren().add(title);
		BorderPane.setMargin(txt,new Insets(20));
		txt.setAlignment(Pos.CENTER);
		
		//txt.setCenter(title);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(buttonPane);
		BorderPane.setMargin(buttonPane,new Insets(20));
		pane.setTop(txt);
		
		
		Scene scene = new Scene(pane,200,200);
		primaryStage.setTitle("Calling Finder");
		primaryStage.setScene(scene);
		primaryStage.show();

		buttonPane.btTop.setOnAction(e -> buttonPane.topButton());
		buttonPane.btBottom.setOnAction(e -> {buttonPane.bottomButton(); title.setText("Existing Files");});
		buttonPane.btBack.setOnAction(e -> {buttonPane.backButton(); title.setText("Welcome");});
	}
	
	class ButtonPane extends GridPane{
		int btWidth = 160;
		/**starting menu*/
		Button btTop = new Button("Blank Form");			
		Button btBottom = new Button("Use Exisiting File");	
		Button btBack = new Button("Back");
		Label label = new Label("(Need Callings, Calling Status)");
		int option1 = 0; //0 first menu not chosen 1 top button 2 bottom button on menu 1
			
		public ButtonPane(){
			setHgap(5);
			setVgap(5);
			add(btTop, 0, 0);
			add(btBottom, 0, 1);
			add(label, 0, 2);
			setAlignment(Pos.CENTER);
			btTop.setMinWidth(btWidth);
			btBottom.setMinWidth(btWidth);
		}
		void topButton(){
			if((option1 == 0)){
				option1 = 1;
				//load blank form gui
			}
			if((option1 == 2)){
				//load calling list gui
			}
		}
		void bottomButton(){
			if((option1 == 0)){
				option1 = 2;
				btTop.setText("Calling List");
				btBottom.setText("Needs Calling");
				getChildren().remove(label);				
				add(btBack, 0, 2);
			}
			if((option1 == 2)){
				//load need calling gui
			}
		}
		void backButton(){
			option1 = 0;
			btTop.setText("Blank Form");
			btBottom.setText("Use Existing File");
			getChildren().remove(btBack);
			add(label, 0, 2);
		}
			
	}

}
