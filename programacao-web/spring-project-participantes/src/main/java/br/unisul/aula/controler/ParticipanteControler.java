package br.unisul.aula.controler;

import br.unisul.aula.modelo.Participante;
import br.unisul.aula.repository.ParticipanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/participante")
public class ParticipanteControler {

    @Autowired
    ParticipanteRepositorio participanteRepositorio;

    @GetMapping("/participantes")
    public List<Participante> getTodosParticipantes(){
        return this.participanteRepositorio.findAll();
    }

    @GetMapping("/participante/{id}")
    public Participante getUmParticipanteId(@PathVariable(value = "id") Integer id){
        return this.participanteRepositorio.findById(id).orElseThrow(null);
    }

    @PostMapping("/participante")
    public Participante criarParticipante(@Valid @RequestBody Participante participante){
        return this.participanteRepositorio.save(participante);
    }

    @PutMapping("/participante/{id}")
    public Participante alterarParticipante(@PathVariable(value = "id") Integer id,
                                            @Valid @RequestBody Participante participanteNovo){
            Participante participante = getUmParticipanteId(id);
            participanteNovo.setIdParticipante(id);
            participante.copyAttributes(participanteNovo);
            this.participanteRepositorio.save(participante);
        return this.participanteRepositorio.save(participante);
    }

    @DeleteMapping("/participante/{id}")
    public ResponseEntity<?> deletaParticipante(@PathVariable(value = "id") Integer id){
        Participante participante = this.getUmParticipanteId(id);
        this.participanteRepositorio.delete(participante);
        return ResponseEntity.ok().build();
    }

}
