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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="users") //Tabla en plural
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El campo de nombre es obligatorio.")
	@Size(min=2, max=30, message="Nombre debe de tener entre 2 y 30 caracteres")
	private String firstname;
	
	@NotEmpty(message="El campo de apellido es obligatorio.")
	@Size(min=2, max=30, message="Apellido debe de tener entre 2 y 30 caracteres")
	private String lastname;
	
	@NotEmpty(message="El campo de email es obligatorio.")
	@Email(message="Ingrese un correo electrónico válido")
	private String email;
	
	@NotEmpty(message="El campo de contraseña es obligatorio.")
	@Size(min=6, max=128, message="La contraseña debe tener más de 6 carácteres")
	private String password;
	
	@Transient //Para que no se almacene en la db
	@NotEmpty(message="El campo de confirmación es obligatorio.")
	@Size(min=6, max=128, message="La confirmación de contraseña debe tener más de 6 carácteres.")
	private String confirm;
	
	@NotEmpty(message="El campo de departamento es obligatorio.")
	private String departamento;
	
	@NotEmpty(message="El campo de universidad es obligatorio.")
	private String universidad;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date created_at;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updated_at;
	
	//==============RELACIONES================
	
	//ONE TO MANY con Convocatoria
	@OneToMany(mappedBy="host", fetch = FetchType.LAZY)
	private List<Convocatoria> convocatoriasPlanned; //La lista de  convocatorias que CREO

	//ONE TO MANY con Mensajes
	@OneToMany(mappedBy="author", fetch = FetchType.LAZY)
	private List<Message> messages; //Los lista de mensajes que CREO
	
	//ManyToMany con Convocatoria
	@ManyToMany(fetch =FetchType.LAZY)
	@JoinTable(
			name = "users_has_convocatorias",
			joinColumns = @JoinColumn(name = "user_id"), //sincronizado a la clase User
			inverseJoinColumns = @JoinColumn(name = "convocatoria_id") //sincronizado a la clase Event
	)
	private List<Convocatoria> convocatoriasInterested; //La lista de convocatorias en las que estoy interesado

	
	//========================================
	
	
	public User() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirm() {
		return confirm;
	}


	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}


	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public String getUniversidad() {
		return universidad;
	}


	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}


	public List<Convocatoria> getConvocatoriasPlanned() {
		return convocatoriasPlanned;
	}


	public void setConvocatoriasPlanned(List<Convocatoria> convocatoriasPlanned) {
		this.convocatoriasPlanned = convocatoriasPlanned;
	}


	public List<Message> getMessages() {
		return messages;
	}


	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	public List<Convocatoria> getConvocatoriasInterested() {
		return convocatoriasInterested;
	}


	public void setConvocatoriasInterested(List<Convocatoria> convocatoriasInterested) {
		this.convocatoriasInterested = convocatoriasInterested;
	}
	
	

	@PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
