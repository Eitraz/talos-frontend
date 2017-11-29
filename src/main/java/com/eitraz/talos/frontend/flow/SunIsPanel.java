package com.eitraz.talos.frontend.flow;

import com.eitraz.talos.frontend.component.IntervalSelect;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.util.Optional;

public class SunIsPanel extends AbstractFlowPanel {
    @Override
    protected String getTitle() {
        return "Sun is";
    }

    @Override
    protected VerticalLayout getContent() {
        // Up / down
        ComboBox<String> sunIs = new ComboBox<>();
        sunIs.setEmptySelectionAllowed(false);
        sunIs.setTextInputAllowed(false);
        sunIs.setItems("Up", "Down");
        sunIs.setSelectedItem("Down");

        IntervalSelect sunriseOffset = new IntervalSelect(true, true);
        sunriseOffset.setCaption("Sunrise offset");

        IntervalSelect sunsetOffset = new IntervalSelect();
        sunsetOffset.setCaption("Sunset offset");

        FormLayout offsetLayout = new FormLayout(sunriseOffset, sunsetOffset);
        offsetLayout.setMargin(false);

        return new VerticalLayout(sunIs, offsetLayout);
    }

    @Override
    public Optional<Component> getFooter() {
        Label sunUpDownLabel = new Label("Today the sun rise at <b>08:16</b> and set at <b>20:43<b>.", ContentMode.HTML);
        return Optional.of(new HorizontalLayout(sunUpDownLabel));
    }
}
