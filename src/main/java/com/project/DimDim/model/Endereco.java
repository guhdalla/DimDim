package com.project.DimDim.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="TB_DIMDIM_ENDERECO")
@SequenceGenerator(allocationSize = 1, name = "seq_endereco", sequenceName = "SQ_TB_DIMDIM_ENDERECO")
public class Endereco {
	
	@Id
	@Column(name="id_endereco")
	@GeneratedValue(generator = "seq_endereco")
	private Long idEndereco;
	
	@Column(name="ds_rua")
	@NotBlank
	private String rua;
	
	@Column(name="nr_numero")
	@NotBlank
	private String numero;
	
	@Column(name="nm_estado")
	@NotBlank
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
}
