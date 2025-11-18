package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// may be used Later
import java.util.Scanner;

public class main extends Application
{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		Scanner input = new Scanner(System.in);
		
		
		department test = new department("Testing", 62);
		department test2 = new department("TestingBoth", 126);
		department test3 = new department("Testing3", 62);
		department test4 = new department("Testing4", 254);
		department test5 = new department("Testing5", 510);
		department test6 = new department("Testing 6", 29);
		department[] deps = new department[] {test, test2,test4, test3};
		company testComp = new company("Comp", "192.156.24.5/24", deps);
		testComp.subnetCompany();
		
		
		
        /*// Main program
		ArrayList<department> departments = new ArrayList<>();
		System.out.println("What do you want to call this company:");
		String Company = input.nextLine();
		System.out.println("Please type the block IP for this company:");
		String ipAddressBlock = input.nextLine();
		
		
		while (!company.ipAddressBlockValid(ipAddressBlock))
		{
			System.out.println("Ip address invalid please type it again");
			ipAddressBlock = input.nextLine();
		}
		
		System.out.println("Now we will create departments for the compnay type the name of the first department");
		String departmentName = input.nextLine();
		System.out.println("How many ips does this department need");
		int departmentIps = input.nextInt();
		department dep = new department(departmentName, departmentIps);
		departments.add(dep);
		
		System.out.println("Now we will add other departments if you want, if you are done writing deparmtents type 0 itself for the department name");
		input.nextLine();
		String departmentName1 = input.nextLine();
		
		while(!departmentName1.equals("0"))
		{
			System.out.println("How many ips does this department need");
			departmentIps = input.nextInt();
			department dep1 = new department(departmentName1, departmentIps);
			departments.add(dep1);
			
			input.nextLine();
			System.out.println("What is the name of this department?");
			departmentName1 = input.nextLine();
			
		}
		
		department[] deps = departments.toArray(department[]:: new);
		
		
		
		
		
		company testCompany = new company(Company, ipAddressBlock, deps);
		testCompany.subnetCompany();
		allNodes testV = new allNodes(testCompany);
		
		/* testing subnetNode
		for (subnetNode sn : testV.subnetNodes)
		{
			System.out.println(sn.networkAddress);
			System.out.println(sn.broadcastAddress);
			System.out.println(sn.subnetMask);
		}
		*/
		
		
		
		// test circle
		
		subnetNode testSn = new subnetNode(testComp.deps[0]);
		Rectangle rect = new Rectangle(220, 100);
        rect.setArcWidth(30);     // rounded corners
        rect.setArcHeight(30);
        rect.setFill(Color.LIGHTGREEN);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);
        

        
        Text t0 = new Text(testSn.companyName);
        Text s0 = new Text(testSn.size + " Ips");
        Text t1 = new Text(testSn.networkAddress);
        Text t2 = new Text(testSn.broadcastAddress);
        Text t3 = new Text(testSn.subnetMask);
        

        VBox box = new VBox(1, t0, s0, t1, t2, t3);
        box.setAlignment(Pos.CENTER);

        // Setes Vbox in the center of the rectangle
        box.layoutXProperty().bind(
        	    rect.layoutXProperty()
        	        .add(rect.widthProperty().subtract(box.widthProperty()).divide(2))
        	);

        	box.layoutYProperty().bind(
        	    rect.layoutYProperty()
        	        .add(rect.heightProperty().subtract(box.heightProperty()).divide(2))
        	);
        	
		Circle testC = new Circle(50);
		testC.setFill(Color.LIGHTBLUE);
		double centerX = 1920 / 2.0;
		double centerY = 1080 / 2.0;
		
		Text cText = new Text(256 + "");
		
		
		// Puts the text in circle C
		
		
		
		Circle testR = new Circle(50);
		testR.setFill(Color.LIGHTBLUE);
		
		Circle testB = new Circle(50);
		testB.setFill(Color.LIGHTBLUE);
		Circle testA = new Circle(50);
		testA.setFill(Color.LIGHTBLUE);
		

		testC.setCenterX(centerX);
		testC.setCenterY(centerY);
		
		Pane root = new Pane();
		
		root.getChildren().addAll(testC, testR, rect, box);
		visuals.placeChildBubble(root, testC, rect, 200, 240);
		visuals.placeChildBubble(root, testC, testR, 200, 300);
		
		root.getChildren().addAll(testB, testA);
		visuals.placeChildBubble(root, testR, testA, 200, 300);
		visuals.placeChildBubble(root, testR, testB, 200, 240);
		
		
		
		
		Scene scene = new Scene(root, 1920, 1080);
		stage.setTitle("test window");
		stage.setScene(scene);
        stage.show();
		
		
		
	}
	
	
	
	
	
}