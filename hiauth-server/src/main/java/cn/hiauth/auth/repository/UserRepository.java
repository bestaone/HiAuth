package cn.hiauth.auth.repository;

import cn.hiauth.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "update user set password= :password where id= :id", nativeQuery = true)
    int updatePwdById(@Param("id") Long id, @Param("password") String password);

    @Query(value = "select * FROM user where username=:account OR phone_num=:account", nativeQuery = true)
    User findOneByAccount(@Param("account") String account);

}


