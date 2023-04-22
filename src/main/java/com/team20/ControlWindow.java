package com.team20;

import java.io.IOException;

import edu.wpi.first.cscore.CameraServerJNI;
import edu.wpi.first.math.WPIMathJNI;
import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTablesJNI;
import edu.wpi.first.util.CombinedRuntimeLoader;
import edu.wpi.first.util.WPIUtilJNI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControlWindow extends Application implements EventHandler<KeyEvent> {
	Button numpad1Btn = new Button("NUMPAD1");
	Button numpad2Btn = new Button("NUMPAD2");
	Button numpad3Btn = new Button("NUMPAD3");
	Button numpad4Btn = new Button("NUMPAD4");
	Button numpad5Btn = new Button("NUMPAD5");
	Button numpad6Btn = new Button("NUMPAD6");
	Button numpad7Btn = new Button("NUMPAD7");
	Button numpad8Btn = new Button("NUMPAD8");
	Button numpad9Btn = new Button("NUMPAD9");
	Button minusBtn = new Button("-");
	Button plusBtn = new Button("+");
	static BooleanPublisher numpad1Publisher;
	static BooleanPublisher numpad2Publisher;
	static BooleanPublisher numpad3Publisher;
	static BooleanPublisher numpad4Publisher;
	static BooleanPublisher numpad5Publisher;
	static BooleanPublisher numpad6Publisher;
	static BooleanPublisher numpad7Publisher;
	static BooleanPublisher numpad8Publisher;
	static BooleanPublisher numpad9Publisher;
	static BooleanPublisher minusPublisher;
	static BooleanPublisher plusPublisher;

	@Override
	public void start(Stage stage) {
		// Size all the buttons
		numpad1Btn.setMinSize(100, 100);
		numpad2Btn.setMinSize(100, 100);
		numpad3Btn.setMinSize(100, 100);
		numpad4Btn.setMinSize(100, 100);
		numpad5Btn.setMinSize(100, 100);
		numpad6Btn.setMinSize(100, 100);
		numpad7Btn.setMinSize(100, 100);
		numpad8Btn.setMinSize(100, 100);
		numpad9Btn.setMinSize(100, 100);
		minusBtn.setMinSize(100, 100);
		plusBtn.setMinSize(100, 100);
		// Create the layout
		GridPane grid = new GridPane();
		// Gaps of 30 pixels between buttons
		grid.setHgap(30);
		grid.setVgap(30);
		// Center everything
		grid.setAlignment(Pos.CENTER);
		// Add all the buttons
		grid.add(numpad1Btn, 0, 2);
		grid.add(numpad2Btn, 1, 2);
		grid.add(numpad3Btn, 2, 2);
		grid.add(numpad4Btn, 0, 1);
		grid.add(numpad5Btn, 1, 1);
		grid.add(numpad6Btn, 2, 1);
		grid.add(numpad7Btn, 0, 0);
		grid.add(numpad8Btn, 1, 0);
		grid.add(numpad9Btn, 2, 0);
		grid.add(minusBtn, 3, 0);
		grid.add(plusBtn, 3, 1);
		Scene scene = new Scene(grid);
		// Styling
		scene.getStylesheets().add("style.css");
		// Key events
		scene.setOnKeyPressed(this);
		scene.setOnKeyReleased(this);
		// Lock minimum window size
		stage.setMinHeight(560);
		stage.setMinWidth(690);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void handle(KeyEvent evt) {
		// When a key is pressed, change the NetworkTables topic to true and turn the
		// button lime
		if (evt.getEventType() == KeyEvent.KEY_PRESSED) {
			switch (evt.getCode()) {
				case NUMPAD1:
					numpad1Btn.getStyleClass().add("activated");
					numpad1Publisher.set(true);
					break;
				case NUMPAD2:
					numpad2Btn.getStyleClass().add("activated");
					numpad2Publisher.set(true);
					break;
				case NUMPAD3:
					numpad3Btn.getStyleClass().add("activated");
					numpad3Publisher.set(true);
					break;
				case NUMPAD4:
					numpad4Btn.getStyleClass().add("activated");
					numpad4Publisher.set(true);
					break;
				case NUMPAD5:
					numpad5Btn.getStyleClass().add("activated");
					numpad5Publisher.set(true);
					break;
				case NUMPAD6:
					numpad6Btn.getStyleClass().add("activated");
					numpad6Publisher.set(true);
					break;
				case NUMPAD7:
					numpad7Btn.getStyleClass().add("activated");
					numpad7Publisher.set(true);
					break;
				case NUMPAD8:
					numpad8Btn.getStyleClass().add("activated");
					numpad8Publisher.set(true);
					break;
				case NUMPAD9:
					numpad9Btn.getStyleClass().add("activated");
					numpad9Publisher.set(true);
					break;
				case SUBTRACT:
					minusBtn.getStyleClass().add("activated");
					minusPublisher.set(true);
					break;
				case ADD:
					plusBtn.getStyleClass().add("activated");
					plusPublisher.set(true);
					break;
				default:
					break;
			}
			// When the button is released, remove the button's lime color, and set the
			// NetworkTables topic to false
		} else if (evt.getEventType() == KeyEvent.KEY_RELEASED) {
			switch (evt.getCode()) {
				case NUMPAD1:
					numpad1Btn.getStyleClass().removeAll("activated");
					numpad1Publisher.set(false);
					break;
				case NUMPAD2:
					numpad2Btn.getStyleClass().removeAll("activated");
					numpad2Publisher.set(false);
					break;
				case NUMPAD3:
					numpad3Btn.getStyleClass().removeAll("activated");
					numpad3Publisher.set(false);
					break;
				case NUMPAD4:
					numpad4Btn.getStyleClass().removeAll("activated");
					numpad4Publisher.set(false);
					break;
				case NUMPAD5:
					numpad5Btn.getStyleClass().removeAll("activated");
					numpad5Publisher.set(false);
					break;
				case NUMPAD6:
					numpad6Btn.getStyleClass().removeAll("activated");
					numpad6Publisher.set(false);
					break;
				case NUMPAD7:
					numpad7Btn.getStyleClass().removeAll("activated");
					numpad7Publisher.set(false);
					break;
				case NUMPAD8:
					numpad8Btn.getStyleClass().removeAll("activated");
					numpad8Publisher.set(false);
					break;
				case NUMPAD9:
					numpad9Btn.getStyleClass().removeAll("activated");
					numpad9Publisher.set(false);
					break;
				case SUBTRACT:
					minusBtn.getStyleClass().removeAll("activated");
					minusPublisher.set(false);
					break;
				case ADD:
					plusBtn.getStyleClass().removeAll("activated");
					plusPublisher.set(false);
					break;
				default:
					break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		NetworkTablesJNI.Helper.setExtractOnStaticLoad(false);
		WPIUtilJNI.Helper.setExtractOnStaticLoad(false);
		WPIMathJNI.Helper.setExtractOnStaticLoad(false);
		CameraServerJNI.Helper.setExtractOnStaticLoad(false);
		CombinedRuntimeLoader.loadLibraries(ControlWindow.class, "wpiutiljni", "wpimathjni", "ntcorejni",
				"cscorejnicvstatic");
		// NetworkTables
		run();
		// JavaFX
		launch();
	}

	/**
	 * Start up NetworkTables instance, and prepare topics for each button
	 */
	public static void run() {
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		inst.startClient4("example client");
		inst.setServer("localhost"); // where TEAM=190, 294, etc, or use inst.setServer("hostname") or similar
		inst.startDSClient(); // recommended if running on DS computer; this gets the robot IP from the DS
		NetworkTable table = inst.getTable("Key");
		numpad1Publisher = table.getBooleanTopic("NUMPAD1").publish();
		numpad2Publisher = table.getBooleanTopic("NUMPAD2").publish();
		numpad3Publisher = table.getBooleanTopic("NUMPAD3").publish();
		numpad4Publisher = table.getBooleanTopic("NUMPAD4").publish();
		numpad5Publisher = table.getBooleanTopic("NUMPAD5").publish();
		numpad6Publisher = table.getBooleanTopic("NUMPAD6").publish();
		numpad7Publisher = table.getBooleanTopic("NUMPAD7").publish();
		numpad8Publisher = table.getBooleanTopic("NUMPAD8").publish();
		numpad9Publisher = table.getBooleanTopic("NUMPAD9").publish();
		minusPublisher = table.getBooleanTopic("MINUS").publish();
		plusPublisher = table.getBooleanTopic("PLUS").publish();
		numpad1Publisher.set(false);
		numpad2Publisher.set(false);
		numpad3Publisher.set(false);
		numpad4Publisher.set(false);
		numpad5Publisher.set(false);
		numpad6Publisher.set(false);
		numpad7Publisher.set(false);
		numpad8Publisher.set(false);
		numpad9Publisher.set(false);
		minusPublisher.set(false);
		plusPublisher.set(false);
	}
}