package com.eitraz.talos.frontend.component;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class IntervalSelect extends HorizontalLayout {
    private final boolean allowNegative;
    private final boolean allowZero;

    public IntervalSelect() {
        this(false, false);
    }

    public IntervalSelect(boolean allowNegative, boolean allowZero) {
        this.allowNegative = allowNegative;
        this.allowZero = allowZero;

        ComboBox<Long> amount = new ComboBox<>();
        amount.addStyleNames("numeric");
        amount.setEmptySelectionAllowed(false);
        amount.setNewItemHandler(string -> {
            try {
                long value = Long.parseLong(string);

                if ((!allowNegative && value < 1) || (!allowZero && value == 0))
                    showWarning();
            } catch (NumberFormatException e) {
                showWarning();
            }
        });

        if (allowNegative)
            amount.setItems(-45L, -30L, -20L, -10L, -5L, -2L, -1L, 1L, 2L, 5L, 10L, 15L, 20L, 30L, 45L);
        else
            amount.setItems(1L, 2L, 5L, 10L, 15L, 20L, 30L, 45L);

        amount.setSelectedItem(5L);

        ComboBox<TemporalUnit> unit = new ComboBox<>();
        unit.addStyleName("time-unit");
        unit.setEmptySelectionAllowed(false);
        unit.setTextInputAllowed(false);
        unit.setItems(ChronoUnit.SECONDS, ChronoUnit.MINUTES, ChronoUnit.HOURS);
        unit.setSelectedItem(ChronoUnit.MINUTES);

        addComponents(amount, unit);
    }

    private void showWarning() {
        String message = String.format("Value has to be a%s whole number%s.",
                allowNegative ? "" : " positive",
                allowZero ? "" : ", zero is not allowed");
        Notification.show(message, Notification.Type.WARNING_MESSAGE);
    }
}
