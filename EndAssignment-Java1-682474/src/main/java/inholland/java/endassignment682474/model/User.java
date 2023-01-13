package inholland.java.endassignment682474.model;

import java.io.Serial;

public class User implements java.io.Serializable{

    @Serial
    private static final long serialVersionUID = -3347972910521345654L;
    public String username;
    public String password;
    public User(String username, String password){
        this.username=username;
        this.password=password;
    }
}
