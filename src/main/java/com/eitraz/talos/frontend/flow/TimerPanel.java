package com.eitraz.talos.frontend.flow;

import com.eitraz.talos.frontend.component.IntervalSelect;
import com.vaadin.ui.HorizontalLayout;

public class TimerPanel extends AbstractFlowPanel {

    private IntervalSelect interval;

    @Override
    protected String getTitle(boolean collapsed) {
        String title = "Every";

        if (collapsed) {
            title += " " + String.valueOf(interval.getAmount().get()) + " " + String.valueOf(interval.getUnit().get()).toLowerCase();
        }

        return title;
    }

    @Override
    protected HorizontalLayout getContent() {
        interval = new IntervalSelect();
        return interval;
    }
}
