package it.flyering.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "italy_provincies")
@Data
@ToString
public class ProvinceDAO {
	
	@Id
	@Column(name="sigla")
	private String id;
	
	@Column(name="provincia")
	private String descrizione;
	
	@Column(name="superficie")
	private double superficie;
	
	@Column(name="residenti")
	private int residenti;
	
	@Column(name="num_comuni")
	private int numeroComuni;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_regione", nullable = false)
	private RegionDAO region;
}