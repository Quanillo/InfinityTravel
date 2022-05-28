package application;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BBDD.Db;
import BBDD.DbCiudad;
import BBDD.DbProducto;
import Code.Alojamiento;
import Code.Cliente;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;

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
	
	public void reservaAlojamiento(MouseEvent event) { 
		Cliente cliente=Db.getUserConnected();
		if(checkCamposVacios()) { //chekeamos que los campos estan rellenos
			LocalDate entrada=dpEntrada.getValue();   //guardamos los datos recogidos en los campos
			LocalDate salida=dpSalida.getValue();
			double precioFinal=getPrecio();
			Alojamiento alojAux=alojamientoSeleccionado;
			alojAux.setIncio(entrada);
			alojAux.setFin(salida);
			alojAux.setImporteProducto(precioFinal);
			cliente.addProducto(alojAux);
			//a�adir a la bbdd los billetes cuando la reserva se haya confirmado en el carrito


		}
		else {//este else creo que se puede eliminar
			System.out.println("Algo sali� mal...");
		}
		clear();
	}

	@FXML
	public void eventActiongetPrecios (ActionEvent event) {
		Object evt=event.getSource();
		if(evt.equals(cbCiudad) || evt.equals(cbAlojamientos) || evt.equals(dpEntrada) || evt.equals(dpSalida)&& checkCamposVacios()){
			//txtPrecio.setText("0");txtPrecioBase.setText("0");
			setDatePickerSalida();
			setTextPrecio(getNumNoches());
			System.out.println(getNumNoches());
		}
	}

	public boolean checkCamposVacios() {
		if(cbCiudad.getValue()!=null && cbAlojamientos.getValue()!=null && dpEntrada.getValue()!=null && dpSalida.getValue()!=null && getNumNoches()>=1 ) 
			return true;
		else 
			return false;
	}

	//--------------------- Getters and setters  ---------------------------
	
	public int getNumNoches() { 
		int noches = 0;
		if(dpEntrada.getValue()!=null && dpSalida.getValue()!=null) {
			LocalDate entrada=dpEntrada.getValue();
			LocalDate salida=dpSalida.getValue();
			noches = (int) ChronoUnit.DAYS.between(entrada, salida);
		}
		return noches;
	}

	public void setTextPrecio (int numNoches) {
		double dPrecio = 0;
		if(checkCamposVacios()) {
			dPrecio = alojamientoSeleccionado.getImporteProducto() * numNoches;
			double precio = Math.round(dPrecio*100.0)/100.0;
			txtPrecio.setText(""+precio);
			txtPrecioBase.setText(""+alojamientoSeleccionado.getImporteProducto());
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
			//cbAlojamientos.getSelectionModel().selectFirst();
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
	

	//Setero del calendario entrada
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
	                        long p = ChronoUnit.DAYS.between(
	                                hoy, item
	                        );
	                        setTooltip(new Tooltip(
	                            "You're about to stay for " + p + " days")
	                        );
	                }
	            };
	        }
	    };
	    dpEntrada.setShowWeekNumbers(false);
	    dpEntrada.setDayCellFactory(dayCellFactory);
	    dpEntrada.setValue(hoy.plusDays(1));
	}
	//seteo del calendario salida
	public void setDatePickerSalida () {
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
	                        long p = ChronoUnit.DAYS.between(
	                                dpEntrada.getValue(), item
	                        );
	                        setTooltip(new Tooltip(
	                            "You're about to stay for " + p + " days")
	                        );
	                }
	            };
	        }
	    };
	    dpSalida.setShowWeekNumbers(false);
	    dpSalida.setDayCellFactory(dayCellFactory);
	    dpSalida.setValue(dpEntrada.getValue().plusDays(1));
	}
	
    
	//--------------  Limpieza  ------------------
	
	public void clear () {
		txtPrecio.setText("0");
		txtPrecioBase.setText("0");
		cbCiudad.setValue(null);
		cbAlojamientos.setValue(null);
		dpEntrada.setValue(null);
		dpSalida.setValue(null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDatePickerEntrada();
		
	}
	
}


