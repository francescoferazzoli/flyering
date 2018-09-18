package it.flyering.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "italy_regions")
@Data
@ToString
public class RegionDAO {
	
	@Id
	@Column(name="id_regione")
	private int id;
	
	@Column(name="regione")
	private String descrizione;
	
	@Column(name="superficie")
	private double superficie;
	
	@Column(name="num_residenti")
	private int residenti;
	
	@Column(name="num_comuni")
	private int numeroComuni;
	
	@Column(name="num_provincie")
	private int numeroProvicie;
	
	@Column(name="presidente")
	private String presidente;
	
	@Column(name="cod_istat")
	private String codiceIstat;
	
	@Column(name="cod_fiscale")
	private String codiceFiscale;
	
	@Column(name="piva")
	private String piva;
	
	@Column(name="pec")
	private String pec;
	
	@Column(name="sito")
	private String sito;
	
	@Column(name="sede")
	private String sede;

}
