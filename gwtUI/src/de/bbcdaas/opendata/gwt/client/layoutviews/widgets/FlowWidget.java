package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IFlowWidget;

public class FlowWidget extends Composite implements IFlowWidget {

	private static FlowWidgetUiBinder uiBinder = GWT
			.create(FlowWidgetUiBinder.class);
	@UiField
	HorizontalPanel horizontalPanel;

	interface FlowWidgetUiBinder extends UiBinder<Widget, FlowWidget> {
	}

	int currentStep;
	int steps;
	HashMap<Integer, Label> texts;

	public FlowWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		currentStep = 1;
		texts = new HashMap<Integer, Label>();
	}

	@Override
	public void setNumberOfSteps(int steps) {
		this.steps = steps;
		for (int i = 0; i < steps; i++) {
			Label label = new Label();
			label.setText("Step");
			texts.put(Integer.valueOf(i + 1), label);
		horizontalPanel.add(label);
		}

	}

	@Override
	public void setStepText(int step, String text) {
	texts.get(Integer.valueOf(step)).setText(text);
	}

	@Override
	public void setCurrentStep(int step) {
		texts.get(currentStep).removeStyleName("currentLabel");
		texts.get(Integer.valueOf(step)).setStyleName("currentLabel");

	}

	@Override
	public int getCurrentStep() {
		return currentStep;
	}

}
