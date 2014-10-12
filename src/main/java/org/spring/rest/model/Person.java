package org.spring.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author Sheetal
 *
 */
@Entity
@Table(name = "PERSON")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonSerialize(include=Inclusion.NON_NULL)
@JsonAutoDetect
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String country;

	public Person() {
		super();
	}

	@JsonCreator
	public Person(@JsonProperty("name") String name,
			@JsonProperty("country") String country, @JsonProperty("id") int id) {
		super();
		this.name = name;
		this.country = country;
		this.id = id;
	}

	@JsonProperty("ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", country=" + country;
	}
}
