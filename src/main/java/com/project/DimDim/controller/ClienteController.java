package com.project.DimDim.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.DimDim.model.Cliente;
import com.project.DimDim.repository.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping("/register-client")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("register-client");
		List<Cliente> clientes = repository.findAll();
		modelAndView.addObject("clientes", clientes);
		return modelAndView;
	}
	
	@PostMapping("/client")
	public ModelAndView save(@Valid Cliente cliente, BindingResult result) {
		repository.save(cliente);
		return new ModelAndView("register-client");
	}

	@DeleteMapping("/client/{id}")
	public ModelAndView delete(@PathVariable Long id) {
		repository.deleteById(id);
		return new ModelAndView("register-client");
	}
}
