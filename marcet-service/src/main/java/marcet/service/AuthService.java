package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.dto.JwtRequest;
import marcet.model.Role;
import marcet.model.User;
import marcet.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public void toAuth(JwtRequest authRequest) {
        System.out.println(authRequest.getUsername() + "  " + authRequest.getPassword() + "  AuthService");
        Optional<User> optionalUser = userRepository.findByUsername(authRequest.getUsername());
        System.out.println(optionalUser.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        System.out.println(user.getUsername() + " Nameeee");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
