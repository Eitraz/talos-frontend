package com.eitraz.talos.frontend.component;

import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TimeSelect extends HorizontalLayout {
    public TimeSelect() {
        WebBrowser webBrowser = Page.getCurrent().getWebBrowser();

        String[] availableIDs = TimeZone.getAvailableIDs(webBrowser.getTimezoneOffset());

        ZonedDateTime now;
        if (availableIDs != null && availableIDs.length > 0)
            now = ZonedDateTime.now(ZoneId.of(availableIDs[0]));
        else
            now = ZonedDateTime.now();

        NativeSelect<Integer> hour = new NativeSelect<>(null, createInegerList(0, 23));
        hour.setEmptySelectionAllowed(false);
        hour.setSelectedItem(now.getHour());
        hour.setItemCaptionGenerator(i -> String.valueOf(i));

        Label colon = new Label(":");

        NativeSelect<Integer> minute = new NativeSelect<>(null, createInegerList(0, 59));
        minute.setEmptySelectionAllowed(false);
        minute.setSelectedItem(now.getMinute());
        hour.setItemCaptionGenerator(i -> String.valueOf(i));

        addComponents(hour, colon, minute);
        setComponentAlignment(colon, Alignment.MIDDLE_CENTER);
        setSpacing(false);
    }

    @SuppressWarnings("SameParameterValue")
    private static List<Integer> createInegerList(int fromIncluded, int toIncluded) {
        return IntStream.rangeClosed(fromIncluded, toIncluded).boxed().collect(toList());
    }
}
