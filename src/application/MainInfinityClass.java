package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainInfinityClass {

	@FXML private Pane p;
	@FXML private BorderPane bp;
	@FXML private AnchorPane ap;
	@FXML private Button packs;
	@FXML private Button Billetes;
	@FXML private Button Alojamiento;
	@FXML private Button Vehiculos;
	@FXML private Button Experiencias;
	@FXML private Button Seguros;
	
	@FXML 
	private void packsLoad(MouseEvent event) {
		loadPage("PacksPage");
	}
	
	@FXML 
	private void billetesLoad(MouseEvent event) {
		loadPage("BilletesPage");
	}
	
	@FXML 
	private void alojamientoLoad(MouseEvent event) {
		loadPage("AlojamientoPage");
	}
	
	@FXML 
	private void vehiculosLoad(MouseEvent event) {
		loadPage("VehiculosPage");
	}
	
	@FXML 
	private void experienciasLoad(MouseEvent event) {
		loadPage("ExperienciasPage");
	}
	
	@FXML 
	private void segurosLoad(MouseEvent event) {
		loadPage("SegurosPage");
	}
	
	private void loadPage(String page) {
		Parent root=null;
	
		try {
			root=FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bp.setCenter(root);;
	}

	
}
