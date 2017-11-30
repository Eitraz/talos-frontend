package com.eitraz.talos.frontend.view;

import com.eitraz.talos.frontend.flow.SetDeviceStatusPanel;
import com.eitraz.talos.frontend.flow.SunIsPanel;
import com.eitraz.talos.frontend.flow.TimeIsBetweenPanel;
import com.eitraz.talos.frontend.flow.TimerPanel;
import com.eitraz.talos.frontend.util.Refresher;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.aop.target.dynamic.Refreshable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@UIScope
@SpringView
public class DashboardView extends HorizontalLayout implements View {
    private ScheduledExecutorService executor;

    @PostConstruct
    void init() {
        setMargin(true);
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();

        addComponent(createGivenLayout("Given"));
        addComponent(createWhenLayout("When"));
        addComponent(createThenLayout("Then"));

        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(new Refresher(getUI(), this::getRefreshableComponents),
                15, 15, TimeUnit.SECONDS);
    }

    private List<Refreshable> getRefreshableComponents() {
        return getRefreshableComponents(new ArrayList<>(), this);
    }

    private List<Refreshable> getRefreshableComponents(List<Refreshable> refreshables, Component component) {
        if (component instanceof Refreshable)
            refreshables.add((Refreshable) component);

        if (component instanceof HasComponents) {
            ((HasComponents) component).forEach(child -> getRefreshableComponents(refreshables, child));
        }

        return refreshables;
    }

    @Override
    public void beforeLeave(ViewBeforeLeaveEvent event) {
        if (executor != null) {
            executor.shutdown();
        }
        event.navigate();
    }

    private Component createGivenLayout(String title) {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);
        layout.setCaption(title);

        layout.addComponent(
                createPanelLayout(
                        new TimerPanel().create()
                ));

        layout.addComponent(
                createPanelLayout(
                        new TimerPanel().create(),
                        createPanelLayout(
                                new TimerPanel().create()
                        )
                ));

        return layout;
    }

    private Component createPanelLayout(Component... components) {
        VerticalLayout layout = new VerticalLayout();
        layout.addStyleName(ValoTheme.PANEL_WELL);
        layout.addStyleName("wrapper");

        if (components.length > 0) {
            layout.addComponents(components);
        }

        createAddNewPanel();

        return layout;
    }

    private Component createWhenLayout(String title) {
        VerticalLayout layout = new VerticalLayout();
        layout.setCaption(title);
        layout.addStyleName(ValoTheme.PANEL_WELL);

        layout.addComponent(new TimeIsBetweenPanel().create());
        layout.addComponent(new SunIsPanel().create());
        layout.addComponent(createAddNewPanel());

        return layout;
    }

    private Component createThenLayout(String title) {
        VerticalLayout layout = new VerticalLayout();
        layout.setCaption(title);
        layout.addStyleName(ValoTheme.PANEL_WELL);

        layout.addComponent(new SetDeviceStatusPanel().create());
        layout.addComponent(createAddNewPanel());

        return layout;
    }

    private Component createAddNewPanel() {
        Button button = new Button(VaadinIcons.PLUS_CIRCLE_O);
        button.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        button.addStyleName("bright");
        button.setWidth(100, Unit.PERCENTAGE);
        button.setHeight(40, Unit.PIXELS);

        button.addClickListener(e -> new AddWindow().open());

        return button;
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
            DashboardView.this.getUI().addWindow(this);
            center();
            focus();
        }
    }
}
