package dw.pagamento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int ano;
    
    @Column(nullable = false)
    private int mes;

    @Column(nullable = false)
    private float valor;

    @ManyToOne
    @JoinColumn(name = "cod_jogador")
    private Jogador jogador;

    @ManyToOne
    //private long cod_jogador

    //getters e setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public long getCod_jogador() {
        return cod_jogador;
    }

    public void setCod_jogador(long cod_jogador) {
        this.cod_jogador = cod_jogador;
    }


    //construtor

    public Pagamento(int ano, int mes, float valor, long cod_jogador) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.cod_jogador = cod_jogador;
    }

    public Pagamento() {

    }
}
