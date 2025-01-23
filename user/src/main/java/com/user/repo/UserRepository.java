package com.user.repo;

import com.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User,String> {
    User findByUserId(String userId);

    @Query(value = """
            select
            	*
            from
            	"user"."user" u
            where
            	:filter is null
            	or :filter = ''
            	or upper(u.user_name) like %:filter%
            	or upper(u.first_name) like %:filter%
            	or upper(u.last_name) like %:filter%
            """,nativeQuery = true)
    Page<User> findAllUsers(String filter, PageRequest pageRequest);

    boolean existsByPhoneNumber(String phoneNumber);

    User findByUsernameAndPassword(String userName, String password);
}
