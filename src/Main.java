import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

     public void codeLogin(){

     }
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
        textLabel.setTextFill(Color.BLACK);

        textLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");



        nom = new TextField();
        nom.setPrefWidth(100);
        nom.setPrefHeight(30);
        nom.setPromptText("NOM");
        nom.setStyle("-fx-border-color: blue;");


        prenom = new TextField();
        prenom.setPrefWidth(100);
        prenom.setPrefHeight(30);
        prenom.setPromptText("PRENOM");
        prenom.setStyle("-fx-border-color: blue;");


        age = new TextField();
        age.setPrefWidth(100);
        age.setPrefHeight(30);
        age.setPromptText("AGE");
        age.setStyle("-fx-border-color: blue;");

        ajouter =  new Button("Inserer");
        ajouter.setMinWidth(150);
        ajouter.setPrefSize(22,33);
        ajouter.setTextFill(Color.WHITE);
        ajouter.setStyle("-fx-font-size: 18; -fx-font-weight: bold;-fx-background-color: blue;");


        siprimer =  new Button("Suprimer");
        siprimer.setMinWidth(150);
        siprimer.setPrefSize(22,33);
        siprimer.setTextFill(Color.WHITE);
        siprimer.setStyle("-fx-font-size: 18; -fx-font-weight: bold;-fx-background-color: red;");


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
        vBox.setAlignment(Pos.CENTER);
        root.setBottom(vBox);



        labelTable = new Label("Tableau");
        labelTable.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        VBox vBox1= new VBox();
        vBox1.setSpacing(10);
        vBox1.setPadding(new Insets(10));
        vBox1.getChildren().addAll(labelTable,table);
        vBox1.setAlignment(Pos.CENTER);

        root.setCenter(vBox1);













        //------------------------------------
        table.setEditable(true);

        TableColumn columnID = new TableColumn("ID");
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnID.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        columnID.setPrefWidth(80.0);


        TableColumn columnNom = new TableColumn("Nom");
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnNom.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        columnNom.setPrefWidth(100.0);


        TableColumn columnPrenom = new TableColumn("Prenom");
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnPrenom.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        columnPrenom.setPrefWidth(100.0);


        TableColumn colomnAge = new TableColumn("Age");
        colomnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colomnAge.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        colomnAge.setPrefWidth(100.0);



        table.getColumns().addAll(columnID,columnNom, columnPrenom, colomnAge );

        columnNom.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNom.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Users,String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Users,String> cellEditEvent) {
                        ((Users) cellEditEvent.getTableView().getItems().get(
                                modifier(
                                        cellEditEvent.getNewValue(),
                                        cellEditEvent.getRowValue().getPrenom(),
                                        cellEditEvent.getRowValue().getAge(),
                                        cellEditEvent.getRowValue().getId())
                                )
                        ).setNom(cellEditEvent.getNewValue());


                    }
                }
        );


//        colomnAge.setCellFactory(TextFieldTableCell.forTableColumn());
//        colomnAge.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<Users,String>>() {
//                    @Override
//                    public void handle(TableColumn.CellEditEvent<Users,String> cellEditEvent) {
////                        ( cellEditEvent.getTableView().getItems().get(
////                                cellEditEvent.getTablePosition().getRow())
////                        ).setAge(Integer.parseInt(cellEditEvent.getNewValue()));
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
                                modifier(
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
            insert();
        }));

        siprimer.setOnAction((event -> {
            Users user = (Users) table.getSelectionModel().getSelectedItem();
            try {
                if (!table.getSelectionModel().isEmpty()) {
                    suprimer(user.getId());
                }else {
                    alert.setContentText("Selection un Utilisateur");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }));



    }
        public int modifier(String nom,String prenom,int age,int id){
            try{
                    stmt = conn.createStatement();
                    String sqlInsert = "UPDATE users SET nom ='"+nom+"', prenom = '"+prenom+"' ,age ='"+age+"' WHERE id="+id+"";
                    stmt.executeUpdate(sqlInsert);

                    alldata();
                    alert.setContentText("donnée modifier");
                    alert.showAndWait();
                    alldata();
        return 1;

            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }
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



    public  void insert(){
        try{

            if (!nom.getText().isEmpty() && !prenom.getText().isEmpty() && !age.getText().isEmpty()){
              if(Integer.parseInt(age.getText())>=0){
                  stmt = conn.createStatement();

                  String sqlInsert = "INSERT INTO users (nom, prenom, age) VALUES('"+nom.getText()+"', '"+prenom.getText()+"', "+Integer.parseInt(age.getText())+");";
                  stmt.executeUpdate(sqlInsert);

                  clearData();
                  alldata();
                  alert.setContentText("donnée ajouter");
                  alert.showAndWait();
              }{
                    alert.setContentText("Age invalid");
                    alert.showAndWait();
              }
            }else {
//                String rs=validationInput(nom.getText(),prenom.getText(),age.getText());
                alert.setContentText("donnée  vide");
                alert.showAndWait();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  String validationInput(String nom1,String prenom1,String age1) {
        String result=null;
      try {
          if (nom1.isEmpty() && prenom1.isEmpty() && age1.isEmpty()){
              this.prenom.setStyle("-fx-border-color: red;");
              this.nom.setStyle("-fx-border-color: red;");
              this.age.setStyle("-fx-border-color: red;");
               result="nom ,prenom ,age";

          }  if (prenom1.isEmpty()  ) {
              this.prenom.setStyle("-fx-border-color: red;");
              return result+ "prenom , nom";
          }  if (age1.isEmpty() && nom1.isEmpty()) {
              this.age.setStyle("-fx-border-color: red;");
              this.nom.setStyle("-fx-border-color: red;");
              return "age nom";

          }if (age1.isEmpty() && prenom1.isEmpty()){
              this.nom.setStyle("-fx-border-color: red;");
              return "nom";

          }
      }catch (Exception e){

          e.printStackTrace();
      }
        return "";
    }
    public  void suprimer(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id ='"+id+"'";
        stmt = conn.createStatement();
        int rowsDeleted = stmt.executeUpdate(sql);
        if (rowsDeleted > 0) {
            System.out.println("Row deleted successfully.");
        } else {
            System.out.println("Row not found or not deleted.");
        }
    alldata();



    }
    public void clearData(){
        nom.setText("");
        prenom.setText("");
        age.setText("");
        this.prenom.setStyle("-fx-border-color: blue;");
        this.nom.setStyle("-fx-border-color: blue;");
        this.age.setStyle("-fx-border-color: blue;");
    }



    public static void main(String[] args) {
        launch(args);
    }

}