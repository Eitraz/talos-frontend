package com.eitraz.talos.frontend.flow;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public abstract class AbstractFlowPanel {
    private HorizontalLayout panelCaption;

    protected abstract String getTitle();

    protected abstract Component getContent();

    protected boolean isTrue() {
        return false;
    }

    public CssLayout create() {
        CssLayout layout = new CssLayout();
        layout.setSizeFull();
        layout.addStyleName(ValoTheme.LAYOUT_CARD);

        panelCaption = new HorizontalLayout();
        {
            panelCaption.setSpacing(false);
            panelCaption.addStyleName("v-panel-caption");
            panelCaption.setWidth(100, Sizeable.Unit.PERCENTAGE);

            Label label = new Label(getTitle());
            panelCaption.addComponent(label);
            panelCaption.setExpandRatio(label, 1);

            Button button = new Button();
            button.setIcon(VaadinIcons.TRASH);
            button.addStyleNames(ValoTheme.BUTTON_BORDERLESS_COLORED, ValoTheme.BUTTON_SMALL, ValoTheme.BUTTON_ICON_ONLY);
            panelCaption.addComponent(button);
        }

        layout.addComponents(panelCaption, new VerticalLayout(getContent()));

        update();
        return layout;
    }

    public void update() {
        if (isTrue())
            panelCaption.addStyleName("green");
        else
            panelCaption.removeStyleName("green");
    }
}
