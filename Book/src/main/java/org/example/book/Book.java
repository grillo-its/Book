package org.example.book;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "libro")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Size(min=4, max=30, message = "Nome deve esser tra 4 e 30 caratteri")
    @NotNull(message = "Inserire il titolo per continuare")
    private String titolo;

    @Size(min=4, max=20, message = "Nome deve esser tra 4 e 20 caratteri")
    @NotNull(message = "Inserire l'autore per continuare")
    private String autore;

    @NotNull
    private int annoPubblicazione;

    @NotNull
    @DecimalMax("1000.0")
    @DecimalMin("0.0")
    private double prezzo;


    public Book(Long id, String titolo, String autore, int annoPubblicazione, double prezzo) {
        super();
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    public Book(String titolo, String autore, int annoPubblicazione, double prezzo) {
        super();
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
