package pl.zgorzalek.web_spy.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zgorzalek.web_spy.role.Role;
import pl.zgorzalek.web_spy.role.RoleRepository;
import pl.zgorzalek.web_spy.user.DTO.UserDataChangeDTO;
import pl.zgorzalek.web_spy.user.DTO.UserRegisterDTO;
import pl.zgorzalek.web_spy.user.User;
import pl.zgorzalek.web_spy.user.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void add(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void update(UserDataChangeDTO userDataChangeDTO) {
        User user = userRepository.getById(userDataChangeDTO.getId());
        user.setFirstName(userDataChangeDTO.getFirstName());
        user.setLastName(userDataChangeDTO.getLastName());
        user.setEmail(userDataChangeDTO.getEmail());
        userRepository.save(user);
    }

    public void updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
