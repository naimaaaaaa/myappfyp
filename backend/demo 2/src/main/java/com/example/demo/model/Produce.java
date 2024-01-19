package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Produce")
@EntityListeners(AuditingEntityListener.class)
public class Produce implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotBlank
	String name;
	
	

	public Produce() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Produce(@NotBlank String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Produce [id=" + id + ", name=" + name + "]";
	}

	
	
}
