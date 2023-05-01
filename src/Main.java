import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends  Application{

    //----------variable interface javafx------------
    TextField nom ,prenom,age;
    Label textLabel,labelTable;
    Button ajouter,siprimer;
    ObservableList<Users> data = FXCollections.observableArrayList();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);



    //------variables connection to database-----------------
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/PROJECTDB";
    private final TableView table = new TableView() ;
    static final String USER = "root";
    static final String PASS = "12345678@";
    public  Connection conn = null;
    public Statement stmt = null;


    //startContent();
    @Override
    public void start(Stage stage) throws Exception {
        alert.setTitle("Infos");

        Class.forName(JDBC_DRIVER);

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        //Interface JavaFX
    BorderPane root = new BorderPane();

     textLabel = new Label("Saisir votre information");
     nom = new TextField();
        nom.setPrefWidth(100);
        nom.setPrefHeight(30);
        nom.setPromptText("NOM");

     prenom = new TextField();
        prenom.setPrefWidth(100);
        prenom.setPrefHeight(30);
        prenom.setPromptText("PRENOM");

     age = new TextField();
        age.setPrefWidth(100);
        age.setPrefHeight(30);
        age.setPromptText("AGE");

                ajouter =  new Button("Envoyer");
        ajouter.setMinWidth(150);
        ajouter.setPrefSize(22,33);

        siprimer =  new Button("Siprimer");
        siprimer.setMinWidth(150);
        siprimer.setPrefSize(22,33);
        HBox hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setPadding(new Insets(10));
        hBox1.setBackground(new Background(new BackgroundFill(null,null,null)));
        hBox1.setMaxWidth(400);
        hBox1.getChildren().addAll(ajouter,siprimer);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setBackground(new Background(new BackgroundFill(null,null,null)));
        vBox.setMaxWidth(400);
        vBox.getChildren().addAll(textLabel,nom,prenom,age,hBox1);
        root.setBottom(vBox);



        labelTable = new Label("Tableau");
    VBox vBox1= new VBox();
        vBox1.setSpacing(10);
        vBox1.setPadding(new Insets(10));
        vBox1.getChildren().addAll(labelTable,table);
        root.setCenter(vBox1);













        //------------------------------------
        table.setEditable(true);

        TableColumn columnID = new TableColumn("ID");
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn columnNom = new TableColumn("Nom");
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn columnPrenom = new TableColumn("Prenom");
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn colomnAge = new TableColumn("Age");
        colomnAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn colomnActions = new TableColumn("Actions");
        colomnActions.setCellValueFactory(new PropertyValueFactory<>("actions"));

        table.getColumns().addAll(columnID,columnNom, columnPrenom, colomnAge );

        columnNom.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNom.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Users,String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Users,String> cellEditEvent) {
                        ((Users) cellEditEvent.getTableView().getItems().get(
                                update(
                                        cellEditEvent.getNewValue(),
                                        cellEditEvent.getRowValue().getPrenom(),
                                        cellEditEvent.getRowValue().getAge(),
                                        cellEditEvent.getRowValue().getId())
                                )
                        ).setNom(cellEditEvent.getNewValue());


                    }
                }
        );
//
//
//        colomnAge.setCellFactory(TextFieldTableCell.forTableColumn());
//        colomnAge.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<Users,String>>() {
//                    @Override
//                    public void handle(TableColumn.CellEditEvent<Users,String> cellEditEvent) {
//                        ( cellEditEvent.getTableView().getItems().get(
//                                cellEditEvent.getTablePosition().getRow())
//                        ).setAge(Integer.parseInt(cellEditEvent.getNewValue()));
//
//
//                    }
//                }
//        );
        columnPrenom.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPrenom.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Users,String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Users,String> cellEditEvent) {
                        ((Users) cellEditEvent.getTableView().getItems().get(
                                update(
                                        cellEditEvent.getRowValue().getNom(),
                                        cellEditEvent.getNewValue(),
                                        cellEditEvent.getRowValue().getAge(),
                                        cellEditEvent.getRowValue().getId())
                                )

                        ).setPrenom(cellEditEvent.getNewValue());


                    }
                }
        );


        alldata();


        Scene scene = new Scene(root,400,600);
        stage.setScene(scene);
        stage.setTitle("TP OOP");
        stage.show();



        ajouter.setOnAction((event -> {



        }));

        siprimer.setOnAction((event -> {

        }));



    }
      


    public  void alldata() throws SQLException {

        stmt = conn.createStatement();
        String sql = "SELECT * FROM users;";
        ResultSet rs= stmt.executeQuery(sql);
        data.clear();
        table.getItems().clear();

        while(rs.next()){
            int id = rs.getInt("id");
            int age1 = rs.getInt("age");
            String nom1 = rs.getString("nom");
            String prenom1 = rs.getString("prenom");

            data.add(new Users(id,nom1,prenom1,age1));
        }
        table.setItems(data);

    }





    public static void main(String[] args) {
        launch(args);
    }

}