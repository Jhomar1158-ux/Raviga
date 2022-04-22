package com.jhomar.ProjectRaviga.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
		
		
		
		
		
		
}
