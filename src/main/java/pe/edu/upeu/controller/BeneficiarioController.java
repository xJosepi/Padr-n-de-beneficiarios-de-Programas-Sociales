package pe.edu.upeu.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pe.edu.upeu.model.Beneficiario;
import pe.edu.upeu.service.BeneficiarioService;
import pe.edu.upeu.service.BeneficiarioServiceImp;

import java.time.LocalDate;

public class BeneficiarioController {

    // Variables conectadas al FXML (Agregado txtDni)
    @FXML Button btnGuardar, btnActualizar, btnLimpiar, btnEliminar;
    @FXML TextField txtDni, txtFolio, txtNombre, txtMonto;
    @FXML ComboBox<String> cmbPrograma;
    @FXML DatePicker dpFechaAlta;

    // Variables para la tabla (Agregado colDni)
    @FXML TableView<Beneficiario> regBeneficiarioTabla;
    private TableColumn<Beneficiario, String> colDni, colFolio, colNombre, colPrograma, colFecha;
    private TableColumn<Beneficiario, Double> colMonto;

    ObservableList<Beneficiario> beneficiarios;
    int index = -1; // Para saber qué fila se seleccionó

    // Llamamos a nuestro servicio
    BeneficiarioService bs = BeneficiarioServiceImp.getInstance();

    @FXML
    public void initialize() {

        // 1. Llenar el ComboBox con opciones (Nombres actualizados)
        cmbPrograma.setItems(FXCollections.observableArrayList(
                "Pensión 65", "Qali Warma", "Vaso de Leche", "Beca 18"
        ));

        // Poner la fecha de hoy por defecto
        dpFechaAlta.setValue(LocalDate.now());

        // 2. Preparar la tabla y cargar datos
        definirColumnas();
        listar();
        agregarEventoSeleccion();
        desacActButton(true);

        // 3. Darle funciones a los botones
        btnEliminar.setOnAction(e -> {
            if(index != -1) { bs.delete(index); listar(); limpiarForm(); }
        });

        btnLimpiar.setOnAction(e -> limpiarForm());

        btnGuardar.setOnAction(e -> guardarBeneficiario());

        btnActualizar.setOnAction(e -> {
            if (index != -1) guardarBeneficiario();
        });
    }

    void desacActButton(boolean valor) {
        btnActualizar.setDisable(valor);
        btnEliminar.setDisable(valor);
        btnGuardar.setDisable(!valor);
    }

    void guardarBeneficiario() {
        Beneficiario b = new Beneficiario();
        b.setDni(txtDni.getText()); // Agregado captura del DNI
        b.setFolio(txtFolio.getText());
        b.setNombre(txtNombre.getText());
        b.setPrograma(cmbPrograma.getValue());
        b.setMontoMensual(Double.parseDouble(txtMonto.getText()));
        b.setFechaAlta(dpFechaAlta.getValue());

        if (index == -1) {
            bs.save(b); // Nuevo registro
        } else {
            bs.update(b, index); // Actualizar registro
        }
        limpiarForm();
        listar();
    }

    void limpiarForm() {
        txtDni.setText(""); // Agregado limpiar DNI
        txtFolio.setText("");
        txtNombre.setText("");
        txtMonto.setText("");
        cmbPrograma.setValue(null);
        dpFechaAlta.setValue(LocalDate.now());

        index = -1;
        regBeneficiarioTabla.getSelectionModel().clearSelection();
        desacActButton(true);
    }

    public void definirColumnas() {
        colDni = new TableColumn<>("DNI"); // Definir columna DNI
        colFolio = new TableColumn<>("Folio");
        colNombre = new TableColumn<>("Nombre");
        colPrograma = new TableColumn<>("Programa");
        colMonto = new TableColumn<>("Monto");
        colFecha = new TableColumn<>("Fecha Alta");

        // Agregar columna DNI al principio de la tabla
        regBeneficiarioTabla.getColumns().addAll(colDni, colFolio, colNombre, colPrograma, colMonto, colFecha);
    }

    private void listar() {
        colDni.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDni())); // Listar DNI
        colFolio.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFolio()));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colPrograma.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrograma()));
        colMonto.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getMontoMensual()).asObject());
        colFecha.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaAlta().toString()));

        beneficiarios = FXCollections.observableArrayList(bs.findAll());
        regBeneficiarioTabla.setItems(beneficiarios);
    }

    private void agregarEventoSeleccion() {
        regBeneficiarioTabla.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if(newV != null) {
                index = regBeneficiarioTabla.getItems().indexOf(newV);
                txtDni.setText(newV.getDni()); // Mostrar DNI al seleccionar
                txtFolio.setText(newV.getFolio());
                txtNombre.setText(newV.getNombre());
                cmbPrograma.setValue(newV.getPrograma());
                txtMonto.setText(String.valueOf(newV.getMontoMensual()));
                dpFechaAlta.setValue(newV.getFechaAlta());
                desacActButton(false); // Activa botones Editar/Eliminar
            }
        });
    }
}