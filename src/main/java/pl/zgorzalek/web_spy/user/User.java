package pl.zgorzalek.web_spy.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = User.TABLE_NAME)
public class User {
    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
