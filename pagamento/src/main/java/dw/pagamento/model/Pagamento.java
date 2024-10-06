package dw.pagamento.model;

import jakarta.persistence.*;
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
    private long cod_pagamento;

    @Column(nullable = false)
    private int ano;
    
    @Column(nullable = false)
    private int mes;

    @Column(nullable = false)
    private float valor;

    @ManyToOne
    @JoinColumn(name = "cod_jogador")
    private Jogador cod_jogador;

    

    //getters e setters

    public long getCodPagamento() {
        return cod_pagamento;
    }

    public void setCodPagamento(long cod_pagamento) {
        this.cod_pagamento = cod_pagamento;
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

    public Jogador getCodJogador() {
        return this.cod_jogador;
    }

    public void setCodJogador(Jogador cod_jogador) {
        this.cod_jogador = cod_jogador;
    }

    


    //construtor

    public Pagamento(int ano, int mes, float valor, Jogador cod_jogador) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
        this.cod_jogador = cod_jogador;
    }

    public Pagamento() {

    }
}