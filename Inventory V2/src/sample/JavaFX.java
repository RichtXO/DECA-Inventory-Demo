package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import java.sql.*;


/**
 * Random app that access the data table
 */
public class JavaFX extends Application
{

    private ObservableList<ObservableList> data;
    private TableView viewbdr;



    public static void main(String args[])
    {
        launch(args);
    }

    public void start(Stage view)throws Exception
    {

        viewbdr = new TableView();
        data = FXCollections.observableArrayList();
        view.setOnShowing(new EventHandler<WindowEvent>(){
            public void handle(WindowEvent event){

                ResultSet rs;
                Statement tabela;
                String URL = "jdbc:mysql://localhost:3306/Inventory";
                String User = "root";
                String Pass = "root";
                try{

                    System.out.println("in the try method!");

                    Class.forName("com.mysql.jdbc.Driver");


                    System.out.println("Here!");

                    Connection Conexao = DriverManager.getConnection(URL,User,Pass);
                    tabela = Conexao.createStatement();


                    String SQL = "SELECT * FROM inventory;";
                    rs = tabela.executeQuery(SQL);

                    while(rs.next())
                    {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        //All the rows are added here dynamically
                        //To add the all the rows of the table with all columns then use this loop
                        for(int i =1; i<=rs.getMetaData().getColumnCount(); i++)
                        {
                            row.add(rs.getString(i));
                        }

                        data.add(row);

                    }

                    viewbdr.setItems(data);
                    rs.close();

                }
                catch(Exception ex)
                {
                    System.out.println("ERROR!!!");
                    ex.printStackTrace();
                }
            }
        });

        viewbdr.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        viewbdr.setEditable(false);
        viewbdr.setTableMenuButtonVisible(true);

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });


        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>> (){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(1).toString());
            }
        });

        TableColumn amount = new TableColumn("amount");
        amount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>> (){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(2).toString());
            }
        });


        //AmountBox Column
        TableColumn amountBox = new TableColumn("AmountBox");
        amountBox.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>()
        {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param)
            {
                return new SimpleStringProperty(param.getValue().get(3).toString());
            }
        });


        //Charge Column
        TableColumn chargeColumn = new TableColumn("Charge");
        chargeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>()
        {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param)
            {
                return new SimpleStringProperty(param.getValue().get(4).toString());
            }
        });


        viewbdr.getColumns().addAll(name, price, amount, amountBox, chargeColumn);


        Group gp = new Group();
        gp.getChildren().add(viewbdr);

        Scene sc = new Scene(gp);

        view.setScene(sc);
        view.show();
    }
}