package inholland.java.endassignment682474;

import inholland.java.endassignment682474.Controller.LoginController;
import inholland.java.endassignment682474.model.Database;
import inholland.java.endassignment682474.model.Item;
import inholland.java.endassignment682474.model.Member;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private Database database;
    private String filename = "database.ser";

    @Override
    public void start(Stage stage) throws IOException {
        try {
            database = new Database();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
            fxmlLoader.setController(new LoginController(database));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop() throws Exception{
        serialize();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
    public void serialize(){
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(database.getItemCollection());
            out.writeObject(database.getMemberCollection());

            out.close();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}