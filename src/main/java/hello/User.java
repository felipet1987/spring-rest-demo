package hello;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    protected User() {}

    public void setId(Long id) {
        this.id = id;
    }

    public User(String userNameusername) {
        this.userName = userName;

    }



// end::sample[]

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

}
