package com.eitraz.talos.frontend.flow;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class SetDeviceStatusPanel extends AbstractFlowPanel {
    @Override
    protected String getTitle() {
        return "Set device";
    }

    @Override
    protected HorizontalLayout getContent() {
        ComboBox<String> device = new ComboBox<>();
        device.addStyleName(ValoTheme.COMBOBOX_SMALL);
        device.setEmptySelectionAllowed(false);
        device.setItems("Kitchen", "Livingroom", "Office");
        device.setSelectedItem("Kitchen");

        Label to = new Label("to");

        ComboBox<String> status = new ComboBox<>();
        status.addStyleName(ValoTheme.COMBOBOX_SMALL);
        status.setEmptySelectionAllowed(false);
        status.setTextInputAllowed(false);
        status.setItems("On", "Off");
        status.setSelectedItem("On");

        HorizontalLayout layout = new HorizontalLayout(device, to, status);
        layout.setComponentAlignment(to, Alignment.MIDDLE_CENTER);
        return layout;
    }
}
