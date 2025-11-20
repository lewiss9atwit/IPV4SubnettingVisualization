package app;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class visualizeSubnets
{
	
	public static void visualPane(Pane root, allNodes nodes)
	{
		subnetNode rootNode = nodes.root;
		// If a department uses the root which is the entire company block nothing else needs to be done after making the rectangle for the department
		if(rootNode.depUses)
		{
			Rectangle rootRect = visuals.putTextInRec(root, rootNode);
			rootRect.setLayoutX( (1920 - rootRect.getWidth()) / 2 );
			rootRect.setLayoutY(2);
			
		}
		
		// Otherwise we know the root is not used so we have a tree of the company's subnetting to display
		else
		{
			Group rootBubble = visuals.makeLabeledCircle(rootNode.size, 50, Color.LIGHTBLUE);
		    Circle rootC = (Circle) rootBubble.getChildren().get(0);
		    rootC.setCenterX(1920 / 2);
			rootC.setCenterY(55);
			root.getChildren().addAll(rootBubble);
			
			
			subnetNode leftChild = rootNode.left;
			subnetNode rightChild = rootNode.right;
			if (leftChild == null) {return;}
			double lastAngleLeft = 225;
			double lastAngleRight = 315;
			
			
			if (leftChild.depUses)
			{
				Rectangle leftRect = visuals.putTextInRec(root, leftChild);
				visuals.placeChildBubble(root, rootC, leftRect, 300, 210);
			}
			else
			{
				Group leftBubble = visuals.makeLabeledCircle(leftChild.size, 50, Color.LIGHTBLUE);
				Circle leftCircle = (Circle) leftBubble.getChildren().get(0);
				root.getChildren().addAll(leftBubble);
				visuals.placeChildBubble(root, rootC, leftCircle, 300, 210);
				visuals.drawChildren(root, leftCircle, leftChild, lastAngleLeft, lastAngleRight);
				
			}
			
			if (rightChild.depUses) 
			{
				Rectangle rightRect = visuals.putTextInRec(root, rightChild);
				visuals.placeChildBubble(root, rootC, rightRect, 300, 330);
				
				
			}
			else
			{
				Group rightBubble = visuals.makeLabeledCircle(rightChild.size, 50, Color.LIGHTBLUE);
				Circle rightCircle = (Circle) rightBubble.getChildren().get(0);
				root.getChildren().addAll(rightBubble);
				visuals.placeChildBubble(root, rootC, rightCircle, 300, 330);
				visuals.drawChildren(root, rightCircle, rightChild, lastAngleLeft, lastAngleRight);
			}
		   
		}
		
	}
}