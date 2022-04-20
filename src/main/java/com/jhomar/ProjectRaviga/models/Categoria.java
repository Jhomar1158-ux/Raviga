package com.jhomar.ProjectRaviga.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El campo de nombre es obligatorio.")
	private String name;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date created_at;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updated_at;
	
	//============RELACIONES============
	
	//ONE TO MANY con Convocatoria
	@OneToMany(mappedBy="categoria", fetch = FetchType.LAZY)
	private List<Convocatoria> convocatoriasAssign; //La lista de convocatorias que tiene mi categoria
	
	//==================================
	
	@PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }

	public Categoria() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Convocatoria> getConvocatoriasAssign() {
		return convocatoriasAssign;
	}

	public void setConvocatoriasAssign(List<Convocatoria> convocatoriasAssign) {
		this.convocatoriasAssign = convocatoriasAssign;
	}

	
    
    
	
	
	
}
