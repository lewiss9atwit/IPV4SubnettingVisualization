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
		
		if(rootNode.depUses)
		{
			Rectangle rootRect = visuals.putTextInRec(root, rootNode);
			rootRect.setLayoutX( (1920 - rootRect.getWidth()) / 2 );
			rootRect.setLayoutY(2);
			
		}
		else
		{
			Group rootBubble = visuals.makeLabeledCircle(rootNode.size, 50, Color.LIGHTBLUE);
		    Circle rootC = (Circle) rootBubble.getChildren().get(0);
		    rootC.setCenterX(1920 / 2);
			rootC.setCenterY(55);
			root.getChildren().addAll(rootBubble);
			
			subnetNode leftChild = rootNode.left;
			subnetNode rightChild = rootNode.right;
			double lastAngleLeft = 225;
			double lastAngleRight = 315;
			
			if (leftChild.depUses)
			{
				Rectangle leftRect = visuals.putTextInRec(root, leftChild);
				visuals.placeChildBubble(root, rootC, leftRect, 500, 195);
			}
			else
			{
				Group leftBubble = visuals.makeLabeledCircle(leftChild.size, 50, Color.LIGHTBLUE);
				Circle leftCircle = (Circle) leftBubble.getChildren().get(0);
				root.getChildren().addAll(leftBubble);
				visuals.placeChildBubble(root, rootC, leftCircle, 200, 225);
				visuals.drawChildren(root, leftCircle, leftChild, lastAngleLeft, lastAngleRight);
				
			}
			
			if (rightChild.depUses) 
			{
				Rectangle rightRect = visuals.putTextInRec(root, rightChild);
				visuals.placeChildBubble(root, rootC, rightRect, 500, 345);
				
				
			}
			else
			{
				Group rightBubble = visuals.makeLabeledCircle(rightChild.size, 50, Color.LIGHTBLUE);
				Circle rightCircle = (Circle) rightBubble.getChildren().get(0);
				root.getChildren().addAll(rightBubble);
				visuals.placeChildBubble(root, rootC, rightCircle, 200, 315);
				visuals.drawChildren(root, rightCircle, rightChild, lastAngleLeft, lastAngleRight);
			}
		   
		}
		
	}
}