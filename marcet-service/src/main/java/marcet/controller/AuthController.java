package marcet.controller;

import lombok.RequiredArgsConstructor;
import marcet.bean.JwtTokenUtil;
import marcet.dto.JwtRequest;
import marcet.dto.JwtResponse;
import marcet.exceptions_handling.MarketError;
import marcet.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthTocen(@RequestBody JwtRequest authRequest) {
        System.out.println(authRequest.getUsername() + " |||| " + authRequest.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            System.out.println("authenticationManager Down");
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Ошибка авторизации"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = authService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generationToken(userDetails);
        System.out.println(token + "     tokenn bbbbnnnnn");
        return ResponseEntity.ok(new JwtResponse(token));

    }
}
