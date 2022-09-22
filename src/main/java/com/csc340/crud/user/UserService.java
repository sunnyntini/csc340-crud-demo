
package com.csc340.crud.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sunny Ntini
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository repo;
    
    public List<User> getAllUsers(){
        return repo.findAll();
    }
}
