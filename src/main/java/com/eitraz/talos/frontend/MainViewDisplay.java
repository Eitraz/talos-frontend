package com.eitraz.talos.frontend;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@SpringViewDisplay
public class MainViewDisplay extends Panel implements ViewDisplay {
    public MainViewDisplay() {
    }

    @PostConstruct
    void init() {
        setSizeFull();
        setStyleName(ValoTheme.PANEL_BORDERLESS);
//        setPrimaryStyleName("valo-content");
//        addStyleName("v-scrollable");
    }

    @Override
    public void showView(View view) {
        setContent((Component) view);
    }
}
