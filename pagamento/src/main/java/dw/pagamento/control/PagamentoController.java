package dw.pagamento.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dw.pagamento.model.Pagamento;
import dw.pagamento.model.Jogador;
import dw.pagamento.repository.PagamentoRepository;
import dw.pagamento.repository.JogadorRepository;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    JogadorRepository jogadorRepository;

    /*
     * GET / : listar todos os pagamentos
     */
    @GetMapping("/")
    public ResponseEntity<List<Pagamento>> getAllPagamentos(@RequestParam(required = false) Integer ano) {
        try {
            List<Pagamento> pagamentos = new ArrayList<>();

            if (ano == null) {
                pagamentoRepository.findAll().forEach(pagamentos::add);
            } else {
                pagamentoRepository.findByAno(ano).forEach(pagamentos::add);
            }

            if (pagamentos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pagamentos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * POST / : criar um pagamento
     */
    @PostMapping("/")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        try {
            Optional<Jogador> jogadorOpt = jogadorRepository.findById(pagamento.getCodJogador().getCodJogador());
            if (jogadorOpt.isPresent()) {
                Jogador jogador = jogadorOpt.get();
                pagamento.setCodJogador(jogador);
                Pagamento savedPagamento = pagamentoRepository.save(pagamento);
                return new ResponseEntity<>(savedPagamento, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * GET /:id : listar pagamento dado um id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable("id") long id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);

        if (pagamento.isPresent()) {
            return new ResponseEntity<>(pagamento.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
     * PUT /:id : atualizar pagamento dado um id
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("id") long id, @RequestBody Pagamento pagamentoDetails) {
        Optional<Pagamento> pagamentoData = pagamentoRepository.findById(id);

        if (pagamentoData.isPresent()) {
            Pagamento pagamento = pagamentoData.get();
            pagamento.setAno(pagamentoDetails.getAno());
            pagamento.setMes(pagamentoDetails.getMes());
            pagamento.setValor(pagamentoDetails.getValor());

            if (pagamentoDetails.getCodJogador() != null) {
                Optional<Jogador> jogadorOpt = jogadorRepository.findById(pagamentoDetails.getCodJogador().getCodJogador());
                jogadorOpt.ifPresent(pagamento::setCodJogador);
            }

            return new ResponseEntity<>(pagamentoRepository.save(pagamento), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
     * DELETE /:id : remover pagamento dado um id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("id") long id) {
        try {
            pagamentoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * DELETE / : remover todos os pagamentos
     */
    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllPagamentos() {
        try {
            pagamentoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * GET /ano/{ano} : buscar por pagamentos de um ano específico
     */
    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<Pagamento>> getPagamentosByAno(@PathVariable("ano") int ano) {
        try {
            List<Pagamento> pagamentos = pagamentoRepository.findByAno(ano);

            if (pagamentos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pagamentos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*
 * GET /jogador/{id} : buscar todos os pagamentos de um jogador específico
     */
    @GetMapping("/jogador/{id}")
    public ResponseEntity<List<Pagamento>> getPagamentosByJogador(@PathVariable("id") long id) {
        try {
            Optional<Jogador> jogadorOpt = jogadorRepository.findById(id);
            if (jogadorOpt.isPresent()) {
                List<Pagamento> pagamentos = pagamentoRepository.findByCodJogador(jogadorOpt.get());

                if (pagamentos.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(pagamentos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
