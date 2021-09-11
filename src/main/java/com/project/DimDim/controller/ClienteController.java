package com.project.DimDim.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.DimDim.model.Cliente;
import com.project.DimDim.model.Endereco;
import com.project.DimDim.repository.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping("/register-client")
	public ModelAndView index(Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView("register-client");
		List<Cliente> clientes = repository.findAll();
		if(!clientes.isEmpty())
			modelAndView.addObject("clientes", clientes);
		return modelAndView;
	}
	
	@PostMapping("/client")
	public String save(@Valid Cliente cliente, BindingResult result) {
		if(result.hasErrors()) return "redirect:/register-client";
		repository.save(cliente);
		return "redirect:/register-client";
	}

	@RequestMapping("/client/delete/{id}")
	public String delete(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/register-client";
	}
	
	@GetMapping("/update-client/{id}")
	public ModelAndView index(@PathVariable Long id , Endereco endereco, Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView("update-client");
		Cliente c = repository.findById(id).get();
		List<Endereco> enderecos = c.getEnderecos();
		modelAndView.addObject("address", enderecos);
		modelAndView.addObject("idCliente", id);
		return modelAndView;
	}
	
	@PostMapping("/client/update/{id}")
	public String atualizarDados(@PathVariable Long id, @Valid Cliente cliente, BindingResult result) {
		if(result.hasErrors()) return "redirect:/update-client/" + id;
		return repository.findById(id).map(x -> {
			x.setDataNascimento(cliente.getDataNascimento());
			x.setEmail(cliente.getEmail());
			x.setNome(cliente.getNome());
			x.setTelefone(cliente.getTelefone());
			repository.save(x);
			return "redirect:/register-client";
		}).orElse("redirect:/update-client/" + id);
	}
}
