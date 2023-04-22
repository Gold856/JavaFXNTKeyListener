package com.team20;

import java.io.IOException;

import edu.wpi.first.cscore.CameraServerJNI;
import edu.wpi.first.math.WPIMathJNI;
import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTablesJNI;
import edu.wpi.first.util.CombinedRuntimeLoader;
import edu.wpi.first.util.WPIUtilJNI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;

public class ControlWindow extends Application {
	static Button numpad1Btn = new Button("NUMPAD1");
	static Button numpad2Btn = new Button("NUMPAD2");
	static Button numpad3Btn = new Button("NUMPAD3");
	static Button numpad4Btn = new Button("NUMPAD4");
	static Button numpad5Btn = new Button("NUMPAD5");
	static Button numpad6Btn = new Button("NUMPAD6");
	static Button numpad7Btn = new Button("NUMPAD7");
	static Button numpad8Btn = new Button("NUMPAD8");
	static Button numpad9Btn = new Button("NUMPAD9");
	static Button minusBtn = new Button("-");
	static Button plusBtn = new Button("+");
	static Slider xAxisSlider = new Slider(-1, 1, 0);
	static Slider yAxisSlider = new Slider(-1, 1, 0);
	static Slider rotationAxisSlider = new Slider(-1, 1, 0);
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
	static DoublePublisher xAxisPublisher;
	static DoublePublisher yAxisPublisher;
	static DoublePublisher rotationAxisPublisher;

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
		xAxisSlider.setBlockIncrement(0.05);
		yAxisSlider.setBlockIncrement(0.05);
		rotationAxisSlider.setBlockIncrement(0.05);
		xAxisSlider.valueProperty().addListener(
				evt -> {
					xAxisPublisher.set(xAxisSlider.getValue());
					NetworkTableInstance.getDefault().flush();
				});
		yAxisSlider.valueProperty().addListener(
				evt -> {
					yAxisPublisher.set(yAxisSlider.getValue());
					NetworkTableInstance.getDefault().flush();
				});
		rotationAxisSlider.valueProperty().addListener(
				evt -> {
					rotationAxisPublisher.set(rotationAxisSlider.getValue());
					NetworkTableInstance.getDefault().flush();
				});
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
		grid.add(xAxisSlider, 0, 3, 3, 1);
		grid.add(yAxisSlider, 0, 4, 3, 1);
		grid.add(rotationAxisSlider, 0, 5, 3, 1);
		Scene scene = new Scene(grid);
		// Styling
		scene.getStylesheets().add("style.css");
		// Lock minimum window size
		stage.setMinHeight(560);
		stage.setMinWidth(690);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * When a button(JavaFX or keyboard) is pressed or released, turn the button
	 * green and
	 * update Network tables
	 * 
	 * @param button          The button to update
	 * @param buttonPublisher The publisher on NetworkTables
	 * @param isPressed       If the button is pressed
	 */
	public static void broadcastButtonEvent(Button button, BooleanPublisher buttonPublisher, boolean isPressed) {
		// If the button is pressed, turn the button lime
		if (isPressed) {
			button.getStyleClass().add("activated");
		} else {
			// If the button is released, remove the button's lime color
			button.getStyleClass().removeAll("activated");
		}
		buttonPublisher.set(isPressed);
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
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook();
		keyboardHook.addKeyListener(new GlobalKeyListener() {
			@Override
			public void keyPressed(GlobalKeyEvent event) {
				switch (event.getVirtualKeyCode()) {
					case GlobalKeyEvent.VK_NUMPAD1 -> broadcastButtonEvent(numpad1Btn, numpad1Publisher, true);
					case GlobalKeyEvent.VK_NUMPAD2 -> broadcastButtonEvent(numpad2Btn, numpad2Publisher, true);
					case GlobalKeyEvent.VK_NUMPAD3 -> broadcastButtonEvent(numpad3Btn, numpad3Publisher, true);
					case GlobalKeyEvent.VK_NUMPAD4 -> broadcastButtonEvent(numpad4Btn, numpad4Publisher, true);
					case GlobalKeyEvent.VK_NUMPAD5 -> broadcastButtonEvent(numpad5Btn, numpad5Publisher, true);
					case GlobalKeyEvent.VK_NUMPAD6 -> broadcastButtonEvent(numpad6Btn, numpad6Publisher, true);
					case GlobalKeyEvent.VK_NUMPAD7 -> broadcastButtonEvent(numpad7Btn, numpad7Publisher, true);
					case GlobalKeyEvent.VK_NUMPAD8 -> broadcastButtonEvent(numpad8Btn, numpad8Publisher, true);
					case GlobalKeyEvent.VK_NUMPAD9 -> broadcastButtonEvent(numpad9Btn, numpad9Publisher, true);
					case GlobalKeyEvent.VK_SUBTRACT -> broadcastButtonEvent(minusBtn, minusPublisher, true);
					case GlobalKeyEvent.VK_ADD -> broadcastButtonEvent(plusBtn, plusPublisher, true);
				}
			}

			@Override
			public void keyReleased(GlobalKeyEvent event) {
				switch (event.getVirtualKeyCode()) {
					case GlobalKeyEvent.VK_NUMPAD1 -> broadcastButtonEvent(numpad1Btn, numpad1Publisher, false);
					case GlobalKeyEvent.VK_NUMPAD2 -> broadcastButtonEvent(numpad2Btn, numpad2Publisher, false);
					case GlobalKeyEvent.VK_NUMPAD3 -> broadcastButtonEvent(numpad3Btn, numpad3Publisher, false);
					case GlobalKeyEvent.VK_NUMPAD4 -> broadcastButtonEvent(numpad4Btn, numpad4Publisher, false);
					case GlobalKeyEvent.VK_NUMPAD5 -> broadcastButtonEvent(numpad5Btn, numpad5Publisher, false);
					case GlobalKeyEvent.VK_NUMPAD6 -> broadcastButtonEvent(numpad6Btn, numpad6Publisher, false);
					case GlobalKeyEvent.VK_NUMPAD7 -> broadcastButtonEvent(numpad7Btn, numpad7Publisher, false);
					case GlobalKeyEvent.VK_NUMPAD8 -> broadcastButtonEvent(numpad8Btn, numpad8Publisher, false);
					case GlobalKeyEvent.VK_NUMPAD9 -> broadcastButtonEvent(numpad9Btn, numpad9Publisher, false);
					case GlobalKeyEvent.VK_SUBTRACT -> broadcastButtonEvent(minusBtn, minusPublisher, false);
					case GlobalKeyEvent.VK_ADD -> broadcastButtonEvent(plusBtn, plusPublisher, false);
				}
			}
		});
		// JavaFX
		launch();
	}

	/**
	 * Start up NetworkTables instance, and prepare topics for each button
	 */
	public static void run() {
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		inst.startClient4("Keyboard Client");
		inst.setServer("localhost"); // where TEAM=190, 294, etc, or use inst.setServer("hostname") or similar
		inst.startDSClient(); // recommended if running on DS computer; this gets the robot IP from the DS
		NetworkTable table = inst.getTable("SmartDashboard");
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
		xAxisPublisher = table.getDoubleTopic("xAxis").publish();
		yAxisPublisher = table.getDoubleTopic("yAxis").publish();
		rotationAxisPublisher = table.getDoubleTopic("rotationAxis").publish();
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
		xAxisPublisher.set(0);
		yAxisPublisher.set(0);
		rotationAxisPublisher.set(0);
	}
}