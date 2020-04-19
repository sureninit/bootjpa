package com.bootJpa.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bootJpa.dao.AlienRepo;
//fine programee
//@RestController // restapi banaunako lagi
@Controller// servlet banaunako lagi
public class AlienController {
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		
		return "home.jsp";
	}

	 @RequestMapping("/addAlien")
	@ResponseBody     // method String bhayesi spring le name banne bujcha tyasaile purani data output garna @RequestBody rakhana parcha
	 public String addAlien(Alien alien) {
		repo.save(alien);
		 return "home.jsp";
	 }
	 @RequestMapping("/getAlien")
	 public ModelAndView addAlien(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("show.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien()); 
		//mv.addobject(alien) garnu aghi objecet chaina bhane new Alien() object banaucha
		mv.addObject(alien);
		return mv;
}
	 //@RequestMapping(value = "/alien")
	 @RequestMapping("/alien")
	@ResponseBody
	 public List<Alien> allAlien() {
		return repo.findAll();
}
	 @PostMapping("/alien")
	 @ResponseBody
	 public Alien postAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
}
	 @DeleteMapping("/alien/{aid}")
	 @ResponseBody
	 public String deleteAlien(@PathVariable int aid) {
		Alien al = repo.getOne(aid);
		repo.delete(al);
		return "deleted";
}
	 @PutMapping("/alien")
	 @ResponseBody
	 public Alien saveOrUpdateAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
}
	 
	 
	 
	 @RequestMapping(value = "/alien/{aid}")
	 @ResponseBody
	 public Optional<Alien> alienaid(@PathVariable int aid) {
		return repo.findById(aid);
}
	 
}
