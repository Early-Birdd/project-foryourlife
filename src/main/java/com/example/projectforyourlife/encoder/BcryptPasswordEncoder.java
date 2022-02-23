package com.example.projectforyourlife.encoder;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptPasswordEncoder {

    public String encode(String password){

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isMatch(String password, String hashedPassword){

        return BCrypt.checkpw(password, hashedPassword);
    }
}
