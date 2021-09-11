package com.project.DimDim.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name="TB_DIMDIM_CLIENTE")
@SequenceGenerator(allocationSize = 1, name = "seq_cliente", sequenceName = "SQ_TB_DIMDIM_CLIENTE")
public class Cliente {
	
		@Id
		@Column(name="id_cliente")
		@GeneratedValue(generator = "seq_cliente", strategy = GenerationType.IDENTITY)
		private Long idCliente;
		
		@Column(name="nm_cliente")
		@NotBlank
		private String nome;
		
		@Column(name="dt_nascimento")
		@NotBlank
		private String dataNascimento;
		
		@Column(name = "ds_email")
		@NotBlank
		private String email;
		
		@Column(name="nr_telefone")
		@NotBlank
		private String telefone;
		
		@OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
		private List<Endereco> enderecos;
}
