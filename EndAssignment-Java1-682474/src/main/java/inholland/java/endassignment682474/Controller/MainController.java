package inholland.java.endassignment682474.Controller;

import inholland.java.endassignment682474.model.Database;
import inholland.java.endassignment682474.model.Item;
import inholland.java.endassignment682474.model.Member;
import inholland.java.endassignment682474.model.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public DateTimeFormatter dateTimeFormatter;
    ObservableList<Item> items;
    @FXML
    public Label welcomeLabel;
    @FXML
    TableColumn<Item, String> ItemCodeColumn;
    @FXML
    TableColumn<Item, String> AvailableItemColumn;
    @FXML
    TableColumn<Item, String> TitleItemColumn;
    @FXML
    TableColumn<Item, String> AuthorItemColumn;
    @FXML
    private TextField ItemCodeTf;
    @FXML
    private TextField MemberIdentifierTf;
    @FXML
    private Button LendItemBtn;
    @FXML
    private Label ErrorLend;
    @FXML
    private Label ErrorReceive;
    @FXML
    private TextField CodeItemTf;
    @FXML
    private Button ReceiveItemBtn;
    @FXML
    private Pane ItemsPane;
    @FXML
    private TableView<Item> ItemTv;
    @FXML
    private Button AddItemBtn;
    @FXML
    public TextField AuthorPaneTf;
    @FXML
    public TextField TitlePaneTf;
    @FXML
    private Pane AddItemPane;
    @FXML
    private Button AddItemAddBtn;
    @FXML
    private Pane EditItemPane;
    @FXML
    public TextField EditTitleTf;
    @FXML
    public TextField EditAuthorTf;
    @FXML
    private Button EditPaneBtn;
    @FXML
    private Pane AddMemberPane;
    @FXML
    public TextField AddMemberFirstnameTb;
    @FXML
    public TextField AddMemberLastnameTb;
    @FXML
    public DatePicker AddMemberBirthdate;
    @FXML
    private Button AddMemberAddBtn;
    @FXML
    private Button EditItemBtn;
    @FXML
    private Button CancelEditBtn;
    @FXML
    private Button AddMemberBtn;
    @FXML
    private Button EditMemberBtn;
    @FXML
    public TextField EditMemberFirstnameTb;
    @FXML
    public TextField EditMemberLastnameTb;
    @FXML
    public DatePicker EditMemberBirthdate;
    public Item selectedItem;
    public final Database database;
    private final User currentUser;
    public Member selectedMember;
    @FXML
    private Button DeleteItemBtn;
    @FXML
    private Button AddItemCancelBtn;
    //Member Tab
    @FXML
    private TableView MemberTv;
    @FXML
    private Pane MemberPane;
    @FXML
    private Button DeleteMemberBtn;
    @FXML
    private Button AddMemberCancelBtn;
    @FXML
    private Pane EditMemberPane;
    @FXML
    private Button EditMemberEditBtn;
    @FXML
    private Button EditMemberCancel;

    @FXML
    private Label LateLabel;
    @FXML
    private Label FineLabel;
    @FXML
    private Button PayBtn;

    @FXML
    private void PayBtnClick(){
        int itemCode = Integer.parseInt(CodeItemTf.getText());
        Item item = database.getItemById(itemCode);
        database.Pay(item);
        ErrorReceive.setText("");
    }

    public MainController(Database database, User currentuser) {
        this.database = database;
        //addItemsToTableView();
        this.currentUser = currentuser;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    //lending tab
    @FXML
    private void LendItemClick() {
        try {
            int itemCode = Integer.parseInt(ItemCodeTf.getText());
            int memberId = Integer.parseInt(MemberIdentifierTf.getText());

            Item item = database.getItemById(itemCode);
            Member member = database.getMemberById(memberId);

            database.lendToMember(item, member);
            updateItems();
            //ErrorLend.setText(member.firstname);
        } catch (Exception e) {
            ErrorLend.setText("Error with lending a item");
        }


    }

    @FXML
    private void ReceiveItem() {
        try {
            int itemCode = Integer.parseInt(CodeItemTf.getText());
            Item item = database.getItemById(itemCode);
            database.receiveItem(item);
        } catch (Exception e) {
            ErrorReceive.setText("This item has not been lend");
        }
    }

    //Items Tab
    @FXML
    public void CollectionTab() {
        AddItemPane.setVisible(false);
        EditItemPane.setVisible(false);
        ItemsPane.setVisible(true);
        updateItems();
        ItemTv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> observableValue, Item oldItem, Item newItem) {
                selectedItem = (Item) ItemTv.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void updateItems() {
        ItemTv.getItems().clear();
        items = FXCollections.observableArrayList(database.getItemCollection());
        ItemTv.setItems(items);
    }
    @FXML
    private void AddItemClick() {
        (ItemsPane).setVisible(false);
        (AddItemPane).setVisible(true);
    }

    @FXML
    private void EditItemClick() {
        ItemsPane.setVisible(false);
        EditItemPane.setVisible(true);

        EditAuthorTf.setText(selectedItem.getAuthor());
        EditTitleTf.setText(selectedItem.getTitle());
        selectedItem = ItemTv.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void DeleteItemClick() {
        database.removeItem(selectedItem);
        updateItems();
    }

    @FXML
    private void AddItemAddClick() {
        AddItemController controller = new AddItemController(this, database);
        controller.addItem();
        AddItemPane.setVisible(false);
        ItemsPane.setVisible(true);
    }

    @FXML
    private void AddItemCancelClick() {
        AddItemPane.setVisible(false);
        ItemsPane.setVisible(true);
    }

    @FXML
    private void EditItemEditBtn() {
        EditItemController controller = new EditItemController(this, database);
        controller.editItem();
    }

    @FXML
    private void CancelEditClick() {
        (EditItemPane).setVisible(false);
        ItemsPane.setVisible(true);
    }

    @FXML
    private void MemberTab() {
        AddMemberPane.setVisible(false);
        EditMemberPane.setVisible(false);
        MemberPane.setVisible(true);
        updateMembers();
        MemberTv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Member>() {
            @Override
            public void changed(ObservableValue<? extends Member> observableValue, Member oldMember, Member newMember) {
                selectedMember = (Member) MemberTv.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void updateMembers() {
        ObservableList<Member> members = FXCollections.observableArrayList(database.getMemberCollection());
        MemberTv.setItems(members);
        MemberTv.refresh();
    }

    @FXML
    private void AddMemberClick() {
        MemberPane.setVisible(false);
        AddMemberPane.setVisible(true);

    }

    @FXML
    private void EditMemberClick() {
        EditMemberPane.setVisible(true);
        MemberPane.setVisible(false);

        EditMemberFirstnameTb.setText(selectedMember.getFirstname());
        EditMemberLastnameTb.setText(selectedMember.getLastname());
        EditMemberBirthdate.setValue(selectedMember.getBirthdate());

    }

    @FXML
    private void DeleteMemberBtnClick() {
        database.removeMember(selectedMember);
        updateMembers();
    }

    @FXML
    private void AddMemberAddClick() {
        // DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        AddMemberController controller = new AddMemberController(this, database);
        controller.addMember();
        AddMemberPane.setVisible(false);
        MemberPane.setVisible(true);
    }

    @FXML
    private void Cancel() {
        AddMemberPane.setVisible(false);
        MemberPane.setVisible(true);
    }

    @FXML
    private void EditMemberEditClick() {
        EditMemberController controller = new EditMemberController(this,database);
        controller.editMember();
        EditMemberPane.setVisible(false);
        MemberPane.setVisible(true);

    }

    @FXML
    private void EditMemberCancelClick() {
        EditMemberPane.setVisible(false);
        MemberPane.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        AddMemberBirthdate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                if (object != null) {
                    return formatter.format(object);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });

    }
}
