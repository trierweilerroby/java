package inholland.java.endassignment682474.model;

import inholland.java.endassignment682474.HelloApplication;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Database implements java.io.Serializable {
    private String filename = "database.ser";
    public List<User> userCollection;
    public List<Member> memberCollection;
    public List<Item> itemCollection;

    public String LateMessage;
    public String FineMessage;
    public long lenddays;
    public LocalDate DateNow;
    public LocalDate ExpectedReturnDate;
    public Database() {
        userCollection = new ArrayList<>();
        userCollection.add(new User("Roby", "1234"));
        userCollection.add(new User("Amielle", "456"));

        memberCollection = new ArrayList<>();
        itemCollection = new ArrayList<>();

        DateNow = LocalDate.now();

        deserialize();
    }
    public void removeItem(Item item) {
        itemCollection.remove(item);
    }

    public void removeMember(Member member) {
        memberCollection.remove(member);
    }

    public List<Member> getMemberCollection() {
        return memberCollection;
    }

    public List<Item> getItemCollection() {
        return itemCollection;
    }

    public Item getItemById(int id) {
        return itemCollection.get(--id);
    }

    public Member getMemberById(int id) {
        return memberCollection.get(--id);
    }

    public void lendToMember(Item item, Member member) throws Exception {
        if (item.isAvailable()) {
            item.setAvailable(false);
            item.setLendingMember(member);
            item.setLendingDate(LocalDate.now());
            //item.setExpectedReturnDate(ExpectedReturnDate);
            //ExpectedReturnDate = DateNow.plusDays(21);
        } else {
            throw new Exception("Item is not available.");
        }
    }

    public void receiveItem(Item item) throws Exception {
        if (!item.isAvailable()) {
                item.setAvailable(true);
                item.setLendingDate(null);
                item.setLendingMember(null);
        } else {
            throw new Exception("Item not lend");
        }
    }
    public void Pay(Item item){
        item.setAvailable(true);
        item.setLendingDate(null);
        item.setLendingMember(null);
    }

    private boolean isOverdo(Item item) throws Exception {
        lenddays = ChronoUnit.DAYS.between(item.getLendingDate(), ExpectedReturnDate);
        if (lenddays >= 21) {
            throw new Exception("Item is overdo:" + lenddays + " days");
        } else return false;
    }
    public void deserialize() {
        try {
            File file = new File(filename);
            if (file.exists()) {

                FileInputStream inFile = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(inFile);

                ArrayList<Item> items = (ArrayList<Item>) in.readObject();
                ArrayList<Member> members = (ArrayList<Member>) in.readObject();


                in.close();
                inFile.close();

                this.itemCollection = items;
                this.memberCollection = members;

            } else {
                this.itemCollection = new ArrayList<>();
                this.memberCollection = new ArrayList<>();
                throw new RuntimeException("File does not exist");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (RuntimeException e){
            System.out.println("File not found");
        }
    }

}


