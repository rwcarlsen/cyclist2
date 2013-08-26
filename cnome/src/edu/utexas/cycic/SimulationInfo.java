package edu.utexas.cycic;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import edu.utah.sci.cyclist.ui.components.ViewBase;
/**
 * View for the Simulation Control Information
 * @author Robert
 *
 */
public class SimulationInfo extends ViewBase{
	/**
	 * Initialization function for the view.
	 */
	public SimulationInfo(){
		super();
		
		simInfo.setHgap(4);
		simInfo.setVgap(4);
		simInfo.setPadding(new Insets(10, 10, 10, 10));
		
		months();
		init();
	}
	
	GridPane simInfo = new GridPane();
	HashMap<String, String> months = new HashMap<String, String>();
	ArrayList<String> monthList = new ArrayList<String>();
	
	/**
	 * Adds the GridPane and input nodes to the simulationInfo view.
	 */
	public void init(){
		TextField duration = new TextField();
		duration.setText(cycicScenarios.workingCycicScenario.simulationData.duration);
		duration.textProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				cycicScenarios.workingCycicScenario.simulationData.duration = newValue;
			}
		});
		simInfo.add(new Label("Duration"), 0, 0);
		simInfo.add(duration, 1, 0);
		simInfo.add(new Label("Months"), 2, 0);
		

		final ComboBox<String> startMonth = new ComboBox<String>();
		startMonth.setValue(cycicScenarios.workingCycicScenario.simulationData.startMonth);
		for(int i = 0; i < 12; i++ ){
			startMonth.getItems().add(monthList.get(i));
		}
		startMonth.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				cycicScenarios.workingCycicScenario.simulationData.startMonth = months.get(newValue);
			}
		});
		simInfo.add(new Label("Start Month"), 0, 1);
		simInfo.add(startMonth, 1, 1);
		
		TextField startYear = new TextField();
		startYear.setText(cycicScenarios.workingCycicScenario.simulationData.startYear);
		startYear.textProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				cycicScenarios.workingCycicScenario.simulationData.startYear = newValue;
			}
		});
		simInfo.add(new Label("Start Year"), 0, 2);
		simInfo.add(startYear, 1, 2);
		
		TextField simStart = new TextField();
		simStart.setText(cycicScenarios.workingCycicScenario.simulationData.simStart);
		simStart.textProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				cycicScenarios.workingCycicScenario.simulationData.simStart = newValue;
			}
		});
		simInfo.add(new Label("Simulation Start"), 0 ,3);
		simInfo.add(simStart, 1, 3);
		
		TextField decay = new TextField();
		decay.setText(cycicScenarios.workingCycicScenario.simulationData.decay);
		decay.textProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				cycicScenarios.workingCycicScenario.simulationData.decay = newValue;
			}
		});
		simInfo.add(new Label("Decay Flag"), 0, 4);
		simInfo.add(decay, 1, 4);
		
		setContent(simInfo);
	}
	
	/**
	 * Quick hack to convert months into their integer values.
	 * i.e. January = 0, Feb = 1, etc...
	 */
	public void months(){
		monthList.add("January");
		monthList.add("February");
		monthList.add("March");
		monthList.add("April");
		monthList.add("May");
		monthList.add("June");
		monthList.add("July");
		monthList.add("August");
		monthList.add("September");
		monthList.add("October");
		monthList.add("November");
		monthList.add("December");
		
		months.put("January", "0");
		months.put("Febuary", "1");
		months.put("March", "2");
		months.put("April", "3");
		months.put("May", "4");
		months.put("June", "5");
		months.put("July", "6");
		months.put("August", "7");
		months.put("September", "8");
		months.put("October", "9");
		months.put("November", "10");
		months.put("December", "11");
	}
}
