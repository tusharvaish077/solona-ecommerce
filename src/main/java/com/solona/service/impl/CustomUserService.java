package com.solona.service.impl;

import com.solona.domain.USER_ROLE;
import com.solona.modal.Seller;
import com.solona.modal.User;
import com.solona.repository.SellerRepository;
import com.solona.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final SellerRepository sellerRepository;
    private static final String SELLER_PREFIX ="seller_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.startsWith(SELLER_PREFIX)){
            String actualUsername = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUsername);

            if(seller!=null){
                System.out.println("Inside from the customuserservice " +seller.getRole());
                return buildUserDetails(seller.getEmail(), seller.getPassword(),seller.getRole());
            }
        }
        else{
            User user= userRepository.findByEmail(username);
            if(user!=null){
                return buildUserDetails(user.getEmail(),user.getPassword(),user.getRole());
            }
        }
        throw new UsernameNotFoundException("user or seller not found with email - "+username);
    }

    private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
        if(role == null){
            role =  USER_ROLE.ROLE_CUSTOMER;
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        //authorityList.add(new SimpleGrantedAuthority("ROLE_"+role)); // this line causing error commenting role edition
        authorityList.add(new SimpleGrantedAuthority(role.toString()));
        return new org.springframework.security.core.userdetails
                .User(email,
                password,
                authorityList);
    }
}
