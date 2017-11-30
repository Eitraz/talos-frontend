package com.eitraz.talos.frontend.view.dashboard;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class AndOrPanel extends VerticalLayout {
    public AndOrPanel(Component... components) {
        setMargin(false);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidth(100, Unit.PERCENTAGE);
        addComponent(buttonLayout);

        addDivider(buttonLayout);

        ComboBox<String> andOr = new ComboBox<>();
        andOr.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
        andOr.addStyleName("and-or");
        andOr.setTextInputAllowed(false);
        andOr.setEmptySelectionAllowed(false);
        andOr.setItems("And", "Or");
        andOr.setSelectedItem("And");
        buttonLayout.addComponent(andOr);

        addDivider(buttonLayout);

        VerticalLayout layout = new VerticalLayout();
        layout.addStyleName(ValoTheme.PANEL_WELL);
        layout.addStyleName("wrapper");
        addComponent(layout);

        layout.addComponents(components);

        layout.addComponent(new AddNewButton());
    }

    private Label createDivider() {
        Label divider = new Label("");
        divider.setSizeFull();
        divider.addStyleName("divider");
        return divider;
    }

    private void addDivider(HorizontalLayout layout) {
        Label divider = createDivider();
        layout.addComponent(divider);
        layout.setExpandRatio(divider, 1);
    }
}
