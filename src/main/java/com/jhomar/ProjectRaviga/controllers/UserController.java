package com.jhomar.ProjectRaviga.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhomar.ProjectRaviga.models.Convocatoria;
import com.jhomar.ProjectRaviga.models.LoginUser;
import com.jhomar.ProjectRaviga.models.Universidad;
import com.jhomar.ProjectRaviga.models.User;
import com.jhomar.ProjectRaviga.servicios.AppService;




@Controller
public class UserController {
	//Importamos el Servicio
		@Autowired
		private AppService servicio;
		
		@GetMapping("/")
		public String index(@ModelAttribute("nuevoUsuario") User nuevoUsuario,
							@ModelAttribute("nuevoLogin") LoginUser nuevoLogin,
							Model model) {
			
			//Agregamos los opciones de State a nuestro formulario
			//model.addAttribute("states", Universidad.Universidades);
			List<Convocatoria> allConvocatorias = servicio.find_all_convocatorias();
			
			model.addAttribute("allConvocatorias", allConvocatorias);
			
			
			return "index.jsp";
		}
		
		@GetMapping("/login")
		public String login(@ModelAttribute("nuevoUsuario") User nuevoUsuario,
							@ModelAttribute("nuevoLogin") LoginUser nuevoLogin,
							Model model) {
			
			//Agregamos los opciones de Universidad a nuestro formulario
//			model.addAttribute("universidades", Universidad.Universidades);
			
			return "login.jsp";
		}
		
		@PostMapping("/loginProcess")
		public String login2(@Valid @ModelAttribute("nuevoLogin") LoginUser nuevoLogin,
							BindingResult result, Model model, HttpSession session) {
								
			User user = servicio.login(nuevoLogin, result);
			if(result.hasErrors()) {
				model.addAttribute("nuevoUsuario", new User());				
				return "login.jsp";
			}
			
			session.setAttribute("user_session", user);
			return "redirect:/dashboard";
								
		}
		
		
		@GetMapping("/registro")
		public String registro(@ModelAttribute("nuevoUsuario") User nuevoUsuario,
							@ModelAttribute("nuevoLogin") LoginUser nuevoLogin,
							Model model) {
			
			//Agregamos los opciones de Universidad a nuestro formulario
			model.addAttribute("universidades", Universidad.Universidades);
			
			return "registro.jsp";
		}
		
		@PostMapping("/registroProcess")
		public String registro2(@Valid @ModelAttribute("nuevoLogin") User nuevoUsuario,
							BindingResult result, Model model, HttpSession session) {
								
			servicio.register(nuevoUsuario, result);
			if(result.hasErrors()) {
				System.out.println(result);
				model.addAttribute("nuevoLogin", new LoginUser());
				model.addAttribute("universidades", Universidad.Universidades);
				return "registro.jsp";
			}
			
			session.setAttribute("user_session", nuevoUsuario);
			return "redirect:/dashboard";
								
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.removeAttribute("user_session");
			return "redirect:/";
		}
		
		@GetMapping("/dashboard")
		public String dashboard(HttpSession session, Model model, @ModelAttribute("convocatoria") Convocatoria convocatoria) {
			/*REVISAMOS SESION*/
			User currentUser = (User)session.getAttribute("user_session");
			if(currentUser == null) {
				return "redirect:/";
			}
			/*REVISAMOS SESION*/
			
			User myUser = servicio.find_user(currentUser.getId());
			model.addAttribute("user", myUser);

			//Lista de Eventos
			//Obtenemos el estado de mi usuario
			String miEUniversidad = currentUser.getUniversidad();
			
			//Creamos una lista de Eventos que tengan mi estado
			List<Convocatoria> convocatorias_enmi_u = servicio.convocatorias_enmi_u(miEUniversidad);
//			//Creamos una lista de Eventos que no tengan mi estado 
			List<Convocatoria> convocatorias_noenmi_u = servicio.convocatorias_noenmi_u(miEUniversidad);
//			
			model.addAttribute("convocatorias_enmi_u", convocatorias_enmi_u);
			model.addAttribute("convocatorias_noenmi_u", convocatorias_noenmi_u);
//			
			return "dashboard.jsp";
		}
		
		
		
		
		
		
		
		
		
}
