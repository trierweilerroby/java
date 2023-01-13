package inholland.java.endassignment682474.Controller;

import inholland.java.endassignment682474.HelloApplication;
import inholland.java.endassignment682474.model.Database;
import inholland.java.endassignment682474.model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController {
    public Database database;
    public User currentuser;

    public LoginController(Database database){
        this.database = database;
    }

    @FXML
    private Button LoginBtn;
    @FXML
    private TextField usernameTb;
    @FXML
    private TextField passwordTb;
    @FXML
    private Label LoginLabel;
    @FXML
    private void LoginClick() throws IOException {
        boolean userExist = false;
        User user2 = new User(usernameTb.getText(),passwordTb.getText());
        for (User user:database.userCollection) {
            if (user.username.equals(user2.username) && user.password.equals(user2.password)){
                userExist = true;
            }
        }
        if (userExist){
            try {
    //close login
                currentuser = user2;
    //open main.fxml
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
                MainController mainController = new MainController(database,currentuser);
                fxmlLoader.setController(mainController);
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage2 = new Stage();
                stage2.setScene(scene);
                stage2.setTitle("Main");
                stage2.show();
                mainController.welcomeLabel.setText("Welcome "+ currentuser.username);

                Stage stage = (Stage) LoginBtn.getScene().getWindow();
                stage.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        else{
            LoginLabel.setText("Invalid username/password combination");
        }
    }

}
