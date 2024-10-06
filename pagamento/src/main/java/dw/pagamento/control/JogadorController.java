package dw.pagamento.control;

import dw.pagamento.model.Jogador;
import dw.pagamento.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class JogadorController {

    @Autowired
    JogadorRepository jogadorRepository;

    /*
     * GET / : listar todos os jogadores, com opção de buscar por nome
     */
    @GetMapping("/jogadores")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam(required = false) String nome) {
        try {
            List<Jogador> jogadores = new ArrayList<Jogador>();

            if (nome == null)
                jogadorRepository.findAll().forEach(jogadores::add);
            else
                jogadorRepository.findByNomeContaining(nome).forEach(jogadores::add);

            if (jogadores.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(jogadores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * POST / : criar um novo jogador
     */
    @PostMapping("/jogadores")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jogador) {
        try {
            Jogador novoJogador = jogadorRepository.save(new Jogador(
                    jogador.getNome(),
                    jogador.getEmail(),
                    jogador.getDatanasc(),
                    new ArrayList<>()) 
            );
            return new ResponseEntity<>(novoJogador, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * GET /jogadores/{id} : buscar jogador por ID
     */
    @GetMapping("/jogadores/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable("id") long id) {
        Optional<Jogador> jogador = jogadorRepository.findById(id);

        if (jogador.isPresent())
            return new ResponseEntity<>(jogador.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*
     * PUT /jogadores/{id} : atualizar jogador por ID
     */
    @PutMapping("/jogadores/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("id") long id, @RequestBody Jogador jogadorDetails) {
        Optional<Jogador> jogador = jogadorRepository.findById(id);

        if (jogador.isPresent()) {
            Jogador jogadorAtualizado = jogador.get();
            jogadorAtualizado.setNome(jogadorDetails.getNome());
            jogadorAtualizado.setEmail(jogadorDetails.getEmail());
            jogadorAtualizado.setDatanasc(jogadorDetails.getDatanasc());

            return new ResponseEntity<>(jogadorRepository.save(jogadorAtualizado), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
     * DELETE /jogadores/{id} : remover jogador por ID
     */
    @DeleteMapping("/jogadores/{id}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("id") long id) {
        try {
            jogadorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * DELETE /jogadores : remover todos os jogadores
     */
    @DeleteMapping("/jogadores")
    public ResponseEntity<HttpStatus> deleteAllJogadores() {
        try {
            jogadorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * GET /jogadores/nome : buscar jogadores pelo nome
     */
    @GetMapping("/jogadores/nome")
    public ResponseEntity<List<Jogador>> getJogadoresByNome(@RequestParam String nome) {
        try {
            List<Jogador> jogadores = jogadorRepository.findByNomeContaining(nome);

            if (jogadores.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(jogadores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
