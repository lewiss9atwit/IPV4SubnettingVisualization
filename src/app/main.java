package app;

import java.util.HashMap;
import java.util.Map;

// may be used Later
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

public class main
{
	
	public static void main(String[] args)
	{
		// Checking if we can push
		System.out.println("test");
		department test = new department("Testing", 62);
		department test2 = new department("TestingBoth", 126);
		department test3 = new department("Testing3", 62);
		department test4 = new department("Testing4", 254);
		department[] deps = new department[] {test, test2,test4, test3};
		
		company testCompany = new company("Company", "192.170.50.5/23", deps);
		boolean testCompanySubnet = testCompany.subnetCompany();
		
	}
	
	
}