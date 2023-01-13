package inholland.java.endassignment682474.Controller;

import inholland.java.endassignment682474.model.Database;

import java.time.LocalDate;

public class EditItemController {
    MainController mainController;
    Database database;

    public EditItemController(MainController mainController, Database db) {
        this.mainController = mainController;
        this.database=db;
    }
    public void editItem(){
        String newTitle = mainController.EditTitleTf.getText();
        String newAuthor = mainController.EditAuthorTf.getText();
        mainController.selectedItem.title = newTitle;
        mainController.selectedItem.author = newAuthor;
        mainController.CollectionTab();
        mainController.updateItems();

    }

}
