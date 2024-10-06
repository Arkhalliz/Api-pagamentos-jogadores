package dw.pagamento.model;

import java.util.ArrayList;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cod_jogador;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 8)
    private String datanasc;

    @OneToMany(mappedBy = "pagamento")
    private ArrayList<Pagamento> pagamentos;

    


    // Getters e setters


    public ArrayList<Pagamento> getPagamentos() {
        return this.pagamentos;
    }

    public void setPagamentos(ArrayList<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public long getCodJogador() {
        return this.cod_jogador;
    }

    public void setCodJogador(long id) {
        this.cod_jogador = id;
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

    public Jogador(String nome, String email, String datanasc, ArrayList<Pagamento> pagamentos) {
        this.nome = nome;
        this.email = email;
        this.datanasc = datanasc;
        this.pagamentos = pagamentos;
    }
}
