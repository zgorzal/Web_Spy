package pl.zgorzalek.web_spy.page;

import lombok.Data;
import pl.zgorzalek.web_spy.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = Page.TABLE_NAME)
public class Page {
    public static final String TABLE_NAME = "pages";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String url;

    @Column(length = 1000)
    private String description;

    private LocalDateTime dateAdded;
    private LocalDateTime dateUpdate;

    @ManyToOne
    private User user;
}