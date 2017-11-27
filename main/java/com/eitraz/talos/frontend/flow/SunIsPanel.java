package com.eitraz.talos.frontend.flow;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class SunIsPanel extends AbstractFlowPanel {
    @Override
    protected String getTitle() {
        return "Sun is";
    }

    @Override
    protected HorizontalLayout getContent() {
        ComboBox<String> sunIs = new ComboBox<>();
        sunIs.addStyleName(ValoTheme.COMBOBOX_SMALL);
        sunIs.setEmptySelectionAllowed(false);
        sunIs.setTextInputAllowed(false);
        sunIs.setItems("Up", "Down");
        sunIs.setSelectedItem("Down");

        Label to = new Label("to");

        ComboBox<String> status = new ComboBox<>();
        status.addStyleName(ValoTheme.COMBOBOX_SMALL);
        status.setEmptySelectionAllowed(false);
        status.setTextInputAllowed(false);
        status.setItems("On", "Off");
        status.setSelectedItem("On");

        HorizontalLayout layout = new HorizontalLayout(sunIs, to, status);
        layout.setComponentAlignment(to, Alignment.MIDDLE_CENTER);

        return layout;
    }
}
