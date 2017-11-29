package com.eitraz.talos.frontend;

import com.eitraz.talos.frontend.view.AnotherView;
import com.eitraz.talos.frontend.view.DashboardView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.LinkedHashSet;
import java.util.Set;

@UIScope
@SpringComponent
public class TalosMenu extends CssLayout {
    private final SpringNavigator navigator;

    private final Set<MenuItem> menuItems = new LinkedHashSet<>();

    @Autowired
    public TalosMenu(SpringNavigator navigator) {
        this.navigator = navigator;

        setPrimaryStyleName(ValoTheme.MENU_ROOT);
    }

    @PostConstruct
    void init() {
        CssLayout menu = new CssLayout();
        menu.addStyleName(ValoTheme.MENU_PART);
        addComponent(menu);

        // ===== Top =====
        HorizontalLayout top = new HorizontalLayout();
        top.setWidth(100, Unit.PERCENTAGE);
        top.setSpacing(false);
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName(ValoTheme.MENU_TITLE);
        menu.addComponent(top);

        // Title
        Label title = new Label("Talos");
        title.addStyleNames(ValoTheme.LABEL_H3, ValoTheme.LABEL_BOLD);
        title.setSizeUndefined();
        top.addComponent(title);
        top.setExpandRatio(title, 1);

        // Toggle menu
        Button showMenu = new Button("Menu", event -> {
            if (menu.getStyleName().contains("valo-menu-visible")) {
                menu.removeStyleName("valo-menu-visible");
            } else {
                menu.addStyleNames("valo-menu-visible");
            }
        });
        showMenu.addStyleNames(ValoTheme.BUTTON_PRIMARY, "valo-menu-toggle");
        showMenu.setIcon(VaadinIcons.LIST);
        menu.addComponent(showMenu);

        // Menu Items
        CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);

        // Create menu items
        this.menuItems.add(new MenuItem("Dashboard", VaadinIcons.LIST, DashboardView.class));
        this.menuItems.add(new MenuItem("Another", VaadinIcons.LIGHTBULB, AnotherView.class));

        // Add menu items
        this.menuItems.forEach(menuItemsLayout::addComponent);


        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
                menuItems.forEach(i -> i.removeStyleName("selected"));

                menuItems.stream()
                        .filter(i -> i.getView() == event.getNewView().getClass())
                        .findFirst()
                        .ifPresent(c -> c.addStyleName("selected"));
            }
        });
    }

    private class MenuItem extends Button {
        private final Class<? extends View> view;

        public MenuItem(String caption, Resource icon, Class<? extends View> view) {
            super(caption, icon);
            this.view = view;

            setPrimaryStyleName(ValoTheme.MENU_ITEM);
            addClickListener(e -> navigator.navigateTo(view.getSimpleName().replaceAll("View", "").toLowerCase()));
        }

        public Class<? extends View> getView() {
            return view;
        }
    }
}
