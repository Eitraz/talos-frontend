package com.eitraz.talos.frontend.flow;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.aop.target.dynamic.Refreshable;

import java.util.Optional;

public abstract class AbstractFlowPanel extends CssLayout implements Refreshable {
    private long refreshCount = 0;
    private long lastRefreshTime = System.currentTimeMillis();

    private HorizontalLayout header;
    private VerticalLayout content;
    private VerticalLayout footer;
    private Label titleLabel;

    protected abstract String getTitle(boolean collapsed);

    protected abstract Component getContent();

    protected boolean isTrue() {
        return false;
    }

    public AbstractFlowPanel create() {
        setSizeFull();
        addStyleName(ValoTheme.LAYOUT_CARD);

        header = new HorizontalLayout();
        {
            header.setSpacing(false);
            header.addStyleName("v-panel-caption");
            header.setWidth(100, Sizeable.Unit.PERCENTAGE);

            ComboBox<String> andOr = new ComboBox<>();
            andOr.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
            andOr.addStyleName("and-or");
            andOr.setTextInputAllowed(false);
            andOr.setEmptySelectionAllowed(false);
            andOr.setItems("And", "Or");
            andOr.setSelectedItem("And");
            header.addComponent(andOr);

            titleLabel = new Label(getTitle(false));
            header.addComponent(titleLabel);
            header.setExpandRatio(titleLabel, 1);

            Button trashButton = new Button();
            trashButton.setIcon(VaadinIcons.TRASH);
            trashButton.addStyleNames(ValoTheme.BUTTON_BORDERLESS_COLORED, ValoTheme.BUTTON_ICON_ONLY);
            header.addComponent(trashButton);

            Button collapseButton = new Button();
            collapseButton.setIcon(VaadinIcons.CHEVRON_UP);
            collapseButton.addStyleNames(ValoTheme.BUTTON_BORDERLESS_COLORED, ValoTheme.BUTTON_ICON_ONLY);
            collapseButton.addClickListener(event -> {
                collapseButton.setIcon(content.isVisible() ? VaadinIcons.CHEVRON_DOWN : VaadinIcons.CHEVRON_UP);
                content.setVisible(!content.isVisible());

                if (footer != null) {
                    footer.setVisible(content.isVisible());
                }

                refresh();
            });
            header.addComponent(collapseButton);
        }

        content = new VerticalLayout(getContent());
        addComponents(header, content);

        getFooter().ifPresent(component -> {
            footer = new VerticalLayout(component);
            footer.setSpacing(false);
            footer.setMargin(false);
            footer.addStyleNames("v-panel-footer");
            footer.setWidth(100, Sizeable.Unit.PERCENTAGE);

            addComponent(footer);
        });

        refresh();
        return this;
    }

    public Optional<Component> getFooter() {
        return Optional.empty();
    }

    @Override
    public synchronized void refresh() {
        titleLabel.setValue(getTitle(!content.isVisible()));

        if (isTrue())
            header.addStyleName("green");
        else
            header.removeStyleName("green");

        refreshCount++;
        lastRefreshTime = System.currentTimeMillis();
    }

    @Override
    public long getRefreshCount() {
        return refreshCount;
    }

    @Override
    public long getLastRefreshTime() {
        return lastRefreshTime;
    }
}
