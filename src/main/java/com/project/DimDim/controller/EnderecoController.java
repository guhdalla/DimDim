package com.project.DimDim.controller;

import java.util.List;
import java.util.Optional;

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
	
	@PostMapping("/address/{id}")
	public String save(@PathVariable Long id, @Valid Endereco endereco, BindingResult result) {
		if(result.hasErrors()) return "redirect:/update-client/" + id;
		endereco.setCliente(repositoryCliente.findById(id).get());
		repository.save(endereco);
		return "redirect:/update-client/" + id;
	}

	@RequestMapping("/address/delete/{id}")
	public String delete(@PathVariable Long id) {
		Endereco endereco = repository.findById(id).get();
		Cliente cliente = endereco.getCliente();
		cliente.getEnderecos().remove(endereco);
		repository.delete(endereco);
		return "redirect:/update-client/" + cliente.getIdCliente();
	}

	@PostMapping("/address/update/{id}")
	public String atualizarDados(@PathVariable Long id, @Valid Endereco endereco, BindingResult result) {
		if(result.hasErrors()) return "redirect:/update-address/" + id;
		return repository.findById(id).map(x -> {
			x.setRua(endereco.getRua());
			x.setEstado(endereco.getEstado());
			x.setNumero(endereco.getNumero());
			repository.save(x);
			return"redirect:/update-client/" + x.getCliente().getIdCliente();
		}).orElse("redirect:/update-address/" + id);
	}
	
	@GetMapping("/update-address/{id}")
	public ModelAndView index(@PathVariable Long id, Endereco endereco) {
		ModelAndView modelAndView = new ModelAndView("update-address");
		endereco = repository.findById(id).get();
		modelAndView.addObject("address", endereco);
		return modelAndView;
	}
}
