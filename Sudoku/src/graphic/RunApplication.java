package graphic;

import design.Sudoku;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunApplication extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Sudoku Solver");
		primaryStage.setScene(new Scene(new SudokuGUI(new Sudoku()).getRoot()));
		primaryStage.show();
	}
	
	public void stop(){
		System.exit(0);
	}

}
