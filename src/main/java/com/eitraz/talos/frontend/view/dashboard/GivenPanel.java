package com.eitraz.talos.frontend.view.dashboard;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class GivenPanel extends VerticalLayout {
    public GivenPanel(Component... components) {
        setMargin(false);
        setCaption("Given");

        addComponents(components);
    }
}
