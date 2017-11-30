package com.eitraz.talos.frontend.view.dashboard;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class AddNewButton extends Button {
    public AddNewButton() {
        super(VaadinIcons.PLUS_CIRCLE_O);

        addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        addStyleName(ValoTheme.BUTTON_FRIENDLY);
        addStyleName("bright");
        setWidth(100, Unit.PERCENTAGE);
        setHeight(40, Unit.PIXELS);

        addClickListener(e -> new AddWindow().open());
    }

    private class AddWindow extends Window {
        public AddWindow() {
            super();

            addCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);

            setModal(true);
            setClosable(false);
            setResizable(false);

            FormLayout form = new FormLayout();
            {
                Label section = new Label("Lorem Ipsum");
                section.addStyleNames(ValoTheme.LABEL_H3, ValoTheme.LABEL_COLORED);
                form.addComponent(section);

                form.addComponent(new TextField("Name"));

                form.setSizeUndefined();
                form.setMargin(false);
            }

            Panel panel = new Panel(new VerticalLayout(form));
            {
                panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
                panel.addStyleName(ValoTheme.PANEL_SCROLL_INDICATOR);
            }

            HorizontalLayout footer = new HorizontalLayout();
            {
                Label footerText = new Label("");
                footerText.setSizeUndefined();

                Button ok = new Button("OK", event -> close());
                ok.addStyleName(ValoTheme.BUTTON_PRIMARY);

                Button cancel = new Button("Close", event -> close());

                footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
                footer.setWidth(100, Unit.PERCENTAGE);
                footer.addComponents(footerText, ok, cancel);
                footer.setExpandRatio(footerText, 1);
            }


            VerticalLayout content = new VerticalLayout(panel, footer);
            content.setExpandRatio(panel, 1);
            setContent(content);
        }

        public void open() {
            UI.getCurrent().addWindow(this);
            center();
            focus();
        }
    }
}
