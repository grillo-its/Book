package org.example.book;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "utenti")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Size(min=4, max=20, message = "Nome deve esser tra 4 e 20 caratteri")
    @NotNull(message = "Inserire il nome per continuare")
    String nome;

    @Size(min=4, max=20, message = "Cognome deve esser tra 4 e 20 caratteri")
    @NotNull(message = "Inserire il cognome per continuare")
    String cognome;

    @Size(min=4, max=10, message = "Username deve esser tra 4 e 10 caratteri")
    @NotNull(message = "Inserire username per continuare")
    String username;

    @Size(min=6, max=45, message = "Password deve esser tra 6 e 45 caratteri")
    @NotNull(message = "Inserire la password per continuare")
    String password;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password, String nome, String cognome) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }

    public User(String username, String password, String nome, String cognome) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", nome=" + nome
                + ", cognome=" + cognome + "]";
    }
}