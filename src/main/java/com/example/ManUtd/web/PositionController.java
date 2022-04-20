package com.example.ManUtd.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.ManUtd.domain.Position;
import com.example.ManUtd.domain.PositionRepository;

@Controller
public class PositionController {

	@Autowired
	private PositionRepository positionRepository;
	
	//Pelaajalista REST 
	@RequestMapping(value= "positions", method= RequestMethod.GET)
	public @ResponseBody List<Position> positionListRest() {
		return (List<Position>) positionRepository.findAll();
	}
	
	// Haetaan positionit tietokannasta ja ohjataan listaan
	@RequestMapping(value= "positionlist", method= RequestMethod.GET)
	public String positionList(Model model) {
		model.addAttribute("positions", positionRepository.findAll());
		return "positionlist";
	}
	
	// Ohjataan lisäämään uusi positio
	@RequestMapping(value= "addposition")
	public String addNationality(Model model) {
		model.addAttribute("position", new Position());
		return "addposition";
	}
	
	// Tallennetaan positio
	@RequestMapping(value= "saveposition", method=RequestMethod.POST)
	public String saveNationality(Position position) {
		positionRepository.save(position);
		return "redirect:positionlist";
	}
	
	// Poistetaan positio
	@RequestMapping(value= "deleteposition/{id}", method= RequestMethod.GET)
	public String deletePosition(@PathVariable("id") Long id, Model model) {
		positionRepository.deleteById(id);
		return "redirect:/positionlist";
	}
	
	
}
