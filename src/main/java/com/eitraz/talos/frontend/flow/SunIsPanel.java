package com.eitraz.talos.frontend.flow;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Optional;

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

    @Override
    public Optional<Component> getFooter() {
        Label sunUpDownLabel = new Label("Today the sun rise at <b>08:16</b> and set at <b>20:43<b>.", ContentMode.HTML);
        return Optional.of(new HorizontalLayout(sunUpDownLabel));
    }
}
