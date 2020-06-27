package com.jungyonge.kakaopay.repository;

import com.jungyonge.kakaopay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    User findById(long id);

}
