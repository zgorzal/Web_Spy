package pl.zgorzalek.web_spy.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final pl.zgorzalek.web_spy.user.User user;

    public CurrentUser(String email, String password, Collection<? extends GrantedAuthority> authorities, pl.zgorzalek.web_spy.user.User user) {
        super(email, password, authorities);
        this.user = user;
    }

    public pl.zgorzalek.web_spy.user.User getUser() {
        return user;
    }
}
