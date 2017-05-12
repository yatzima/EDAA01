package phonebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Main extends Application {
	private PhoneBook pb;
	private Stage primaryStage;

	/**
	 * The entry point for the Java program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// launch() do the following:
		// - creates an instance of the Main class
		// - calls Main.init()
		// - create and start the javaFX application thread
		// - waits for the javaFX application to finish (close all windows)
		// the javaFX application thread do:
		// - calls Main.start(Stage s)
		// - runs the event handling loop
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Here you can place any action to be done when the application is
		// opened, i.e. read phone book from file.
		pb = new PhoneBook();
		pb.put("Justin Ma", "0762263995");
		pb.put("Justin Ma", "031439822");
		pb.put("Petter Herzog", "0");

		FileChooser fc = new FileChooser();
		fc.setTitle("Öppna");
		File selectedFile = fc.showOpenDialog(primaryStage);
		if (selectedFile != null) {
			read(selectedFile);
		}

		PhoneBookGUI gui = new PhoneBookGUI(pb);
		Scene scene = new Scene(gui.getRoot());
		primaryStage.setTitle("PhoneBook");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Öppna");
		File selectedFile = fc.showOpenDialog(primaryStage);
		
		if (selectedFile != null) {
			save(selectedFile);
		}

		System.exit(0);
	}

	private void save(File selectedFile) {

		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(selectedFile));
			out.writeObject(pb);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void read(File selectedFile) {

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(selectedFile));
			pb = (PhoneBook) in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
