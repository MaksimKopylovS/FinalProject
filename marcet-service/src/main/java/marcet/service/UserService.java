package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.dto.UserDTO;
import marcet.model.User;
import marcet.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO changeUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setMail(user.getEmail());
        return userDTO;
    }

    public User changeUserDTO(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getUsername());
        user.setEmail(userDTO.getMail());
        return user;
    }

}
