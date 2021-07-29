package pl.zgorzalek.web_spy.user.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zgorzalek.web_spy.role.Role;
import pl.zgorzalek.web_spy.role.RoleRepository;
import pl.zgorzalek.web_spy.user.DTO.UserDataChangeDTO;
import pl.zgorzalek.web_spy.user.DTO.UserPasswordChangeDTO;
import pl.zgorzalek.web_spy.user.DTO.UserRegisterDTO;
import pl.zgorzalek.web_spy.user.User;
import pl.zgorzalek.web_spy.user.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
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
    private final JavaMailSender mailSender;

    public void add(UserRegisterDTO userRegisterDTO, String siteURL) throws UnsupportedEncodingException, MessagingException {
        User user = new User();
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        userRepository.save(user);
        sendVerificationEmail(user, siteURL);
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

    public void updatePassword(UserPasswordChangeDTO userPasswordChangeDTO) {
        User user = userRepository.getById(userPasswordChangeDTO.getId());
        user.setPassword(passwordEncoder.encode(userPasswordChangeDTO.getNewPassword()));
        userRepository.save(user);
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "webspymail@gmail.com";
        String senderName = "WebSpy!";
        String subject = "Potwierdz rejestrację w WebSpy!";
        String content = "Cześć [[name]],<br>"
                + "Prosze kliknij w poniższy link w celu potwierdzenia rejestracji:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">Potwierdzam</a></h3>"
                + "Dziękuje,<br>"
                + "Zgorzał z WebSpy!";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getFirstName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
    }

}
