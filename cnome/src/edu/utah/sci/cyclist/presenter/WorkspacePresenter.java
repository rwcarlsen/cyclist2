package edu.utah.sci.cyclist.presenter;



import java.util.ArrayList;
import java.util.List;

import org.mo.closure.v1.Closure;

import edu.utah.sci.cyclist.event.notification.CyclistNotification;
import edu.utah.sci.cyclist.event.notification.CyclistNotificationHandler;
import edu.utah.sci.cyclist.event.notification.CyclistNotifications;
import edu.utah.sci.cyclist.event.notification.CyclistTableNotification;
import edu.utah.sci.cyclist.event.notification.EventBus;
import edu.utah.sci.cyclist.event.notification.SimpleNotification;
import edu.utah.sci.cyclist.model.Model;
import edu.utah.sci.cyclist.model.Table;
import edu.utah.sci.cyclist.view.View;
import edu.utah.sci.cyclist.view.components.ViewBase;
import edu.utah.sci.cyclist.view.components.Workspace;
import edu.utah.sci.cyclist.view.tool.GenericTool;
import edu.utah.sci.cyclist.view.tool.Tool;
import edu.utah.sci.cyclist.view.tool.ToolsLibrary;

public class WorkspacePresenter extends PresenterBase {

	private Workspace _workspace;
	private Model _model;
	private List<Presenter> _presenters = new ArrayList<>();
	
	public WorkspacePresenter(EventBus bus, Model model) {
		super(bus);
		_model = model;
		
		addListeners();
	}
	
	public void setView(View workspace) {
		if (workspace instanceof Workspace) {
			_workspace = (Workspace) workspace;
			
			_workspace.setOnToolDrop(new Closure.V3<Tool, Double, Double>() {

				@Override
				public void call(Tool tool, Double x, Double y) {
					addTool(tool, x, y);
				}
			});
			
			_workspace.setOnTableDrop(new Closure.V1<Table>() {

				@Override
				public void call(Table table) {
					_workspace.addTable(table);
					broadcast(new CyclistTableNotification(CyclistNotifications.DATASOURCE_ADD, table));				
				}
				
			});
			
			_workspace.setOnShowTable(new Closure.V3<Table, Double, Double>() {

				@Override
				public void call(Table table, Double x, Double y) {
					GenericPresenter presenter = (GenericPresenter) addTool(new GenericTool(), x, y);
					presenter.addTable(table);
				}
			});
		}
	}
	
		
	public void run() {
		// setup event listeners on the bus
	}

	private Presenter addTool(Tool tool, double x, double y) {
		ViewBase view = (ViewBase) tool.getView();
		view.setTranslateX(x);
		view.setTranslateY(y);
		_workspace.addView(view);
		
		Presenter presenter = tool.getPresenter(getEventBus());
		if (presenter != null) {	
			_presenters.add(presenter);
			presenter.setView(view);	
		}
		
		return presenter;
	}
	
	private void addListeners() {
		addNotificationHandler(CyclistNotifications.REMOVE_VIEW, new CyclistNotificationHandler() {
			
			@Override
			public void handle(CyclistNotification event) {
				String id = ((SimpleNotification)event).getMsg();
				for (Presenter presenter : _presenters) {
					if (presenter.getId().equals(id)) {
						_presenters.remove(presenter);
						_workspace.removeView((ViewBase)presenter.getView());
						break;
					}
				}
				
			}
		});
	}
	
}
