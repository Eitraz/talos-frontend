package com.eitraz.talos.frontend.flow;

import com.eitraz.talos.frontend.component.TimeSelect;
import com.eitraz.talos.frontend.util.TimeUtils;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TimeIsBetweenPanel extends AbstractFlowPanel {
    private final TimeSelect start;
    private final TimeSelect end;
    private Label currentTimeLabel;
    private ZoneId zoneId;

    public TimeIsBetweenPanel() {
        start = TimeSelect.create(Duration.ofHours(-1));
        start.addTimeSelectionListener(t -> timeChanged());

        end = TimeSelect.create(Duration.ofHours(1));
        end.addTimeSelectionListener(t -> timeChanged());
    }

    @Override
    protected String getTitle(boolean collapsed) {
        String title = "Time is between";

        if (collapsed) {
            title += " " + start.getSelectedTime().getHour() + ":" + start.getSelectedTime().getMinute() + " and " + end.getSelectedTime().getHour() + ":" + end.getSelectedTime().getMinute();
        }

        return title;
    }

    @Override
    protected HorizontalLayout getContent() {
        Label and = new Label("and");
        HorizontalLayout layout = new HorizontalLayout(start, and, end);
        layout.setComponentAlignment(and, Alignment.MIDDLE_CENTER);
        return layout;
    }

    @Override
    public Optional<Component> getFooter() {
        zoneId = TimeUtils.getZoneId();
        currentTimeLabel = new Label("", ContentMode.HTML);
        updateCurrentTime();
        return Optional.of(new HorizontalLayout(currentTimeLabel));
    }

    private void updateCurrentTime() {
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        String serverTime = now.format(DateTimeFormatter.ofPattern("HH:mm"));
        currentTimeLabel.setValue("Current server time is <b>" + serverTime + "<b>.");
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
        refresh();
    }

    @Override
    public synchronized void refresh() {
        updateCurrentTime();
        super.refresh();
    }
}
