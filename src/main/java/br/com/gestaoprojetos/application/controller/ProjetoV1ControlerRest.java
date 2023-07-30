package br.com.gestaoprojetos.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestaoprojetos.application.dto.MembroDto;
import br.com.gestaoprojetos.domain.entity.Pessoa;
import br.com.gestaoprojetos.domain.entity.Projeto;
import br.com.gestaoprojetos.domain.service.PessoaService;
import br.com.gestaoprojetos.domain.service.ProjetoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projeto/v1")
public class ProjetoV1ControlerRest {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping()
    public ResponseEntity<String> save(@Valid @RequestBody MembroDto membroDto, HttpServletResponse response) {

        try{
            Projeto projeto = projetoService.findById(membroDto.projetoId());
            Pessoa pessoa = pessoaService.findById(membroDto.pessoaId());
            projeto.getMembros().add(pessoa);
            projeto = projetoService.save(projeto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Membro incluido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(e.getMessage());
        }



    }
}
