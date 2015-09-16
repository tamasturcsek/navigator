package navigator

import javax.persistence.*

@Entity
@Table(name = "user")
public class User {
    public static final String USERNAME = "username"
    public static final String PASSWORD = "password"
    public static final String NAME = "name"
    public static final String MAIL = "mail"


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id


    private String username
    private String password
    private String name
    private String mail

    public User(String username, String password, String name, String mail) {
        this.username = username
        this.name = name
        this.mail = mail
        this.password = password
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name
    }

    void setUsername(String username) {
        this.username = username
    }

    public void setPassword(String password) {
        this.password = password
    }

    void setMail(String mail) {
        this.mail = mail
    }

    String getMail() {
        return mail
    }

    public String getName() {
        return name
    }

    String getUsername() {
        return username
    }

    public String getPassword() {
        return password
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}'
    }
}
