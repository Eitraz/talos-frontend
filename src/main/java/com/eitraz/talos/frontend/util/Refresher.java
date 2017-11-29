package com.eitraz.talos.frontend.util;

import com.vaadin.ui.UI;
import org.springframework.aop.target.dynamic.Refreshable;

import java.util.Collection;
import java.util.function.Supplier;

public class Refresher implements Runnable {
    private final UI ui;
    private final Supplier<Collection<Refreshable>> refreshableSupplier;

    public Refresher(UI ui, Supplier<Collection<Refreshable>> refreshableSupplier) {
        this.ui = ui;
        this.refreshableSupplier = refreshableSupplier;
    }

    @Override
    public void run() {
        ui.access(() -> refreshableSupplier.get().forEach(Refreshable::refresh));
    }
}
