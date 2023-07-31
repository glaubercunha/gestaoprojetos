package br.com.gestaoprojetos.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gestaoprojetos.application.dto.ProjetoDto;
import br.com.gestaoprojetos.application.mapper.Mapper;
import br.com.gestaoprojetos.domain.entity.Pessoa;
import br.com.gestaoprojetos.domain.entity.Projeto;
import br.com.gestaoprojetos.domain.service.PessoaService;
import br.com.gestaoprojetos.domain.service.ProjetoService;
import br.com.gestaoprojetos.domain.util.RiscoProjeto;
import br.com.gestaoprojetos.domain.util.StatusProjeto;
import jakarta.validation.Valid;

@Controller
@RequestMapping("projeto")
public class ProjetoControler {
    
    private static final String PESQUISAR = "projeto/projetos";
    private static final String MANTER = "projeto/projeto_form";
    private static final String REDIRECT_PESQUISAR = "redirect:/projeto/pesquisar";

    @Autowired
    private ProjetoService projetoService;
        
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private Mapper mapper;

    @GetMapping("pesquisar")
    public ModelAndView projetos(){
 
        Iterable<Projeto> projetos = projetoService.findAll();
        List<ProjetoDto> projetosDto = mapper.entityToDto(projetos);
        ModelAndView mv = new ModelAndView(PESQUISAR, "projetos", projetosDto);
        return mv;
    }

    @GetMapping("novo")
    public String novo(ProjetoDto projetoDto, Model model, RedirectAttributes redirectAttributes){
        try {
            carregarCombos(model);
            model.addAttribute("pageTitle", "Novo");
            return MANTER;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return REDIRECT_PESQUISAR;
    }

    @PostMapping("salvar")
    public String salvar(@Valid ProjetoDto projetoDto, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        try {
            if (result.hasErrors()) {
                carregarCombos(model);
                return MANTER;
            }
            Projeto projeto = mapper.mapDtoToEntity(projetoDto);
            projetoService.save(projeto);
            redirectAttributes.addFlashAttribute("message", "Salvo com sucesso!");
        } catch(Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return REDIRECT_PESQUISAR;
    }

    @GetMapping("{id}")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Projeto projeto = projetoService.findById(id);
            ProjetoDto projetoDto = mapper.entityToDto(projeto);
            Iterable<Pessoa> pessoas = pessoaService.findAll();
        
            model.addAttribute("riscos", RiscoProjeto.values());
            model.addAttribute("status", StatusProjeto.values());
            model.addAttribute("pessoas", pessoas);
            model.addAttribute("projetoDto", projetoDto);
            model.addAttribute("pageTitle", "Editar");

            return MANTER;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return REDIRECT_PESQUISAR;
    }
 
    @GetMapping("excluir/{id}")
    public String excluir(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            projetoService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return REDIRECT_PESQUISAR;
    }

    private void carregarCombos(Model model) {
        Iterable<Pessoa> pessoas = pessoaService.findAll();
      
        model.addAttribute("riscos", RiscoProjeto.values());
        model.addAttribute("status", StatusProjeto.values());
        model.addAttribute("pessoas", pessoas);
    } 
}
