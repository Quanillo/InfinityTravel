package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class BilletesClass extends MainInfinityClass {
	
	@FXML private Text numBilletes;
	@FXML private ChoiceBox<String> origen;
	@FXML private ChoiceBox<String> destino;
	@FXML private TextField fecha;
	@FXML private Button btnSoloIda;
	@FXML private Button btnVuelta;
	@FXML private Button masBilletes;
	@FXML private Button menosBilletes;
	@FXML private Button comprar;
	@FXML private BorderPane bp;
	@FXML private AnchorPane ap;
	
	
	
	public void cargaIdaYvuelta(MouseEvent event) {
		loadPage("IdayvueltaPage");
	}
	public void cargaSoloIda(MouseEvent event) {
		loadPage("SoloIda");
	}
	
	
	
	private void loadPage(String page) { //metodo para cargar pagina
		Parent root=null;

		try {
			root=FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bp.setCenter(root);;
	}
	
	
	
}
