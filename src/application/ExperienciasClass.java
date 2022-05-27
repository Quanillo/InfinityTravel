package application;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;

import BBDD.Db;
import BBDD.DbCiudad;
import BBDD.DbProducto;
import Code.Cliente;
import Code.Experiencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ExperienciasClass extends MainInfinityClass{
	
	@FXML private Text txtPrecio;
	@FXML private Text txtNombre;
	@FXML private ComboBox<String> cbCiudad;
	@FXML private ComboBox<Experiencia> cbExperiencias;
	@FXML private DatePicker dpFecha;
	@FXML private ImageView imageView; 
	@FXML private Button btnAddCarrito;
	@FXML private Image img;
	private static DbCiudad dbc = new DbCiudad();
	private static DbProducto dbp = new DbProducto();
	private Experiencia experienciaSeleccionada;

	public void reservaExperiencia(MouseEvent event) { 
		Cliente cliente=Db.getUserConnected();
		if(checkCamposVacios()) { //chekeamos que los campos estan rellenos
			LocalDate fecha=dpFecha.getValue();   //guardamos los datos recogidos en los campos
			double precioFinal=getPrecio();
			Experiencia expAux=experienciaSeleccionada;
			expAux.setIncio(fecha);
			expAux.setImporteProducto(precioFinal);
			cliente.addProducto(expAux);
			//añadir a la bbdd los billetes cuando la reserva se haya confirmado en el carrito


		}
		else {//este else creo que se puede eliminar
			System.out.println("Algo salió mal...");
		}
		clear();
	}
	
	@FXML
	public void eventActiongetPrecios (ActionEvent event) {
		Object evt=event.getSource();
		if(evt.equals(cbCiudad) || evt.equals(cbExperiencias) || evt.equals(dpFecha) && checkCamposVacios()){
			setTextPrecio();
		}
	}
	
	public boolean checkCamposVacios() {
		if(cbCiudad.getValue()!=null && cbExperiencias.getValue()!=null && dpFecha.getValue()!=null)
			return true;
		else 
			return false;
	}

	//--------------------- Getters and setters  ---------------------------
	
	public void setTextPrecio () {
		if(checkCamposVacios()) {
			double dPrecio = experienciaSeleccionada.getImporteProducto();
			double precio = Math.round(dPrecio*100.0)/100.0;
			txtPrecio.setText(""+precio);
		}
		else {
			txtPrecio.setText("0");
		}
	}

	public double getPrecio () {
		double nb=Double.parseDouble(txtPrecio.getText());
		return nb;
	}
	public void setImagen() {
		if(cbExperiencias.getValue()!=null) {
			URLConnection connection;
			try {
				connection = new URL(experienciaSeleccionada.getUrl()).openConnection();
				connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				img = new Image(connection.getInputStream());
				imageView.setImage(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setExperienciaSeleccionada (ActionEvent event) {
		if(cbExperiencias.getValue()!=null) {
			this.experienciaSeleccionada=cbExperiencias.getValue();
			setImagen();
			txtNombre.setText(experienciaSeleccionada.getNombre());
		}
	}
	
	public void setComboBoxCiudad() {
		ArrayList<String> listaCiudades = dbc.listarCiudadesConExperiencia();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<listaCiudades.size();i++) {
			list.add(listaCiudades.get(i));
		}
		cbCiudad.setItems(list);
	}
	
	public void setComboBoxExperiencia() {
		if(cbCiudad.getValue()!=null) {
			String ciudad=cbCiudad.getValue();
			ArrayList<Experiencia> listaExperiencias=dbp.getExperiencias(ciudad);
			for(int i=0; i<listaExperiencias.size(); i++) {
				System.out.println(listaExperiencias.get(i).getNombre());
			}
			cbExperiencias.setItems(FXCollections.observableList(listaExperiencias));
			cbExperiencias.getSelectionModel().selectFirst();
			cbExperiencias.setCellFactory(new Callback<ListView<Experiencia>,ListCell<Experiencia>>(){
				public ListCell<Experiencia> call(ListView<Experiencia> l){
					return new ListCell<Experiencia>(){
						@Override
						protected void updateItem(Experiencia item, boolean empty) {
							super.updateItem(item, empty);
							if (item == null || empty) {
								setGraphic(null);
							} else {
								setText(item.getNombre());
							}
						}
					} ;
				}
			});
			cbExperiencias.setConverter(new StringConverter<Experiencia>() {
				@Override
				public String toString(Experiencia user) {
					if (user == null){
						return null;
					} else {
						return user.getNombre();
					}
				}
				@Override
				public Experiencia fromString(String userId) {
					return null;
				}
			});	
		}
	}
	
	//--------------  Limiar campos  ------------------
	
	public void clear () {
		txtPrecio.setText("0");
		cbCiudad.setValue(null);
		cbExperiencias.setValue(null);
		dpFecha.setValue(null);
	}
	
}
