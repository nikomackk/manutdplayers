package com.example.ManUtd.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ManUtd.domain.NationalityRepository;
import com.example.ManUtd.domain.Player;
import com.example.ManUtd.domain.PlayerRepository;
import com.example.ManUtd.domain.PositionRepository;

@Controller
public class PlayerController {

	@Autowired
	private NationalityRepository nationalityRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="login")
	public String login() {
		return "login";
	}
	
	
	//Pelaajalista REST 
	@RequestMapping(value= "players", method= RequestMethod.GET)
	public @ResponseBody List<Player> playerListRest() {
		return (List<Player>) playerRepository.findAll();
	}
	
	
	// Haetaan tietokannasta kirjat ja ohjataan playerlist.html
	@RequestMapping(value = "playerlist", method= RequestMethod.GET)
	public String playerList(Model model) {
		model.addAttribute("players", playerRepository.findAll());
		return "playerlist";
	}
	
	// Uuden pelaajan lisääminen html-sivulta
	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value= "addplayer")
	public String addPlayer(Model model) {
		model.addAttribute("player", new Player());
		model.addAttribute("nationalities", nationalityRepository.findAll());
		model.addAttribute("positions", positionRepository.findAll());
		return "addplayer";
	}
	
	// Uuden pelaajan tietojen validointi
	@RequestMapping(value= "add", method= RequestMethod.POST)
	public String addingPlayerSubmit(@Valid Player player, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("nationalities", nationalityRepository.findAll());
			model.addAttribute("positions", positionRepository.findAll());
			return "addplayer";
		} else {
			System.out.println("Tallennetaan pelaaja" + player);
			playerRepository.save(player);
			return "redirect:/playerlist";
		}
	}
	
	//Tallennetaan pelaaja playerRepositoryyn 
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String savePlayer(Player player) {
		playerRepository.save(player);
		return "redirect:playerlist";
	}
	
	//Pelaajan tietojen muokkaus 
	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="edit/{id}")
	public String editPlayer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("player", playerRepository.findById(id));
		model.addAttribute("nationalities", nationalityRepository.findAll());
		model.addAttribute("positions", positionRepository.findAll());
		return "editplayer";
	}
	
	// Muokattavan pelaajan tietojen validointi
	
	@RequestMapping(value= "editsubmit", method= RequestMethod.POST)
	public String editSubmitPlayer(@Valid Player player, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("nationalities", nationalityRepository.findAll());
			model.addAttribute("positions", positionRepository.findAll());
			return "editplayer";
		} else {
			playerRepository.save(player);
			return "redirect:playerlist";
		}
	}
	
	//Poistetaan pelaaja tietokannasta ID:n perusteella
	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value= "delete/{id}", method= RequestMethod.GET)
	public String deletePlayer(@PathVariable("id") Long id, Model model) {
		playerRepository.deleteById(id);
		return "redirect:/playerlist";
	}
		
	
}
