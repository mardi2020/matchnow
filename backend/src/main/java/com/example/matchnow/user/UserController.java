package com.example.matchnow.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUserPOST(@RequestBody UserJoinDTO userJoinDTO) {

        try {
            authService.join(userJoinDTO);
            String email = userJoinDTO.getEmail();
            return new ResponseEntity<>(email + "님 회원 가입 성공!", HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUserPOST(@RequestBody UserLoginDTO userLoginDTO, HttpSession session) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userLoginDTO.getEmail(), userLoginDTO.getPassword()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());

            userService.updateLoginDate(userLoginDTO.getEmail());
            return new ResponseEntity<>("로그인 성공!", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/me")
    public ResponseEntity<?> getMyInfo(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("로그인 않됌", HttpStatus.BAD_REQUEST);
        }
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User user = userService.findUserByEmail(principal.getUsername());
        try {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/me")
    public ResponseEntity<?> updateMyInfo(@RequestBody UpdateMyInfoDTO myinfo, Principal principal) {
        try {
            String email = principal.getName();
            User user = userService.findUserByEmail(email);
            userService.updateMyInfo(user, myinfo);
            return new ResponseEntity<>("내 정보 수정 완료", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}
