package inholland.java.endassignment682474.Controller;

import inholland.java.endassignment682474.model.Database;
import inholland.java.endassignment682474.model.Member;

import java.time.LocalDate;

public class AddMemberController {

    MainController mainController;
    Database database;

    public AddMemberController(MainController mainController, Database db) {
        this.mainController = mainController;
        this.database=db;
    }

    public void addMember(){
        String Firstname = mainController.AddMemberFirstnameTb.getText();
        String Lastname = mainController.AddMemberLastnameTb.getText();
        LocalDate Brithdate = LocalDate.parse(mainController.AddMemberBirthdate.getEditor().getText(), mainController.dateTimeFormatter);
        Member lastmember = database.memberCollection.get(database.memberCollection.size()-1);
        int newindex = lastmember.getId()+1;
        Member member = new Member(newindex,Firstname, Lastname, Brithdate);
        database.memberCollection.add(member);
        mainController.updateMembers();
    }
}
