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
		
		/*
		department test = new department("Testing", 62);
		department test2 = new department("TestingBoth", 126);
		department test3 = new department("Testing3", 62);
		department test4 = new department("Testing4", 254);
		department test5 = new department("Testing5", 510);
		department test6 = new department("Testing 6", 29);
		department[] deps = new department[] {test3, test6};
		company testComp = new company("Comp", "192.156.24.5/24", deps);
		testComp.subnetCompany();
		*/
		
		
		
		ArrayList<department> departments = new ArrayList<>();
		System.out.println("What do you want to call this company:");
		String Company = input.nextLine();
		System.out.println("Please type the block IP for this company:");
		String ipAddressBlock = input.nextLine();
		
		
		while ((!company.ipAddressBlockValid(ipAddressBlock)) || company.noIps(ipAddressBlock))
		{
			System.out.println("Ip address invalid or has 2 little Ips for any department please type it again");
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
		
		// Sorts departments to have the deparments with the largest amount of ips first then descending down to the next max until the end
		departments.sort((a, b) -> Integer.compare(b.ipsNeeded, a.ipsNeeded));
		department[] deps = departments.toArray(department[]:: new);
		
		
		
		
		
		company mainCompany = new company(Company, ipAddressBlock, deps);
		mainCompany.subnetCompany();
		allNodes companyNodes = new allNodes(mainCompany);
		

		
		
		
		Pane root = new Pane();
		
		visualizeSubnets.visualPane(root, companyNodes);
		Scene scene = new Scene(root, 1920, 1080);
		stage.setTitle(mainCompany.name + "'s IPV4 Subnetting");
		stage.setScene(scene);
        stage.show();
		
        companyNodes.unusableDepmessages();
		
		
	}
	
	
	
	
	
}