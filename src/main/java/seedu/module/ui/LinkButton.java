package seedu.module.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import seedu.module.model.module.Link;

/**
 * Button that will launch the contained link when clicked
 */
public class LinkButton extends Button {
    private Link link;

    public LinkButton(Link link) {
        super(link.name);
        this.link = link;
        this.setPrefHeight(25);
        this.setPrefWidth(160);
        this.setOnAction(event -> this.link.launch());
    }
}
