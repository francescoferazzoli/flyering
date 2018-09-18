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
@Table(name = "italy_cap")
@Data
@ToString
public class CapDAO {
	
	@Id
	@Column(name="cap_id")
	private int id;

	@Column(name="cap")
	private String cap;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "istat", nullable = false)
	private CityDAO city;
}