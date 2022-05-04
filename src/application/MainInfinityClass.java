package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainInfinityClass {

	@FXML private Pane p;
	@FXML private BorderPane bp;
	@FXML private AnchorPane ap;
	
	@FXML 
	private void packsLoad(MouseEvent event) {
		loadPage("PacksClass");
	}
	
	
	private void loadPage(String page) {
		Parent root=null;
	
		try {
			root=FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setClip(root);;
	}

	
}
