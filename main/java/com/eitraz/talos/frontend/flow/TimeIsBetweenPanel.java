package com.eitraz.talos.frontend.flow;

import com.eitraz.talos.frontend.component.TimeSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import java.time.Duration;
import java.time.ZonedDateTime;

public class TimeIsBetweenPanel extends AbstractFlowPanel {
    private final TimeSelect start;
    private final TimeSelect end;

    public TimeIsBetweenPanel() {
        start = TimeSelect.create(Duration.ofHours(-1));
        start.addTimeSelectionListener(t -> timeChanged());

        end = TimeSelect.create(Duration.ofHours(1));
        end.addTimeSelectionListener(t -> timeChanged());
    }

    @Override
    protected String getTitle() {
        return "Time is between";
    }

    @Override
    protected HorizontalLayout getContent() {
        Label and = new Label("and");
        HorizontalLayout layout = new HorizontalLayout(start, and, end);
        layout.setComponentAlignment(and, Alignment.MIDDLE_CENTER);
        return layout;
    }

    @Override
    protected boolean isTrue() {
        ZonedDateTime startTime = start.getSelectedTime();
        ZonedDateTime endTime = end.getSelectedTime();

        if (endTime.isBefore(startTime))
            endTime = endTime.plusDays(1);

        ZonedDateTime now = ZonedDateTime.now();

        return startTime.isBefore(now) && now.isBefore(endTime);
    }

    private void timeChanged() {
        update();
    }

}
