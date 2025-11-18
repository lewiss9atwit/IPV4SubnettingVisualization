package app;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class allNodes
{
	ArrayList<department> unusableDepartments = new ArrayList<>();
	subnetNode root;
	
	public allNodes(company c)
	{
		createNodes(c);
		
	}
	public void createNodes(company comp)
	{
	
		this.root = new subnetNode (comp.totalIps);
		
		for(department d : comp.deps)
		{
			if(d.networkAddress == -1)
			{
				
				unusableDepartments.add(d);
				
			}
			else
			{
				subnetNode sN = new subnetNode(d);
				
				subnetNode foundNode = findNode(sN, root);
				if (foundNode == root)
				{
					root = sN;
				}
				
				else 
				{
					subnetNode parentNode = foundNode.parent;
					if (parentNode.left == foundNode) 
					{
						parentNode.left = sN;
						
					}
					else
					{
						parentNode.right = sN;
						
					}
					sN.parent = parentNode;
					
				}
				
				
			}
			
		}
		
	}
	
	public subnetNode findNode(subnetNode subNode, subnetNode curr)
	{
		if (curr.size == subNode.size)
		{
			if(!curr.depUses && curr.left == null)
			{
				return curr;
				
			}
			return null;
			
		}
		
		if (curr.left == null)
		{
			curr.left = new subnetNode(curr.size/2);
			curr.left.parent = curr;
			curr.right = new subnetNode(curr.size/2);
			curr.right.parent = curr;
		}
				
	
		subnetNode leftFound = findNode(subNode, curr.left);
		if (leftFound != null) {return leftFound;}
		return findNode(subNode, curr.right);
	}
	
	
	
	
	public String unusableDepmessage(department d)
	{
		long blockSize = (long) Math.pow(2,  32- d.prefix);
		
		return ("There are not enough IPV4 Address available because " + blockSize + " IP Addresses are not available "
				+ "for the department's " + d.ipsNeeded + " IP addresses it needs");
		
	}
}