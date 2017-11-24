package com.eitraz.talos.frontend.flow;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class TimerPanel extends AbstractFlowPanel {
    @Override
    protected String getTitle() {
        return "Every";
    }

    @Override
    protected HorizontalLayout getContent() {
        ComboBox<Long> amount = new ComboBox<>();
        amount.addStyleName(ValoTheme.COMBOBOX_SMALL);
        amount.setEmptySelectionAllowed(false);
        amount.setItems(1L, 2L, 5L, 10L, 15L, 20L, 30L, 45L, 60L);
        amount.setSelectedItem(5L);

        ComboBox<TemporalUnit> unit = new ComboBox<>();
        unit.addStyleName(ValoTheme.COMBOBOX_SMALL);
        unit.setEmptySelectionAllowed(false);
        unit.setTextInputAllowed(false);
        unit.setItems(ChronoUnit.SECONDS, ChronoUnit.MINUTES, ChronoUnit.HOURS);
        unit.setSelectedItem(ChronoUnit.MINUTES);

        return new HorizontalLayout(amount, unit);
    }

}
