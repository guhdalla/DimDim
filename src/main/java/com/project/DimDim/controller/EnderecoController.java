package com.project.DimDim.controller;

import java.util.List; 
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.project.DimDim.model.Cliente;
import com.project.DimDim.model.Endereco;
import com.project.DimDim.repository.ClienteRepository;
import com.project.DimDim.repository.EnderecoRepository;

@Controller
public class EnderecoController {

	@Autowired
	private EnderecoRepository repository;
	
	@Autowired
	private ClienteRepository repositoryCliente;
	
	@PostMapping("/adderess/{id}")
	public String save(@PathVariable Long id, @Valid Endereco endereco, BindingResult result) {
		if(result.hasErrors()) return "redirect:/update-client/" + id;
		endereco.setCliente(repositoryCliente.findById(id).get());
		repository.save(endereco);
		return "redirect:/update-client/" + id;
	}

	@DeleteMapping("/adderess/delete/{id}")
	public ModelAndView delete(@PathVariable Long id) {
		repository.deleteById(id);
		return new ModelAndView("update-client");
	}

	@PutMapping("/adderess/update/{id}")
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
