package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.thirdparty.BulletList;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.thirdparty.ListItem;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.thirdparty.MultipleTextBox;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.thirdparty.Paragraph;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.thirdparty.Span;

public class AutoSuggestForm extends Composite {
	FlowPanel form;
	SuggestBox box;
	Set<String> tags;
	MultipleTextBox txtTB;

	public String getText() {
		String textString=txtTB.getText();
		return txtTB.getText();

	}

	public AutoSuggestForm(Set<String> tags) {
		form = new FlowPanel();
		txtTB = new MultipleTextBox();
		this.tags = tags;
		box = new SuggestBox(getSuggestions(tags), txtTB);

		box.addStyleName("original-token-input");

		form.add(box);

		initWidget(form);

		/*
		 * form.setStyleName("form"); initWidget(form);
		 * 
		 * form.add(new HTML(
		 * "<p>Type in the box below to see basic autocomplete in action...</p>"
		 * ));
		 * 
		 * MultipleTextBox txt = new MultipleTextBox(); SuggestBox box = new
		 * SuggestBox(getSuggestions(), txt);
		 * box.addStyleName("original-token-input");
		 * box.setAnimationEnabled(true);
		 * 
		 * form.add(box);
		 * 
		 * form.add(new HTML(
		 * "<p style='margin-top: 20px'>Type in the box below to see autocomplete with Facebook-style formatting.</p>"
		 * ));
		 * 
		 * // Facebook Style Autocompleter // CSS and DIV structure from
		 * http://loopj.com/tokeninput/demo.html:
		 * 
		 * // 1. Create an input field form.add(new InputListWidget());
		 * 
		 * form.add(new HTML(
		 * "<p>For more information about this demo, see <a href=\"http://raibledesigns.com/rd/entry/creating_a_facebook_style_autocomplete\">Creating a Facebook-style Autocomplete with GWT</a>."
		 * ));
		 */}

	/**
	 * Facebook Style Autocompleter. CSS and DIV structure from
	 * http://loopj.com/tokeninput/demo.html:
	 */
	public class InputListWidget extends Composite {
		List<String> itemsSelected = new ArrayList<String>();

		public InputListWidget() {
			FlowPanel panel = new FlowPanel();
			initWidget(panel);
			// 2. Show the following element structure and set the last <div> to
			// display: block
			/*
			 * <ul class="token-input-list-facebook"> <li
			 * class="token-input-input-token-facebook"> <input type="text"
			 * style=
			 * "outline-color: -moz-use-text-color; outline-style: none; outline-width: medium;"
			 * /> </li> </ul> <div class="token-input-dropdown-facebook"
			 * style="display: none;"/>
			 */
			final BulletList list = new BulletList();
			list.setStyleName("token-input-list-facebook");
			final ListItem item = new ListItem();
			item.setStyleName("token-input-input-token-facebook");
			final TextBox itemBox = new TextBox();
			itemBox.getElement()
					.setAttribute(
							"style",
							"outline-color: -moz-use-text-color; outline-style: none; outline-width: medium;");
			final SuggestBox box = new SuggestBox(getSuggestions(tags), itemBox);
			box.getElement().setId("suggestion_box");
			item.add(box);
			list.add(item);

			// this needs to be on the itemBox rather than box, or backspace
			// will get executed twice
			itemBox.addKeyDownHandler(new KeyDownHandler() {
				@Override
				public void onKeyDown(KeyDownEvent event) {
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						// only allow manual entries with @ signs (assumed email
						// addresses)
						if (itemBox.getValue().contains("@"))
							deselectItem(itemBox, list);
					}
					// handle backspace
					if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
						if ("".equals(itemBox.getValue().trim())) {
							ListItem li = (ListItem) list.getWidget(list
									.getWidgetCount() - 2);
							Paragraph p = (Paragraph) li.getWidget(0);
							if (itemsSelected.contains(p.getText())) {
								itemsSelected.remove(p.getText());
								GWT.log("Removing selected item '"
										+ p.getText() + "'", null);
								GWT.log("Remaining: " + itemsSelected, null);
							}
							list.remove(li);
							itemBox.setFocus(true);
						}
					}
				}
			});

			box.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
				@Override
				public void onSelection(SelectionEvent selectionEvent) {
					deselectItem(itemBox, list);
				}
			});

			panel.add(list);

			panel.getElement().setAttribute("onclick",
					"document.getElementById('suggestion_box').focus()");
			box.setFocus(true);
			/*
			 * Div structure after a few elements have been added: <ul
			 * class="token-input-list-facebook"> <li
			 * class="token-input-token-facebook"> <p>What's New Scooby-Doo?</p>
			 * <span class="token-input-delete-token-facebook">x</span> </li>
			 * <li class="token-input-token-facebook"> <p>Fear Factor</p> <span
			 * class="token-input-delete-token-facebook">x</span> </li> <li
			 * class="token-input-input-token-facebook"> <input type="text"
			 * style=
			 * "outline-color: -moz-use-text-color; outline-style: none; outline-width: medium;"
			 * /> </li> </ul>
			 */
		}

		private void deselectItem(final TextBox itemBox, final BulletList list) {
			if (itemBox.getValue() != null
					&& !"".equals(itemBox.getValue().trim())) {
				/**
				 * Change to the following structure: <li class="token-input-token-facebook">
				 * <p>
				 * What's New Scooby-Doo?
				 * </p>
				 * <span class="token-input-delete-token-facebook">x</span></li>
				 */

				final ListItem displayItem = new ListItem();
				displayItem.setStyleName("token-input-token-facebook");
				Paragraph p = new Paragraph(itemBox.getValue());

				displayItem.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent clickEvent) {
						displayItem
								.addStyleName("token-input-selected-token-facebook");
					}
				});

				/**
				 * TODO: Figure out how to select item and allow deleting with
				 * backspace key displayItem.addKeyDownHandler(new
				 * KeyDownHandler() { public void onKeyDown(KeyDownEvent event)
				 * { if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE) {
				 * removeListItem(displayItem, list); } } });
				 * displayItem.addBlurHandler(new BlurHandler() { public void
				 * onBlur(BlurEvent blurEvent) { displayItem.removeStyleName(
				 * "token-input-selected-token-facebook"); } });
				 */

				Span span = new Span("x");
				span.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent clickEvent) {
						removeListItem(displayItem, list);
					}
				});

				displayItem.add(p);
				displayItem.add(span);
				// hold the original value of the item selected

				GWT.log("Adding selected item '" + itemBox.getValue() + "'",
						null);
				itemsSelected.add(itemBox.getValue());
				GWT.log("Total: " + itemsSelected, null);

				list.insert(displayItem, list.getWidgetCount() - 1);
				itemBox.setValue("");
				itemBox.setFocus(true);
			}
		}

		private void removeListItem(ListItem displayItem, BulletList list) {
			GWT.log("Removing: "
					+ displayItem.getWidget(0).getElement().getInnerHTML(),
					null);
			itemsSelected.remove(displayItem.getWidget(0).getElement()
					.getInnerHTML());
			list.remove(displayItem);
		}
	}

	/**
	 * To make this return a DTO that allows you to grab multiple values, see
	 * the following tutorial:
	 * <p/>
	 * http://eggsylife.blogspot.com/2008/08/gwt-suggestbox-backed-by-dto-model.
	 * html
	 * 
	 * @return names of possible contacts
	 */
	private MultiWordSuggestOracle getSuggestions(Set<String> tags) {
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		oracle.addAll(tags);

		return oracle;
	}

}
