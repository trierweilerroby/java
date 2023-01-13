package inholland.java.endassignment682474.model;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Item implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -5724539239555844720L;
    public static final AtomicInteger count = new AtomicInteger(0);
    public Integer id;
    public boolean available;
    public String title;
    public String author;
    public LocalDate lendingDate;
    public Member lendingMember;
    public LocalDate ExpectedReturnDate;

    public LocalDate getExpectedReturnDate() {
        return ExpectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        ExpectedReturnDate = expectedReturnDate;
    }

    public Item(int id,String title, String author){
        this.id = id;
        this.available = true;
        this.title = title;
        this.author = author;
        this.lendingDate = null;
        this.lendingMember = null;
    }

    public Integer getId() {
        return id;
    }

    public String getIdAsString(){
        return id.toString();
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public String getAvailable(){
        if (isAvailable()){
            return "yes";
        }
        return "no";
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(LocalDate lendingDate) {
        this.lendingDate = lendingDate;
    }

    public Member getLendingMember() {
        return lendingMember;
    }

    public void setLendingMember(Member lendingMember) {
        this.lendingMember = lendingMember;
    }
}
