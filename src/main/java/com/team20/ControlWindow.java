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
	static BooleanPublisher numpad1Publisher;
	static BooleanPublisher numpad2Publisher;
	static BooleanPublisher numpad3Publisher;
	static BooleanPublisher numpad4Publisher;
	static BooleanPublisher numpad5Publisher;
	static BooleanPublisher numpad6Publisher;
	static BooleanPublisher numpad7Publisher;
	static BooleanPublisher numpad8Publisher;
	static BooleanPublisher numpad9Publisher;
	Button minusBtn = new Button("-");
	Button plusBtn = new Button("+");

	@Override
	public void start(Stage stage) {
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
		GridPane grid = new GridPane();
		grid.setHgap(30);
		grid.setVgap(30);
		grid.setAlignment(Pos.CENTER);
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
		scene.getStylesheets().add("style.css");
		scene.setOnKeyPressed(this);
		scene.setOnKeyReleased(this);
		stage.setMinHeight(560);
		stage.setMinWidth(690);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void handle(KeyEvent evt) {
		if (evt.getEventType() == KeyEvent.KEY_PRESSED) {
			switch (evt.getCode()) {
				case NUMPAD1:
					numpad1Btn.getStyleClass().add("activated");
					numpad1Publisher.set(true);
					break;
				case NUMPAD2:
					numpad2Btn.getStyleClass().add("activated");
					break;
				case NUMPAD3:
					numpad3Btn.getStyleClass().add("activated");
					break;
				case NUMPAD4:
					numpad4Btn.getStyleClass().add("activated");
					break;
				case NUMPAD5:
					numpad5Btn.getStyleClass().add("activated");
					break;
				case NUMPAD6:
					numpad6Btn.getStyleClass().add("activated");
					break;
				case NUMPAD7:
					numpad7Btn.getStyleClass().add("activated");
					break;
				case NUMPAD8:
					numpad8Btn.getStyleClass().add("activated");
					break;
				case NUMPAD9:
					numpad9Btn.getStyleClass().add("activated");
					break;
				case SUBTRACT:
					minusBtn.getStyleClass().add("activated");
					break;
				case ADD:
					plusBtn.getStyleClass().add("activated");
				default:
					break;
			}
		} else if (evt.getEventType() == KeyEvent.KEY_RELEASED) {
			System.out.println(evt.getCode());
			switch (evt.getCode()) {
				case NUMPAD1:
					numpad1Btn.getStyleClass().removeAll("activated");
					numpad1Publisher.set(false);
					break;
				case NUMPAD2:
					numpad2Btn.getStyleClass().remove("activated");
					break;
				case NUMPAD3:
					numpad3Btn.getStyleClass().remove("activated");
					break;
				case NUMPAD4:
					numpad4Btn.getStyleClass().remove("activated");
					break;
				case NUMPAD5:
					numpad5Btn.getStyleClass().remove("activated");
					break;
				case NUMPAD6:
					numpad6Btn.getStyleClass().remove("activated");
					break;
				case NUMPAD7:
					numpad7Btn.getStyleClass().remove("activated");
					break;
				case NUMPAD8:
					numpad8Btn.getStyleClass().remove("activated");
					break;
				case NUMPAD9:
					numpad9Btn.getStyleClass().remove("activated");
					break;
				case SUBTRACT:
					minusBtn.getStyleClass().remove("activated");
					break;
				case ADD:
					plusBtn.getStyleClass().remove("activated");
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

		run();
		launch();
	}

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
		numpad1Publisher.set(false);
	}
}