package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.INavigationPane;
import de.bbcdaas.opendata.gwt.client.resources.Resources;

public class NavigationPane extends Composite implements INavigationPane {

	private static NavigationPaneUiBinder uiBinder = GWT
			.create(NavigationPaneUiBinder.class);
	@UiField
	ImageButton uploadBtn;
	@UiField
	Tree categoriesTree;
	@UiField ScrollPanel categoriesScrollPanel;

	interface NavigationPaneUiBinder extends UiBinder<Widget, NavigationPane> {
	}

	public NavigationPane() {

		initWidget(uiBinder.createAndBindUi(this));

		uploadBtn.setResource(Resources.INSTANCE.uploadBtnIcon());
		
		categoriesTree.getElement().getStyle().setProperty("maxWidth","150px");
		categoriesTree.getElement().getStyle()
				.setProperty("maxHeight", "200px");
	}

	@Override
	public HasClickHandlers getUploadHandlers() {
		return uploadBtn;
	}

}
