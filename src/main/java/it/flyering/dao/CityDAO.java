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
@Table(name = "italy_cities")
@Data
@ToString
public class CityDAO {
	@Id
	@Column(name="istat")
	private int istat;
	
	@Column(name="comune")
	private String comune;
	
	@Column(name="regione")
	private String descrizioneRegione;
	
	@Column(name="prefisso")
	private String prefisso;
	
	@Column(name="cod_fisco")
	private String codiceFiscale;
	
	@Column(name="superficie")
	private double superficie;
	
	@Column(name="num_residenti")
	private int numeroResidenti;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provincia", nullable = false)
	private ProvinceDAO province;
}