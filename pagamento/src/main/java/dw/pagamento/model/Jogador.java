package dw.pagamento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 8)
    private String datanasc;

    // Getters e setters


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatanasc() {
        return this.datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    // Construtor

    public Jogador(){

    }

    public Jogador(String nome, String email, String datanasc) {
        this.nome = nome;
        this.email = email;
        this.datanasc = datanasc;
    }


}
