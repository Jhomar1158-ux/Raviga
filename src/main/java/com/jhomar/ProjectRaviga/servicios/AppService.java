package com.jhomar.ProjectRaviga.servicios;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jhomar.ProjectRaviga.models.LoginUser;
import com.jhomar.ProjectRaviga.models.User;
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
	private ConvocatoriaRepository repositorio_event;
	
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
	
}
