package application;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Code.Cliente;
import Code.Experiencia;
import db.Db;
import db.DbCiudad;
import db.DbProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Clase controladora de la ventana de compra de experiencias.
 */
public class ExperienciasClass implements Initializable{
	
	@FXML private Text txtPrecio;
	@FXML private Text txtNombre;
	@FXML private Text txtNumExp;
	@FXML private ComboBox<String> cbCiudad;
	@FXML private ComboBox<Experiencia> cbExperiencias;
	@FXML private DatePicker dpFecha;
	@FXML private ImageView imageView; 
	@FXML private Button btnAddCarrito;
	@FXML private Button btnMasExp;
	@FXML private Button btnMenosExp;
	@FXML private Image img;
	private static DbCiudad dbc = new DbCiudad();
	private static DbProducto dbp = new DbProducto();
	private Experiencia experienciaSeleccionada;
	
	/**
	 * Reserva las experiencias añadiendolo al array list de Producto llamada carrito del Cliente conectado.
	 * @param event Evento de ratón (el usuario pulsa el botón).
	 */
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
	/**
	 * Imprime en la interface el precio cuando uno de los elementos de la interface es pulsado
	 * @param event Evento de ratón (el usuario pulsa el botón).
	 */
	@FXML
	public void eventActiongetPrecios (ActionEvent event) {
		Object evt=event.getSource();
		if(evt.equals(cbCiudad) || evt.equals(cbExperiencias) || evt.equals(dpFecha) && checkCamposVacios()){
			setTextPrecio();
		}
	}
	/**
	 * Valida que los campos estan completos.
	 * @return Devuelve true si los campos estan completos.
	 */
	public boolean checkCamposVacios() {
		if(cbCiudad.getValue()!=null && cbExperiencias.getValue()!=null && dpFecha.getValue()!=null)
			return true;
		else 
			return false;
	}
	/**
	 * Suma el numero tickets para la experiencia segun se pulasn los botones de masBilletes o menosBilletes y actualiza el precio si los campos estan completos
	 * @param event
	 */
	@FXML
	private void precioSumaResta(ActionEvent event) {  
		Object evt=event.getSource();
		int nb=getNumExp();
		if(evt.equals(btnMasExp)) {
			nb++;
			setNumExp(nb);
			setTextPrecio();
		}	
		else if (evt.equals(btnMenosExp)) {
			nb--;
			if(nb>0) {
				setNumExp(nb);
				setTextPrecio();
			}
			else
				setNumExp(0);
		}
	}
	
	
	//--------------------- Getters and setters  ---------------------------
	
	public void setNumExp (int nb) {
		if(nb<1) 
			txtNumExp.setText("1");
		else
			txtNumExp.setText(""+ nb);
	}
	
	public int getNumExp () {
		int nb=Integer.parseInt(txtNumExp.getText());
		return nb;
	}
	
	public void setTextPrecio () {
		if(checkCamposVacios()) {
			double dPrecio = experienciaSeleccionada.getImporteProducto()*getNumExp();
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
			cbExperiencias.setItems(FXCollections.observableList(listaExperiencias));
			//cbExperiencias.getSelectionModel().selectFirst();
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

	public void setComboBoxExperienciaP() {
		if(cbCiudad.getValue()!=null) {
			String ciudad=cbCiudad.getValue();
			ArrayList<Experiencia> listaExperiencias=dbp.getExperiencias(ciudad);
			cbExperiencias.setItems(FXCollections.observableList(listaExperiencias));
			//cbExperiencias.getSelectionModel().selectFirst();
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
				public String toString(Experiencia exp) {
					if (exp == null){
						return null;
					} else {
						return exp.getNombre();
					}
				}
				@Override
				public Experiencia fromString(String exp) {
					return null;
				}
			});	
		}
	}
	
	//Setero del calendario entrada
	public void setDatePickerFecha() {
		LocalDate hoy=LocalDate.now();
	    final Callback<DatePicker, DateCell> dayCellFactory = 
	        new Callback<DatePicker, DateCell>() {
	            @Override
	            public DateCell call(final DatePicker datePicker) {
	                return new DateCell() {
	                    @Override
	                    public void updateItem(LocalDate item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (item.isBefore(
	                                hoy.plusDays(1))
	                            ) {
	                                setDisable(true);
	                                setStyle("-fx-background-color: #ffc0cb;");
	                        }
	                }
	            };
	        }
	    };
	    dpFecha.setShowWeekNumbers(false);
	    dpFecha.setDayCellFactory(dayCellFactory);
	    dpFecha.setValue(hoy.plusDays(1));
	}
	
	//--------------  Limiar campos  ------------------
	/**
	 * Deja los campos de la interface con los valores de inicio.
	 */
	public void clear () {
		txtPrecio.setText("0");
		cbCiudad.setValue(null);
		cbExperiencias.setValue(null);
		dpFecha.setValue(null);
	}
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDatePickerFecha();
		
	}
}