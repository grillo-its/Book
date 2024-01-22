package org.example.book.Dao;

import org.example.book.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> login(String username, String password) {
        return jdbcTemplate.query(
                "select * from utenti u join auth a on u.id = a.id where username = ? and password = ?"
                ,
                new Object[]{username, password},
                (rs, rowNum) ->
                        new User(
                                rs.getLong("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("nome"),
                                rs.getString("cognome")
                        )
        );
    }
}
