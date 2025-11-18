package app;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class visuals {

	/*
	 * public static Group makeBubbles(allNodes compNodes) {
	 * 
	 * 
	 * 
	 * return bubbleGroup;
	 * 
	 * 
	 * }
	 */

	public static void placeChildBubble(
	        Pane root,
	        Circle parent,
	        Node child,
	        double distance,
	        double angleDegrees) {

	    double angle = Math.toRadians(angleDegrees);

	    // Gets the center of the parent
	    double parentCenterX = parent.getCenterX();
	    double parentCenterY = parent.getCenterY();

	    // gets the angle the child will be at mathematically
	    double childCenterX = parentCenterX + distance * Math.cos(angle);
	    double childCenterY = parentCenterY - distance * Math.sin(angle);

	    // Positions the child
	    if (child instanceof Rectangle rect) {
	        rect.setLayoutX(childCenterX - rect.getWidth() / 2);
	        rect.setLayoutY(childCenterY - rect.getHeight() / 2);
	    }
	    else if (child instanceof Circle c) {
	        c.setCenterX(childCenterX);
	        c.setCenterY(childCenterY);
	    }

	    // Draws the line
	    Line line = new Line();

	    // Start at bottom-center of parent circle
	    line.setStartX(parentCenterX);
	    line.setStartY(parentCenterY + parent.getRadius());

	    // End at top middle of child
	    if (child instanceof Rectangle rect) {
	        line.setEndX(rect.getLayoutX() + rect.getWidth() / 2);
	        line.setEndY(rect.getLayoutY());
	    }
	    else if (child instanceof Circle c) {
	        line.setEndX(c.getCenterX());
	        line.setEndY(c.getCenterY() - c.getRadius());
	    }

	    root.getChildren().add(line);
	}
}