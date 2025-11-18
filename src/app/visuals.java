package app;

import java.util.ArrayList;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	
	public static Group makeLabeledCircle(long labelNum, double radius, Color color) 
	{
	    Circle circle = new Circle(radius, color);
	    circle.setStroke(Color.BLACK);
	    circle.setStrokeWidth(2);

	    Text label = new Text(labelNum+"");
	    label.setFill(Color.BLACK);
	    label.setFont(Font.font("Arial", FontWeight.BOLD, 30));

	    // Update label position whenever bounds or circle moves
	    label.boundsInLocalProperty().addListener((obs, oldV, newV) -> {
	        label.setX(circle.getCenterX() - newV.getWidth() / 2);
	        label.setY(circle.getCenterY() + newV.getHeight() / 4);
	    });

	    circle.centerXProperty().addListener((obs, oldV, newV) -> {
	        label.setX(newV.doubleValue() - label.getBoundsInLocal().getWidth() / 2);
	    });

	    circle.centerYProperty().addListener((obs, oldV, newV) -> {
	        label.setY(newV.doubleValue() + label.getBoundsInLocal().getHeight() / 4);
	    });

	    return new Group(circle, label);
	}
	
	public static Rectangle putTextInRec(Pane root, subnetNode depSn)
	{
		
		Rectangle rect = new Rectangle(220, 100);
        rect.setArcWidth(30);     // rounded corners
        rect.setArcHeight(30);
        rect.setFill(Color.LIGHTGREEN);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);
        
        Text t0 = new Text(depSn.companyName);
        Text s0 = new Text(depSn.size + " Ips");
        Text t1 = new Text(depSn.networkAddress);
        Text t2 = new Text(depSn.broadcastAddress);
        Text t3 = new Text(depSn.subnetMask);
        
        VBox box = new VBox(1, t0, s0, t1, t2, t3);
        box.setAlignment(Pos.CENTER);

        // Sets Vbox in the center of the rectangle
        box.layoutXProperty().bind(
        	    rect.layoutXProperty()
        	        .add(rect.widthProperty().subtract(box.widthProperty()).divide(2))
        	);

        	box.layoutYProperty().bind(
        	    rect.layoutYProperty()
        	        .add(rect.heightProperty().subtract(box.heightProperty()).divide(2))
        	);
        	
        	root.getChildren().addAll(rect, box);
        
        
        
        return rect;
        
        
        
	}
	
	
	public static void drawChildren(Pane root, Circle parentC, subnetNode parentNode, double angleLeft, double angleRight)
	{
		
		// If there is a left child there is guarenteed to be a right child
		if (parentNode.left != null)
		{
			subnetNode currentNode = parentNode.left;
			long size = parentNode.size/2;
			
			// If the left child is a rectangle
			if(currentNode.depUses)
			{
				Rectangle leftRect = putTextInRec(root, currentNode);
				placeChildBubble(root, parentC, leftRect, 200, angleLeft);
			}
			
			else
			{
				Group leftBubble = visuals.makeLabeledCircle(currentNode.size, 50, Color.LIGHTBLUE);
				Circle leftCircle = (Circle) leftBubble.getChildren().get(0);
				root.getChildren().addAll(leftBubble);
				// angleLeft += 10;
				placeChildBubble(root, parentC, leftCircle, 200, angleLeft);
				drawChildren(root, leftCircle, currentNode, angleLeft, angleRight);
				
				
				
			}
			
			currentNode = parentNode.right;
			
			if(currentNode.depUses)
			{
				Rectangle rightRect = putTextInRec(root, currentNode);
				placeChildBubble(root, parentC, rightRect, 200, angleRight);
			}
			
			else
			{
				Group rightBubble = visuals.makeLabeledCircle(currentNode.size, 50, Color.LIGHTBLUE);
				Circle rightCircle = (Circle) rightBubble.getChildren().get(0);
				root.getChildren().addAll(rightBubble);
				// angleLeft += 10;
				placeChildBubble(root, parentC, rightCircle, 200, angleRight);
				drawChildren(root, rightCircle, currentNode, angleLeft, angleRight);
				
				
				
			}
		}
	}
	        
}