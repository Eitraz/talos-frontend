package com.eitraz.talos.frontend.flow;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class SetDeviceStatusPanel extends AbstractFlowPanel {
    private ComboBox<String> device;
    private ComboBox<String> status;

    @Override
    protected String getTitle(boolean collapsed) {
        String title = "Set device";
        if (collapsed) {
            title += " " + String.valueOf(device.getSelectedItem().get()) + " to " + String.valueOf(status.getSelectedItem().get()).toLowerCase();
        }
        return title;
    }

    @Override
    protected HorizontalLayout getContent() {
        device = new ComboBox<>();
        device.setEmptySelectionAllowed(false);
        device.setItems("Kitchen", "Livingroom", "Office");
        device.setSelectedItem("Kitchen");

        Label to = new Label("to");

        status = new ComboBox<>();
        status.addStyleName("on-off");
        status.setEmptySelectionAllowed(false);
        status.setTextInputAllowed(false);
        status.setItems("On", "Off");
        status.setSelectedItem("On");

        HorizontalLayout layout = new HorizontalLayout(device, to, status);
        layout.setComponentAlignment(to, Alignment.MIDDLE_CENTER);
        return layout;
    }
}
