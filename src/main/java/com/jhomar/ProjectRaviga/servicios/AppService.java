package com.jhomar.ProjectRaviga.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jhomar.ProjectRaviga.models.Categoria;
import com.jhomar.ProjectRaviga.models.Convocatoria;
import com.jhomar.ProjectRaviga.models.LoginUser;
import com.jhomar.ProjectRaviga.models.User;
import com.jhomar.ProjectRaviga.repositorios.CategoriaRepository;
import com.jhomar.ProjectRaviga.repositorios.ConvocatoriaRepository;
import com.jhomar.ProjectRaviga.repositorios.MessageRepository;
import com.jhomar.ProjectRaviga.repositorios.UserRepository;



@Service
public class AppService {
	@Autowired
	private UserRepository repositorio_user;
	
	@Autowired
	private MessageRepository repositorio_message;
	
	@Autowired
	private ConvocatoriaRepository repositorio_convocatoria;
	
	@Autowired
	private	CategoriaRepository repositorio_categoria;
	
	public User login(LoginUser nuevoLogin, BindingResult result) {
		
		if(result.hasErrors()) {
			return null;
		}
		
		//Buscamos por correo
		Optional<User> posibleUsuario = repositorio_user.findByEmail(nuevoLogin.getEmail());
		if(!posibleUsuario.isPresent()) {
			result.rejectValue("email", "Unique", "Correo ingresado no existe");
			return null;
		}
		
		User user_login = posibleUsuario.get();
		
		//Comparamos contraseñas encriptadas
		if(! BCrypt.checkpw(nuevoLogin.getPassword(), user_login.getPassword()) ) {
			result.rejectValue("password", "Matches", "Contraseña inválida");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			return user_login; 
		}
		
	}
	
	public User register(User nuevoUsuario, BindingResult result) {
		
		String nuevoEmail = nuevoUsuario.getEmail();
		
		//Revisamos si existe el correo electrónico en BD
		if(repositorio_user.findByEmail(nuevoEmail).isPresent()) {
			result.rejectValue("email", "Unique", "El correo fue ingresado previamente.");
		}
		
		if(! nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirm()) ) {
			result.rejectValue("confirm", "Matches", "Las contraseñas no coiniciden");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			//Encriptamos contraseña
			String contra_encr = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
			nuevoUsuario.setPassword(contra_encr);
			//Guardo usuario
			return repositorio_user.save(nuevoUsuario);
		}
			
	}
	public User find_user(Long id) {
		Optional<User> optionalUser = repositorio_user.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
	}
	
	public List<Categoria> fin_all_categorias(){
		return (List<Categoria>) repositorio_categoria.findAll();
	}
	
	public void save_convocatoria(Convocatoria thisConvoc) {
		repositorio_convocatoria.save(thisConvoc);
	}
	
	
	//Creamos un método que devuelva una lista de eventos de mi estado
	public List<Convocatoria> convocatorias_enmi_u(String universidad) {
			return repositorio_convocatoria.findByUniversidad(universidad);
	}
	
	
	//Creamos un método que devuelva una lista de eventos que no están en mi estado
	public List<Convocatoria> convocatorias_noenmi_u(String universidad){
		return repositorio_convocatoria.findByUniversidadIsNot(universidad);
	}
	public Convocatoria find_convocatoria(Long id) {
		Optional<Convocatoria> optionalEvent= repositorio_convocatoria.findById(id);
        if(optionalEvent.isPresent()) {
            return optionalEvent.get();
        } else {
            return null;
        }
	}
	
	public void save_convocatoria_user(Long user_id, Long convocatoria_id) {
		User myUser = find_user(user_id);
		Convocatoria myConvocatoria = find_convocatoria(convocatoria_id);
		
		myUser.getConvocatoriasInterested().add(myConvocatoria);
		repositorio_user.save(myUser);
	}
	
	
	
	public void remove_convocatoria_user(Long user_id, Long convocatoria_id) {
		
		User myUser = find_user(user_id);
		Convocatoria myConvocatoria = find_convocatoria(convocatoria_id);
		
		//Removemos myEvent de mi lista de Eventos a los que asistiré
		myUser.getConvocatoriasInterested().remove(myConvocatoria);
		repositorio_user.save(myUser);
		
	}
	
	
}
