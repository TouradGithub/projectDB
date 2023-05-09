import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends Application {
    ConnectionDB connectionDB =new ConnectionDB();
    TextField email,password;
    Label textLabel,validEmail;
    Button login;

    public Login() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void start(Stage stage) throws Exception {




        //Interface JavaFX
        BorderPane root = new BorderPane();

        textLabel = new Label("Login Admin");
        textLabel.setTextFill(Color.BLACK);

        textLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");



        email = new TextField();
        email.setPrefWidth(100);
        email.setPrefHeight(30);
        email.setPromptText("EMAIL");
        email.setStyle("-fx-border-color: blue;");


        password = new TextField();
        password.setPrefWidth(100);
        password.setPrefHeight(30);
        password.setPromptText("PASSWORD");
        password.setStyle("-fx-border-color: blue;");


        validEmail =new Label();
        validEmail.setTextFill(Color.RED);


        login =  new Button("LOGIN");
        login.setMinWidth(150);
        login.setPrefSize(22,33);
        login.setTextFill(Color.WHITE);
        login.setStyle("-fx-font-size: 18; -fx-font-weight: bold;-fx-background-color: blue;");




        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setBackground(new Background(new BackgroundFill(null,null,null)));
        vBox.setMaxWidth(400);
        vBox.getChildren().addAll(textLabel,email,password, validEmail,login);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(50));
        vBox.setAlignment(Pos.CENTER);
        root.setCenter(vBox);


        Scene scene = new Scene(root,400,600);
        stage.setScene(scene);
        stage.setTitle("PAGE LOGIN");
        stage.show();



        login.setOnAction((event -> {
            try {
                if (logIn()){
                    stage.hide();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    public boolean logIn() throws SQLException {
//        stmt = conn.createStatement();
        connectionDB.setStmt(connectionDB.getConn().createStatement());
        String sql = "SELECT id FROM  admin WHERE email='"+email.getText()+"' AND password='"+password.getText()+"';";
        System.out.println(sql);
        ResultSet rs = connectionDB.getStmt().executeQuery(sql);
        System.out.println(rs);
//        if(rs){
//            return true;
//        }
        return false;

    }
}
