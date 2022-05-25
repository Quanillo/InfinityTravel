package application;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import BBDD.DbCiudad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AlojamientoClass extends MainInfinityClass{
	
	@FXML private ComboBox<String> cbCiudad;
	@FXML private ComboBox<String> cbTipo;
	@FXML private ComboBox<String> cbAlojamientos;
	@FXML private DatePicker dpEntrada;
	@FXML private DatePicker dpSalida;
	@FXML private ImageView imageView; 
	@FXML private Button button;
	@FXML private Button buttonPapa;
	@FXML private Image img;
	private static DbCiudad dbc = new DbCiudad();
	String papa = "https://memegenerator.net/img/instances/82133322/quien-es-tu-papa-ahh-quin.jpg";
	String imgUrl = "https://www.rutaspormarruecosali.com/pics_fotosnoticias/75/big_res_rutas-por-marruecos-ali-los-5-mejores-hoteles-de-marrakechl.jpg";
	
	
	@FXML
    void setImagen(MouseEvent event) {
		URLConnection connection;
		try {
			connection = new URL(imgUrl).openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			img = new Image(connection.getInputStream());
			imageView.setImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
    void setImagenPapa(MouseEvent event) {
		URLConnection connection;
		try {
			connection = new URL(papa).openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			img = new Image(connection.getInputStream());
			imageView.setImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public void setComboBoxCiudad() {
		ArrayList<String> listaCiudades = dbc.listarCiudades();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<listaCiudades.size();i++) {
			list.add(listaCiudades.get(i));
		}
		cbCiudad.setItems(list);
		
	}
}
