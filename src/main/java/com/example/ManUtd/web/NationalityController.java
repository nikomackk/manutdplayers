package com.example.ManUtd.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ManUtd.domain.Nationality;
import com.example.ManUtd.domain.NationalityRepository;


@Controller
public class NationalityController {
	
	@Autowired
	private NationalityRepository nationalityRepository;
	
	//Nationalitylist REST 
	/*@RequestMapping(value= "nationalities", method= RequestMethod.GET)
	public @ResponseBody List<Nationality> nationalityListRest() {
		return (List<Nationality>) nationalityRepository.findAll();
	}*/
	
	// Haetaan tietokannasta kansallisuudet ja ohjataan nationalitylistiin
	@RequestMapping(value= "nationalitylist", method = RequestMethod.GET)
	public String nationalityList(Model model) {
		model.addAttribute("nationalities", nationalityRepository.findAll());
		return "nationalitylist";
	}
	
	// Uuden nationalityn lisääminen html-sivulta
	@RequestMapping(value= "addnationality")
	public String addNationality(Model model) {
		model.addAttribute("nationality", new Nationality());
		return "addnationality";
	}
	
	// Tallennetaan uusi nationality
	@RequestMapping(value= "savenationality", method=RequestMethod.POST)
	public String saveNationality(Nationality nationality) {
		nationalityRepository.save(nationality);
		return "redirect:nationalitylist";
	}
	
	// Poistetaan nationality
	@RequestMapping(value= "deletenationality/{id}", method= RequestMethod.GET)
	public String deleteNationality(@PathVariable("id") Long id, Model model) {
		nationalityRepository.deleteById(id);
		return "redirect:/nationalitylist";
	}

}
