package com.eitraz.talos.frontend.view.dashboard;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class WhenPanel extends VerticalLayout {
    public WhenPanel(Component... components) {
        setMargin(false);
        setCaption("When");
        addStyleName("gwt-container");
        setHeight(100, Unit.PERCENTAGE);

        addComponents(components);
    }
}
