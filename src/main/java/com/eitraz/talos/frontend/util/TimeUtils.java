package com.eitraz.talos.frontend.util;

import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public final class TimeUtils {
    private TimeUtils() {
    }

    public static ZoneId getZoneId() {
        WebBrowser webBrowser = Page.getCurrent().getWebBrowser();

        List<String> availableIDs = Arrays.asList(TimeZone.getAvailableIDs(webBrowser.getTimezoneOffset()));
        ZoneId systemDefaultZoneId = ZoneId.systemDefault();

        // Default
        if (availableIDs.isEmpty() || availableIDs.contains(systemDefaultZoneId.getId())) {
            return systemDefaultZoneId;
        }
        // First available
        else {
            return ZoneId.of(availableIDs.get(0));
        }
    }
}
