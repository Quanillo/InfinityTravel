package application;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Code.Alojamiento;
import Code.Cliente;
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
 * Clase controladora de la ventana de compra de alojamientos.
 */
public class AlojamientoClass implements Initializable{
	
	@FXML private Text txtPrecio;
	@FXML private Text txtPrecioBase;
	@FXML private Text txtNombre;
	@FXML private ComboBox<String> cbCiudad;
	@FXML private ComboBox<Alojamiento> cbAlojamientos;
	@FXML private DatePicker dpEntrada;
	@FXML private DatePicker dpSalida;
	@FXML private ImageView imageView; 
	@FXML private Button btnAddCarrito;
	@FXML private Image img;
	private static DbCiudad dbc = new DbCiudad();
	private static DbProducto dbp = new DbProducto();
	private Alojamiento alojamientoSeleccionado;
	

	/**
	 * Reserva el alojamiento añadiendolo al array list de Producto llamada carrito del Cliente conectado.
	 * @param event Evento de ratón (el usuario pulsa el botón).
	 */
	public void reservaAlojamiento(MouseEvent event) { 
		Cliente cliente=Db.getUserConnected();
		if(checkCamposVacios()) { 
			LocalDate entrada=dpEntrada.getValue();   
			LocalDate salida=dpSalida.getValue();
			double precioFinal=alojamientoSeleccionado.calculaPrecio(getNumNoches());
			Alojamiento alojAux=alojamientoSeleccionado;
			alojAux.setIncio(entrada);
			alojAux.setFin(salida);
			alojAux.setImporteProducto(precioFinal);
			cliente.addProducto(alojAux);
		}
		else {//este else creo que se puede eliminar
			System.out.println("Algo salió mal...");
		}
		clear();
	}
	
	/**
	 * Setea el precio en la interface cada vez que el usuario cambia algun parámetro en la interface.
	 * @param event Evento de ratón (el usuario pulsa el botón).
	 */
	@FXML
	public void eventActiongetPrecios (ActionEvent event) {
		Object evt=event.getSource();
		if(evt.equals(cbCiudad) || evt.equals(cbAlojamientos) || evt.equals(dpEntrada) || evt.equals(dpSalida) && checkCamposVacios()){
			setTextPrecio(getNumNoches());
		}
	}
	
	/**
	 * Valida que los campos estan completos.
	 * @return Devuelve true si los campos estan completos.
	 */
	public boolean checkCamposVacios() {
		if(cbCiudad.getValue()!=null && cbAlojamientos.getValue()!=null && dpEntrada.getValue()!=null && dpSalida.getValue()!=null && getNumNoches()>=1 ) 
			return true;
		else 
			return false;
	}

	//--------------------- Getters and setters  ---------------------------

	/**
	 * Lee y los parametros de fecha introducidos por el usuario y los guarda en el objeto Alojamiento
	 * utilizando el método getNumNoches para calcular el número de noches seleccionadas por el usuario.
	 * @return
	 */
	public int getNumNoches() { 
		int noches = 0;
		if(dpEntrada.getValue()!=null && dpSalida.getValue()!=null) {
			alojamientoSeleccionado.setIncio(dpEntrada.getValue());
			alojamientoSeleccionado.setFin(dpSalida.getValue());
			noches = alojamientoSeleccionado.getNumNoches();
		}
		return noches;
	}
	/**
	 * Imprime el precio en la interface con los parámetros introducidos por el cliente
	 * @param numNoches Numero de noches.
	 */
	public void setTextPrecio (int numNoches) {
		if(checkCamposVacios()) {	
			double precio = alojamientoSeleccionado.calculaPrecio(numNoches);  //, alojamientoSeleccionado.getImporteProducto()
			txtPrecio.setText(""+precio);
			txtPrecioBase.setText(""+alojamientoSeleccionado.getImporteProducto());
		}
		else {
			txtPrecio.setText("0");
		}
	}
	
	/**
	 * Imprime la imagen en la interface del alojamiento seleccionado. Esta es una url almacenada en la bbdd.
	 */
	public void setImagen() {
		if(cbAlojamientos.getValue()!=null) {
			URLConnection connection;
			try {
				connection = new URL(alojamientoSeleccionado.getUrl()).openConnection();
				connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
				img = new Image(connection.getInputStream());
				imageView.setImage(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Selecciona el alojamiento seleccionado en el combobox cbAlojamientos y lo guarda en la variable alojamientoSeleccionado.
	 * @param event
	 */
	public void setAlojamientoSeleccionado (ActionEvent event) {
		if(cbAlojamientos.getValue()!=null) {
			this.alojamientoSeleccionado=cbAlojamientos.getValue();
			setImagen();
			txtNombre.setText(alojamientoSeleccionado.getNombre());
		}
	}
	
	//--------------  SET COMBOBOXES  ------------------

	public void setComboBoxCiudad() {
		ArrayList<String> listaCiudades = dbc.listarCiudadesConAlojamiento();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<listaCiudades.size();i++) {
			list.add(listaCiudades.get(i));
		}
		cbCiudad.setItems(list);
	}
	
	public void setComboBoxAlojamiento() {
		if(cbCiudad.getValue()!=null) {
			String ciudad=cbCiudad.getValue();
			ArrayList<Alojamiento> listaAlojamientos=dbp.getAlojamientos(ciudad);
			cbAlojamientos.setItems(FXCollections.observableList(listaAlojamientos));
			cbAlojamientos.setCellFactory(new Callback<ListView<Alojamiento>,ListCell<Alojamiento>>(){
				public ListCell<Alojamiento> call(ListView<Alojamiento> l){
					return new ListCell<Alojamiento>(){
						@Override
						protected void updateItem(Alojamiento item, boolean empty) {
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
			cbAlojamientos.setConverter(new StringConverter<Alojamiento>() {
				@Override
				public String toString(Alojamiento aloj) {
					if (aloj == null){
						return null;
					} else {
						return aloj.getNombre();
					}
				}
				@Override
				public Alojamiento fromString(String aloj) {
					return null;
				}
			});	
		}
	}
	

	//Setero del calendario entrada.
	public void setDatePickerEntrada () {
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
	    dpEntrada.setShowWeekNumbers(false);
	    dpEntrada.setDayCellFactory(dayCellFactory);
	    dpEntrada.setValue(hoy.plusDays(1));
	}
	//seteo del calendario salida.
	public void setDatePickerSalida () {
	   try {
		final Callback<DatePicker, DateCell> dayCellFactory = 
	        new Callback<DatePicker, DateCell>() {
	            @Override
	            public DateCell call(final DatePicker datePicker) {
	                return new DateCell() {
	                    @Override
	                    public void updateItem(LocalDate item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (item.isBefore(
	                                dpEntrada.getValue().plusDays(1))
	                            ) {
	                                setDisable(true);
	                                setStyle("-fx-background-color: #ffc0cb;");
	                        }
	                }
	            };
	        }
	    };
	    dpSalida.setShowWeekNumbers(false);
	    dpSalida.setDayCellFactory(dayCellFactory);
	    dpSalida.setValue(dpEntrada.getValue().plusDays(1));
	   }catch(NullPointerException e) {
		   
	   }
	}
	
    
	//--------------  Limpieza  ------------------
	/**
	 * Deja los campos de la interface con los valores de inicio.
	 */
	public void clear () {
		txtPrecio.setText("0");
		txtPrecioBase.setText("0");
		cbCiudad.setValue(null);
		cbAlojamientos.setValue(null);
		dpSalida.setValue(null);
		dpEntrada.setValue(null);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDatePickerEntrada();
		
	}
	
}
