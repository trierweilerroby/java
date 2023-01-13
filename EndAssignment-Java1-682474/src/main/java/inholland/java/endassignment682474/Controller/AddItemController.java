package inholland.java.endassignment682474.Controller;

import inholland.java.endassignment682474.model.Database;
import inholland.java.endassignment682474.model.Item;
import inholland.java.endassignment682474.model.Member;

public class AddItemController {
    MainController mainController;
    Database database;

    public AddItemController(MainController mainController, Database db) {
        this.mainController = mainController;
        this.database=db;
    }
    public void addItem(){
        String title = mainController.AuthorPaneTf.getText();
        String author = mainController.TitlePaneTf.getText();
        Item lastItem = database.itemCollection.get(database.itemCollection.size()-1);
        int newItem = lastItem.getId()+1;
        Item item =new Item(newItem,title,author);
        mainController.database.itemCollection.add(item);
        mainController.items.add(item);
        mainController.updateItems();
    }
}
