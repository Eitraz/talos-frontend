package com.eitraz.talos.frontend.view.dashboard;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class ThenPanel extends VerticalLayout {
    public ThenPanel(Component... components) {
        setMargin(false);
        setCaption("Then");

        addComponents(components);
    }
}
