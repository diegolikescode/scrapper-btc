package br.unisul.aula.controler;

import br.unisul.aula.modelo.Participante;
import br.unisul.aula.modelo.Projeto;
import br.unisul.aula.repository.ParticipanteRepositorio;
import br.unisul.aula.repository.ProjetoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/projeto")
public class ProjetoControler {

    @Autowired
    private ProjetoRepositorio projetoRepositorio;

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;



    @GetMapping("/hello")
    public List<?> sayHello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Chave", "valor");
        map.put("mensagem", "Hello World");
        map.put("Aula", "teste");
        List lista = new ArrayList<>();
        lista.add( map);
        lista.add( map);
        return lista;
    }

    @GetMapping("/projetos")
    public List<Projeto> getTodosProjetos() {

        return this.projetoRepositorio.findAll();
    }

    @GetMapping("/proj/{id}")
    public Projeto getUmProjetoId(@PathVariable(value = "id") Integer id) {
        return this.projetoRepositorio.findById(id).orElseThrow(null);
    }

    @GetMapping("/buscanome/{nome}")
    public List<Projeto> encontraProjetoComNome(@PathVariable(value = "nome") String nome){
        return this.projetoRepositorio.findAllByDescricaoContains(nome);
    }

    @GetMapping("/participante/{nome}")
    public List<Projeto> econtrarPorParticipante(@PathVariable(value = "nome") String nome){
        return this.projetoRepositorio.findAllByParticipantesContains(nome);
    }

    @PostMapping(value = "/projeto", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Projeto criarProjeto(@Valid @RequestBody Projeto projeto) {

        for (Participante participante : projeto.getParticipantes()
        ) {
            if (participante.getIdParticipante() != null) {
                Participante participanteBD = participanteRepositorio.findById(participante.getIdParticipante()).orElseThrow(null);
                if (participanteBD != null) {
                    participante.copyAttributes(participanteBD);
                }
            }
        }
        return this.projetoRepositorio.save(projeto);
    }

    @PutMapping("/projeto/{id}")
    public Projeto alterarProjeto(@PathVariable(value = "id") Integer id,
                                  @Valid @RequestBody Projeto projetoNovo) {
        Projeto projeto = this.getUmProjetoId(id);
        projetoNovo.setIdProjeto(id);
        projeto.copyAttributes(projetoNovo);
        return this.projetoRepositorio.save(projeto);
    }

    @DeleteMapping("/projeto/{id}")
    public ResponseEntity<?> delUmProjetoId(@PathVariable(value = "id") Integer id) {
        Projeto projeto = this.getUmProjetoId(id);
        this.projetoRepositorio.delete(projeto);
        return ResponseEntity.ok().build();
    }


}
