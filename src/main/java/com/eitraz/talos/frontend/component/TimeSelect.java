package com.eitraz.talos.frontend.component;

import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TimeSelect extends HorizontalLayout implements SingleSelectionListener<Integer> {
    private final ZoneId zoneId;
    private final NativeSelect<Integer> hour;
    private final NativeSelect<Integer> minute;

    private Set<Consumer<ZonedDateTime>> listeners = new HashSet<>();

    public TimeSelect(ZonedDateTime selectedTime) {
        this.zoneId = selectedTime.getZone();

        hour = new NativeSelect<>(null, createInegerList(0, 23));
        hour.setEmptySelectionAllowed(false);
        hour.setSelectedItem(selectedTime.getHour());
        hour.addSelectionListener(this);

        Label colon = new Label(":");

        minute = new NativeSelect<>(null, createInegerList(0, 59));
        minute.setEmptySelectionAllowed(false);
        minute.setSelectedItem(selectedTime.getMinute());
        minute.addSelectionListener(this);

        addComponents(hour, colon, minute);
        setComponentAlignment(colon, Alignment.MIDDLE_CENTER);
        setSpacing(false);
    }

    public void addTimeSelectionListener(Consumer<ZonedDateTime> listener) {
        listeners.add(listener);
    }

    @SuppressWarnings("ConstantConditions")
    public ZonedDateTime getSelectedTime() {
        return ZonedDateTime.now(zoneId)
                .withHour(hour.getSelectedItem().get())
                .withMinute(minute.getSelectedItem().get());
    }

    @Override
    public void selectionChange(SingleSelectionEvent<Integer> event) {
        ZonedDateTime selectedTime = getSelectedTime();
        listeners.forEach(listener -> listener.accept(selectedTime));
    }

    @SuppressWarnings("SameParameterValue")
    private static List<Integer> createInegerList(int fromIncluded, int toIncluded) {
        return IntStream.rangeClosed(fromIncluded, toIncluded).boxed().collect(toList());
    }

    public static TimeSelect create() {
        return create(Duration.ofHours(0));
    }

    public static TimeSelect create(TemporalAmount offset) {
        WebBrowser webBrowser = Page.getCurrent().getWebBrowser();

        String[] availableIDs = TimeZone.getAvailableIDs(webBrowser.getTimezoneOffset());

        ZoneId zoneId;
        if (availableIDs != null && availableIDs.length > 0)
            zoneId = ZoneId.of(availableIDs[0]);
        else
            zoneId = ZoneId.systemDefault();

        return create(ZonedDateTime.now(zoneId).plus(offset));
    }

    public static TimeSelect create(ZonedDateTime selectedTime) {
        return new TimeSelect(selectedTime);
    }
}
