package com.example.auth.services;

import com.example.auth.config.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAccountServices {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress:" + accountDto.getEmail());
        }
        User user = new User();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());

        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        user.setEmail(accountDto.getEmail());
        user.setRole(new Role(Integer.valueOf(1), user));
        return repository.save(user);
    }
}
