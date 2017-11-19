package com.eitraz.talos.frontend.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@UIScope
@SpringView
public class DashboardView extends HorizontalLayout implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {
        setMargin(true);
        setSizeFull();

        addComponent(createTestLayout("Given"));
        addComponent(createTestLayout("When"));
        addComponent(createTestLayout("Then"));


//        addComponent(new Label("Whoho!"));


//        VerticalLayout dropTargetLayout = new VerticalLayout();
//        dropTargetLayout.setCaption("Drop things inside me");
//        dropTargetLayout.addStyleName(ValoTheme.LAYOUT_CARD);
//
//        // make the layout accept drops
//        DropTargetExtension<VerticalLayout> dropTarget = new DropTargetExtension<>(dropTargetLayout);
//
//        // the drop effect must match the allowed effect in the drag source for a successful drop
//        dropTarget.setDropEffect(DropEffect.MOVE);
//
//        // catch the drops
//        dropTarget.addDropListener(event -> {
//            // if the drag source is in the same UI as the target
//            Optional<AbstractComponent> dragSource = event.getDragSourceComponent();
//            if (dragSource.isPresent() && dragSource.get() instanceof Label) {
//                // move the label to the layout
//                dropTargetLayout.addComponent(dragSource.get());
//
//                // get possible transfer data
//                event.getDataTransferData("text/html").ifPresent(s -> System.out.println("Transferred: " + s));
////                if (message != null) {
////                    Notification.show("DropEvent with data transfer html: " + message);
////                } else {
////                    // get transfer text
////                    message = event.getDataTransferText();
////                    Notification.show("DropEvent with data transfer text: " + message);
////                }
//
//                // handle possible server side drag data, if the drag source was in the same UI
//                event.getDragData().ifPresent(data -> System.out.println("Data: " + data));
//            }
//        });
//
//
//        addComponent(dropTargetLayout);
//
//
//
//        Arrays.asList("Drag me", "And me", "Me to")
//                .forEach(title -> {
//                    Label label = new Label(title);
//                    DragSourceExtension<Label> dragSource = new DragSourceExtension<>(label);
//                    dragSource.setEffectAllowed(EffectAllowed.MOVE);
//                    dragSource.setDataTransferText("hello receiver");
//                    dragSource.setDataTransferData("text/html", "<label>hello receiver</label>");
//
//                    dragSource.addDragStartListener(event -> Notification.show("Drag event started"));
//
//                    dragSource.addDragEndListener(event -> {
//                        if (event.isCanceled()) {
//                            Notification.show("Drag event was canceled");
//                        } else {
//                            Notification.show("Drag event finished");
//                        }
//                    });
//
//                    dropTargetLayout.addComponent(label);
//                });
    }

    private Component createTestLayout(String title) {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth(100, Unit.PERCENTAGE);
        layout.setHeightUndefined();
        layout.setCaption(title);
        layout.addStyleName(ValoTheme.PANEL_WELL);

        FormLayout form = new FormLayout();
        form.setMargin(false);
        form.setSizeFull();

        TextField textField = new TextField("Name");
        textField.setSizeFull();
        form.addComponent(textField);

        VerticalLayout container = new VerticalLayout(form);
        container.setSizeFull();
        Panel panel = new Panel("A test panel", container);

        layout.addComponent(panel);

        layout.addComponent(createAddNewPanel());

        return layout;
    }

    private Component createAddNewPanel() {
        Button button = new Button(VaadinIcons.PLUS_CIRCLE_O);
        button.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.addStyleName("dashed");
        button.setWidth(100, Unit.PERCENTAGE);
        button.setHeight(50, Unit.PIXELS);

        return button;
    }

}
