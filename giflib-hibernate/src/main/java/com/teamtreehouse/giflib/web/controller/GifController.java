package com.teamtreehouse.giflib.web.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamtreehouse.giflib.model.Gif;
import com.teamtreehouse.giflib.service.CategoryService;
import com.teamtreehouse.giflib.service.GifService;
import com.teamtreehouse.giflib.web.FlashMessage;
import com.teamtreehouse.giflib.web.FlashMessage.Status;

@Controller
public class GifController {
	
	@Autowired
	private GifService gifService;
	
	@Autowired
	private CategoryService categoryService;

	//Home page- index of all GIFs
	@RequestMapping("/")
	public String listGifs(Model model) {
		List<Gif> gifs=gifService.findAll();
		
		model.addAttribute("gifs", gifs);
		return "gif/index";
	}
	
	//Single GIF page
	@RequestMapping("/gifs/{gifId}")
	public String gifDetails(@PathVariable Long gifId,Model model) {
		Gif gif=gifService.findById(gifId);
		
		model.addAttribute("gif", gif);
		return "gif/details";
	}
	
	//GIF image data
	@RequestMapping("/gifs/{gifId}.gif")
	@ResponseBody
	public byte[] gifImage(@PathVariable Long gifId) {
		
		return gifService.findById(gifId).getBytes();
	}
	
	//Upload a new GIF
	@RequestMapping(value="/gifs", method=RequestMethod.POST)
	public String addGif(Gif gif, @RequestParam MultipartFile file,RedirectAttributes redirectAttributes) {
		gifService.save(gif, file);
		
		//Add flash message for success
		redirectAttributes.addFlashAttribute("flash", new FlashMessage("GIF successfully uploade!", Status.SUCCESS));
		return String.format("redirect:/gifs/%s", gif.getId());
	}
	
	
	//Form for uploading a new GIF
	@RequestMapping("/upload")
	public String formNewGif(Model model) {
		if (model.containsAttribute("gif")) {
			model.addAttribute("gif", new Gif());
		}
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("action", "/gifs");
		model.addAttribute("heading", "Upload");
		model.addAttribute("submit", "Add");
		
		
		return "gif/form";
	}
	
		//Form for editing an existing GIF
		@RequestMapping("/gifs/{gifId}/edit")
		public String formEditGif(@PathVariable Long gifId,Model model) {
			if (model.containsAttribute("gif")) {
				model.addAttribute("gif", gifService.findById(gifId));
			}
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("action", String.format("/gifs/%s", gifId));
			model.addAttribute("heading", "Edit GIF");
			model.addAttribute("submit", "Update");
			
			
			return "gif/form";
		}
	
		//Delete an existing GIF
		@RequestMapping(value="/gifs/{gifId}/delete", method=RequestMethod.POST)
		public String deleteGif(@PathVariable Long gifId, RedirectAttributes redirectAttributes) {
			Gif gif=gifService.findById(gifId);
			gifService.delete(gif);
			redirectAttributes.addFlashAttribute("flash", new FlashMessage("GIF deleted", Status.SUCCESS));
			
			return "redirect:/";
		}
	
		//Mark / unmark an existing GIF as a favorite
		@RequestMapping(value="/gifs/{gifId}/favorite", method=RequestMethod.POST)
		public String toggleFavorite(@PathVariable Long gifId,HttpServletRequest request) {
			
			Gif gif=gifService.findById(gifId);
			gifService.toggleFavorite(gif);
			return String.format("redirect:%s", request.getHeader("referer"));
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
