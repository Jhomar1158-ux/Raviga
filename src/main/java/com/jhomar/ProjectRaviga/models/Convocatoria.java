package com.jhomar.ProjectRaviga.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="convocatorias")
public class Convocatoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El campo de nombre es obligatorio.")
	private String name;
	
	@Size(max=200, message="La descripcion tiene 200 caracteres como maximo.")
	@NotEmpty(message="El campo de descripción es obligatorio.")
	private String descripcion;
	
	@NotEmpty(message="El campo para el url de inscripción es obligatorio.")
	private String linkInscripcion;
	
	@Future //Solo acepta fechas a futuro
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date deadline;
	
	@NotEmpty(message="El campo de universidad es obligatorio.")
	private String universidad;
	
	//==PENDIENTE===
	@Column(nullable = true, length = 64)
    private String afiche;
	//==============
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date created_at;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updated_at;
	
	//===========RELACIONES============
	//MANY TO ONE con User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User host;
	
	//MANY TO MANY con User
	@ManyToMany(fetch =FetchType.LAZY)
	@JoinTable(
			name = "users_has_convocatorias",
			joinColumns = @JoinColumn(name = "convocatoria_id"), //sincronizado a la clase Event
			inverseJoinColumns = @JoinColumn(name = "user_id") //sincronizado a la clase User
	)
	private List<User> attendees; //La lista de usuarios que interesados
	
	//OneToMany con Message
	@OneToMany(mappedBy="convocatoria", fetch=FetchType.LAZY)
	private List<Message> messages; //La lista de mensajes que pertenecen a este evento
	
	//MANY TO ONE con Categorias
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	
	//=====================================
	
	@PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }

	public Convocatoria() {
		
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLinkInscripcion() {
		return linkInscripcion;
	}

	public void setLinkInscripcion(String linkInscripcion) {
		this.linkInscripcion = linkInscripcion;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
    
    
    
	
}
