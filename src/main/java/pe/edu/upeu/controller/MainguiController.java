package pe.edu.upeu.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Map;

public class MainguiController {
    @FXML BorderPane bp;

    @FXML
    MenuBar menuBar;

    @FXML
    MenuItem menuItem1, menuItem2, menuItem3;

    @FXML
    TabPane tabPane;

    @FXML
    public void initialize(){
        MenuItemListener mi=new MenuItemListener();
        menuItem1.setOnAction(mi::handle);
        menuItem2.setOnAction(mi::handle);
        menuItem3.setOnAction(mi::handle);
    }

    class MenuItemListener{
        // Mapa de menús modificado para incluir el Padrón de Beneficiarios
        Map<String, String[]> menus=Map.of(
                "menuItem1", new String[]{"/view/main_cliente.fxml", "Reg. Cliente", "T"},
                "menuItem2", new String[]{"/view/main_beneficiario.fxml", "Padrón Beneficiarios", "T"},
                "menuItem3", new String[]{"/view/login.fxml", "Salir", "C"}
        );

        public void handle(ActionEvent e){
            String id=((MenuItem)e.getSource()).getId();
            if(menus.containsKey(id)){
                String[] items=menus.get(id);
                if(items[2].equals("C")){
                    Platform.exit();
                    System.exit(0);
                }else{
                    abrirTabPaneFXML(items[0],items[1]);
                }
            }
        }

        private void abrirTabPaneFXML(String fxmlPath, String tittle){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = fxmlLoader.load();
                ScrollPane scrollPane = new ScrollPane(root);
                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(true);
                Tab newTab = new Tab(tittle, scrollPane);

                // Limpiamos los tabs anteriores para que solo se vea la ventana seleccionada
                tabPane.getTabs().clear();
                tabPane.getTabs().add(newTab);
            }catch (IOException ex){
                throw new RuntimeException(ex);
            }
        }
    }
}