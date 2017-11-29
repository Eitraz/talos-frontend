package com.eitraz.talos.frontend;

import com.eitraz.talos.frontend.view.ErrorView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("talos")
@Title("Talos - Home Automation")
@Push
public class TalosUI extends UI {
    private final SpringViewProvider viewProvider;
    private final MainViewDisplay mainView;
    private final TalosMenu talosMenu;

    @Autowired
    public TalosUI(MainViewDisplay mainView, SpringViewProvider viewProvider, SpringNavigator navigator, TalosMenu talosMenu) {
        this.mainView = mainView;
        this.viewProvider = viewProvider;
        this.talosMenu = talosMenu;

        navigator.setErrorView(ErrorView.class);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(false);

        layout.addComponent(talosMenu);
        layout.addComponent(mainView);
        layout.setExpandRatio(mainView, 1);

        setContent(layout);

        getNavigator().navigateTo("dashboard");
    }
}
