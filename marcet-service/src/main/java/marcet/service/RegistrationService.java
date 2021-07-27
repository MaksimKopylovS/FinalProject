package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.dto.UserDTO;
import marcet.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;


    @Transactional
    public void registrationUser(UserDTO userDTO) {
        System.out.println(userDTO.getUsername() + " " + userDTO.getPassword() + " " + userDTO.getMail() + " " + " " + "Pапись в Базу");
        entityManager.createNativeQuery("insert into users(USERNAME, PASSWORD, EMAIL) values(:a,:b,:c)")
                .setParameter("a", userDTO.getUsername())
                .setParameter("b", userDTO.getPassword())
                .setParameter("c", userDTO.getMail())
                .executeUpdate();
    }

}
