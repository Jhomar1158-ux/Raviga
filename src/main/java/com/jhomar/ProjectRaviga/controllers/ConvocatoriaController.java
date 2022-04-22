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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhomar.ProjectRaviga.models.Categoria;
import com.jhomar.ProjectRaviga.models.Convocatoria;
import com.jhomar.ProjectRaviga.models.Message;
import com.jhomar.ProjectRaviga.models.Universidad;
import com.jhomar.ProjectRaviga.models.User;
import com.jhomar.ProjectRaviga.servicios.AppService;

@Controller
public class ConvocatoriaController {
	@Autowired
	private AppService servicio;
		
	@GetMapping("/crear-convocatoria")
	public String crearConvocatoria(HttpSession session, Model model, @ModelAttribute("convocatoria") Convocatoria convocatoria) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		User myUser = servicio.find_user(currentUser.getId());
		model.addAttribute("user", myUser);

		List<Categoria> categorias = servicio.fin_all_categorias();
		
		
		model.addAttribute("categorias", categorias);
		model.addAttribute("universidades", Universidad.Universidades);
		
		return "crearConvocatoria.jsp";
	}
	@PostMapping("/create-convocatoria-process")
	public String create_convocatoria(@Valid @ModelAttribute("convocatoria") Convocatoria convocatoria,
							   BindingResult result, Model model, HttpSession session) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		if(result.hasErrors()) {
			
			User myUser = servicio.find_user(currentUser.getId());
			
			List<Categoria> categorias = servicio.fin_all_categorias();
			model.addAttribute("categorias", categorias);
			model.addAttribute("universidades", Universidad.Universidades);
			model.addAttribute("user", myUser);
			
			return "crearConvocatoria.jsp";
		}
		
		servicio.save_convocatoria(convocatoria);
		return "redirect:/dashboard";
	}
	
	
	@GetMapping("/convocatoria-join/{convocatoria_id}")
	public String join_convocatoria(@PathVariable("convocatoria_id") Long convocatoria_id,
							HttpSession session) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		servicio.save_convocatoria_user(currentUser.getId(), convocatoria_id);
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("/convocatoria-remove/{convocatoria_id}")
	public String remove_convocatoria(@PathVariable("convocatoria_id") Long convocatoria_id, 
								HttpSession session) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		servicio.remove_convocatoria_user(currentUser.getId(), convocatoria_id);
		
		return "redirect:/dashboard";
		
	}
	
	@GetMapping("/convocatoria-show/{convocatoria_id}")
	public String show_convocatoria(@PathVariable("convocatoria_id") Long convocatoria_id,
							HttpSession session,
							Model model,
							@ModelAttribute("message") Message message) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		Convocatoria convocatoriaThis = servicio.find_convocatoria(convocatoria_id);
		model.addAttribute("convocatoriaThis", convocatoriaThis);
		
		return "showConvocatoria.jsp";
		
	}
	
	@PostMapping("/convocatoria-message")
	public String message(@Valid @ModelAttribute("message") Message message, 
							BindingResult result,
							HttpSession session, Model model) {
		/*REVISAMOS SESION*/
		User currentUser = (User)session.getAttribute("user_session");
		if(currentUser == null) {
			return "redirect:/";
		}
		/*REVISAMOS SESION*/
		
		//
		Long convocatoria_id=message.getConvocatoria().getId();
		Convocatoria convocatoria_this= servicio.find_convocatoria(convocatoria_id);
		
		if(result.hasErrors()) {
			model.addAttribute("convocatoria_this", convocatoria_this);
			return "showConvocatoria.jsp";
		}
		
		servicio.save_message(message);
		
		return "redirect:/convocatoria-show/"+convocatoria_id;
		
		
	}
	
}
