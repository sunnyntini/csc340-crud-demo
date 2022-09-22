
package com.csc340.crud.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sunny Ntini
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
}
