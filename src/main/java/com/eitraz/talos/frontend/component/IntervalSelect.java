package com.eitraz.talos.frontend.component;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IntervalSelect extends HorizontalLayout {
    private final boolean allowNegative;
    private final boolean allowZero;
    private final ComboBox<Long> amount;
    private final ComboBox<TemporalUnit> unit;

    public IntervalSelect() {
        this(false, false);
    }

    public IntervalSelect(boolean allowNegative, boolean allowZero) {
        this.allowNegative = allowNegative;
        this.allowZero = allowZero;

        amount = new ComboBox<>();
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

        List<Long> values;

        if (allowNegative)
            values = Arrays.asList(-45L, -30L, -20L, -10L, -5L, -2L, -1L, 0L, 1L, 2L, 5L, 10L, 15L, 20L, 30L, 45L);
        else
            values = Arrays.asList(0L, 1L, 2L, 5L, 10L, 15L, 20L, 30L, 45L);

        if (!allowZero)
            values = values.stream().filter(v -> v != 0).collect(Collectors.toList());

        amount.setItems(values);
        amount.setSelectedItem(5L);
        amount.setPageLength(values.size());

        unit = new ComboBox<>();
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

    public void setSelected(long amount, ChronoUnit unit) {
        if (!allowNegative && amount < 0)
            return;

        if (!allowZero && amount == 0)
            return;

        this.amount.setSelectedItem(amount);
        this.unit.setSelectedItem(unit);
    }

    public Optional<Long> getAmount() {
        return amount.getSelectedItem();
    }

    public Optional<TemporalUnit> getUnit() {
        return unit.getSelectedItem();
    }
}
