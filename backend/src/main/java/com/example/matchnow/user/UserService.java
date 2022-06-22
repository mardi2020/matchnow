package com.example.matchnow.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email:" + email);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 로그인을 하기 위해 가입된 user정보를 조회하는 메서드

        User user = userRepository.findByEmail(email).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email:" + email);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(email)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Transactional
    public void updateLoginDate(String email) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userRepository.updateLoginDate(email, sdf.format(System.currentTimeMillis()));
    }

    @Transactional
    public void updateMyInfo(User user, UpdateMyInfoDTO myInfo) {
        user.setMyInfo(myInfo);
        userRepository.save(user);
    }
}
