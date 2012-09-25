package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

public interface IFlowWidget {
	void setNumberOfSteps(int steps);

	void setStepText(int step,String text);
	
	void setCurrentStep(int step);
	int getCurrentStep();
}
