package phonebook;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class PhoneBookGUI {
	private PhoneBook pb;
	private BorderPane root;
	private TextArea textArea;

	public PhoneBookGUI(PhoneBook pb) {
		this.pb = pb;

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setPrefColumnCount(50);
		textArea.setPrefRowCount(20);

		HBox control = makeControl();

		root = new BorderPane();
		root.setTop(makeMenu());
		root.setBottom(control);
		root.setCenter(textArea);

	}

	public Parent getRoot() {
		return root;
	}

	private HBox makeControl() {
		HBox hbox = new HBox();
		Button quit = new Button("Quit");

		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);

		quit.setPrefSize(100, 20);
		quit.setOnAction(e -> quit());

		hbox.getChildren().addAll(quit);
		return hbox;
	}

	private MenuBar makeMenu() {
		final Menu menuEdit = new Menu("Edit");
		final MenuItem menuAdd = new MenuItem("Add");
		menuAdd.setOnAction(e -> add());
		final MenuItem menuRemovePerson = new MenuItem("Remove Person");
		menuRemovePerson.setOnAction(e -> removePerson());
		final MenuItem menuRemoveNumber = new MenuItem("Remove Number");
		menuRemoveNumber.setOnAction(e -> removeNumber());
		menuEdit.getItems().addAll(menuAdd, menuRemovePerson, menuRemoveNumber);

		final Menu menuFind = new Menu("Find");
		final MenuItem findName = new MenuItem("Find Name(s)");
		findName.setOnAction(e -> findNames());
		final MenuItem findNumber = new MenuItem("Find Number(s)");
		findNumber.setOnAction(e -> findNumbers());
		menuFind.getItems().addAll(findName, findNumber);

		final Menu menuView = new Menu("View");
		final MenuItem menuShowAll = new MenuItem("Show All");
		menuShowAll.setOnAction(e -> viewAll());
		menuView.getItems().addAll(menuShowAll);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuEdit, menuFind, menuView);
		// menuBar.setUseSystemMenuBar(true); // if you want operating system
		// rendered menus, uncomment this line
		return menuBar;
	}

	private void add() {
		String[] labs = new String[2];
		labs[0] = "Namn";
		labs[1] = "Telefonnummer";

		Optional<String[]> tid = twoInputsDialog("Lägg till en kontakt", "Hallåå", labs);
		if (tid.isPresent()) {
			String[] inputs = tid.get();
			pb.put(inputs[0], inputs[1]);
		}
	}

	private void removePerson() {
		Optional<String> oid = oneInputDialog("Ta bort en kontakt", "Hallååå", "Namn");
		if (oid.isPresent()) {
			String input = oid.get();
			pb.remove(input);
		}
	}

	private void removeNumber() {
		String[] labs = new String[2];
		labs[0] = "Namn";
		labs[1] = "Telefonnummer";
		Optional<String[]> tid = twoInputsDialog("Lägg till en kontakt", "Hallåå", labs);
		if (tid.isPresent()) {
			String[] inputs = tid.get();
			pb.removeNumber(inputs[0], inputs[1]);
		}
	}

	private void findNumbers() {
		Optional<String> oid = oneInputDialog("Visa alla kontakter", "Hallååå", "Namn");
		if (oid.isPresent()) {
			textArea.clear();
			textArea.appendText(pb.findNumber(oid.get()).toString());
		}
	}

	private void findNames() {
		Optional<String> oid = oneInputDialog("Visa alla kontakter", "Hallååå", "Telefonnummer");
		if (oid.isPresent()) {
			textArea.clear();
			textArea.appendText(pb.findNames(oid.get()).toString());
		} 
	}

	private void viewAll() {
		textArea.clear();
		textArea.appendText(pb.toString());
	}

	private void quit() {
		Platform.exit();
	}

	private Optional<String> oneInputDialog(String title, String headerText, String label) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setResizable(true);
		return dialog.showAndWait();
	}

	private Optional<String[]> twoInputsDialog(String title, String headerText, String[] labels) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setResizable(true);
		Label label1 = new Label(labels[0] + ':');
		Label label2 = new Label(labels[1] + ':');
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf1, 2, 1);
		grid.add(label2, 1, 2);
		grid.add(tf2, 2, 2);
		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeCancel, buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, String>() {
			@Override
			public String call(ButtonType b) {
				String inputs = null;
				if (b == buttonTypeOk) {
					inputs = tf1.getText() + ":" + tf2.getText();

				}
				return inputs;
			}
		});
		tf1.requestFocus();

		Optional<String> result = dialog.showAndWait();
		String[] input = null;
		if (result.isPresent()) {
			input = result.get().split(":");
		}
		return Optional.ofNullable(input);
	}
}
