package com.eitraz.talos.frontend.flow;

import com.eitraz.talos.frontend.component.IntervalSelect;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class SunIsPanel extends AbstractFlowPanel {

    private ComboBox<String> sunIs;

    @Override
    protected String getTitle(boolean collapsed) {
        String title = "Sun is";

        if (collapsed) {
            title += " " + String.valueOf(sunIs.getSelectedItem().get()).toLowerCase();
        }

        return title;
    }

    @Override
    protected VerticalLayout getContent() {
        // Up / down
        sunIs = new ComboBox<>();
        sunIs.addStyleName("up-down");
        sunIs.setEmptySelectionAllowed(false);
        sunIs.setTextInputAllowed(false);
        sunIs.setItems("Up", "Down");
        sunIs.setSelectedItem("Down");

        IntervalSelect sunriseOffset = new IntervalSelect(true, true);
        sunriseOffset.setCaption("Sunrise offset");
        sunriseOffset.setSelected(0L, ChronoUnit.MINUTES);

        IntervalSelect sunsetOffset = new IntervalSelect(true, true);
        sunsetOffset.setCaption("Sunset offset");
        sunsetOffset.setSelected(0L, ChronoUnit.MINUTES);

        FormLayout offsetLayout = new FormLayout(sunriseOffset, sunsetOffset);
        offsetLayout.setMargin(false);

        VerticalLayout layout = new VerticalLayout(sunIs, offsetLayout);
        layout.setMargin(false);
        return layout;
    }

    @Override
    public Optional<Component> getFooter() {
        Label sunUpDownLabel = new Label("Today the sun rise at <b>08:16</b> and set at <b>20:43<b>.", ContentMode.HTML);
        return Optional.of(new HorizontalLayout(sunUpDownLabel));
    }
}
