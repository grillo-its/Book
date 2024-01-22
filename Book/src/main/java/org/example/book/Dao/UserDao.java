package org.example.book.Dao;

import org.example.book.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface UserDao extends CrudRepository<User, Long> {
    List<User> findByNome(String nome);//select * from Utenti where nome = :nome
    List<User> findByCognome(String cognome);
    List<User> findByUsername(String username);
    List<User> findByPassword(String password);
    User findById(long id);

    @Query("select s from User s where username= :username and password = :password")
    public User login(String username, String password);
}
