package com.jhomar.ProjectRaviga.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
			model.addAttribute("states", Universidad.Universidades);
			
			return "index.jsp";
		}
		
		
		
		
		
		
		
}
