package com.eitraz.talos.frontend.flow;

import com.eitraz.talos.frontend.component.IntervalSelect;
import com.vaadin.ui.HorizontalLayout;

public class TimerPanel extends AbstractFlowPanel {
    @Override
    protected String getTitle() {
        return "Every";
    }

    @Override
    protected HorizontalLayout getContent() {
        return new IntervalSelect();
    }
}
