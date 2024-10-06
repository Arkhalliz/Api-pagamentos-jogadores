package dw.pagamento.repository;

import dw.pagamento.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    List<Jogador> findByNomeContaining(String nome);
}