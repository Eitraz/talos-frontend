package com.eitraz.talos.frontend.view;

import com.eitraz.talos.frontend.flow.SetDeviceStatusPanel;
import com.eitraz.talos.frontend.flow.SunIsPanel;
import com.eitraz.talos.frontend.flow.TimeIsBetweenPanel;
import com.eitraz.talos.frontend.flow.TimerPanel;
import com.eitraz.talos.frontend.util.Refresher;
import com.eitraz.talos.frontend.view.dashboard.AndOrPanel;
import com.eitraz.talos.frontend.view.dashboard.GivenPanel;
import com.eitraz.talos.frontend.view.dashboard.ThenPanel;
import com.eitraz.talos.frontend.view.dashboard.WhenPanel;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.HorizontalLayout;
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

        addComponent(createGivenLayout());
        addComponent(createWhenLayout());
        addComponent(createThenLayout());

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

    private Component createGivenLayout() {
        GivenPanel given = new GivenPanel();

        given.addComponent(
                new AndOrPanel(
                        new TimerPanel().create()
                )
        );

        given.addComponent(
                new AndOrPanel(
                        new TimerPanel().create(),
                        new AndOrPanel(new TimerPanel().create())
                )
        );

        return given;
    }

    private Component createWhenLayout() {
        WhenPanel when = new WhenPanel();

        when.addComponent(
                new AndOrPanel(
                        new TimeIsBetweenPanel().create(),
                        new SunIsPanel().create()
                )
        );
        return when;
    }

    private Component createThenLayout() {
        ThenPanel thenPanel = new ThenPanel();

        thenPanel.addComponent(
                new AndOrPanel(
                        new SetDeviceStatusPanel().create()
                )
        );
        return thenPanel;
    }
}
