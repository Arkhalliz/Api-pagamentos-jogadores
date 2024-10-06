package dw.pagamento.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import dw.pagamento.model.Jogador;
import dw.pagamento.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    // Encontrar pagamentos por ano
    List<Pagamento> findByAno(int ano);

    // Encontrar pagamentos por jogador
    List<Pagamento> findByCodJogador(Jogador jogador);
}