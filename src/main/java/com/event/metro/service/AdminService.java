package com.event.metro.service;

import com.event.metro.model.RequestRole;
import com.event.metro.model.Role;
import com.event.metro.model.User;
import com.event.metro.repository.RequestRoleRepository;
import com.event.metro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequestRoleRepository requestRoleRepository;

    public ResponseEntity<User> promoteUser(String userId, String newAuthority) {
        User user = userService.findById(userId);
        List<Role> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
            if (grantedAuthority instanceof Role) {
                authorities.add((Role) grantedAuthority);
            }
        }
        if (!authorities.isEmpty()) {
            authorities.get(0).setAuthority(newAuthority.toUpperCase());
        }
        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<List<RequestRole>> getAllRequest() {
        return new ResponseEntity<>(requestRoleRepository.findAll(), HttpStatus.OK);
    }
}
