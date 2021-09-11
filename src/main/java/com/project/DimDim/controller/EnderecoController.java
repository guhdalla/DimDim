package com.project.DimDim.controller;

import java.util.List; 
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.project.DimDim.model.Endereco;
import com.project.DimDim.repository.EnderecoRepository;

@Controller
public class EnderecoController {

	@Autowired
	private EnderecoRepository repository;

	@GetMapping("/update-client")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("update-client");
		List<Endereco> enderecos = repository.findAll();
		modelAndView.addObject("address", enderecos);
		return modelAndView;
	}
	
	@PostMapping("/adderess")
	public ModelAndView save(@Valid Endereco endereco, BindingResult result) {
		repository.save(endereco);
		return new ModelAndView("update-client");
	}

	@DeleteMapping("/adderess/{id}")
	public ModelAndView delete(@PathVariable Long id) {
		repository.deleteById(id);
		return new ModelAndView("update-client");
	}

	@PutMapping("/adderess/{id}")
	public ModelAndView atualizarDados(@PathVariable Long id, @Valid Endereco endereco) {
		return repository.findById(id).map(x -> {
			x.setRua(endereco.getRua());
			x.setEstado(endereco.getEstado());
			x.setNumero(endereco.getNumero());
			repository.save(x);
			return new ModelAndView("update-client");
		}).orElse(new ModelAndView("update-client"));
	}
}
