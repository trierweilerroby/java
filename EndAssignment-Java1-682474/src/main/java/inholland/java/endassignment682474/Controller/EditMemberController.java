package inholland.java.endassignment682474.Controller;

import inholland.java.endassignment682474.model.Database;

import java.time.LocalDate;

public class EditMemberController {
    MainController mainController;
    Database database;

    public EditMemberController(MainController mainController, Database db) {
        this.mainController = mainController;
        this.database=db;
    }


    public void editMember(){
        String newFirstname = mainController.EditMemberFirstnameTb.getText();
        String newLastname = mainController.EditMemberLastnameTb.getText();
        LocalDate newBirthdate = LocalDate.parse(mainController.EditMemberBirthdate.getEditor().getText(), mainController.dateTimeFormatter);
        mainController.selectedMember.setFirstname(newFirstname);
        mainController.selectedMember.setLastname(newLastname);
        mainController.selectedMember.setBirthdate(newBirthdate);
        mainController.updateMembers();

    }
}
