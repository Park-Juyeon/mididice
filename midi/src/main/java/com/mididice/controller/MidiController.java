package com.mididice.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.InvalidMidiDataException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mididice.service.ImageService;
import com.mididice.service.MidiService;
import com.mididice.service.MidiToMp3Service;

@Controller
public class MidiController {
	private static final Logger logger = LoggerFactory.getLogger(MidiController.class);
	
	private MidiService midiService;
	private MidiToMp3Service mp3Service;
	private ImageService imageService;
	
	public MidiController(MidiService midiService, MidiToMp3Service mp3Service, ImageService imageService) {
		this.midiService = midiService;
		this.mp3Service = mp3Service;
		this.imageService = imageService;
	}
	
	/*
	 * 
	 * iputfile list(max 36)
	 * outfile name
	 * 
	 * */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String appendMidiTest(
			@RequestParam(value="midis")String[] names, //11.midi
			@RequestParam(value="seconds")String seconds,
			@RequestParam(value="bar")String bar,
			@RequestParam(value="bpm")String bpm,
			HttpServletRequest request,
			RedirectAttributes rediAttr) throws IOException, InvalidMidiDataException{
				
		String fileName = midiService.mergeMidi(names);
		String mp3Name = mp3Service.midiToMp3(fileName);
		
		String[] imgNames = midiService.getImageArr(names);
		String enc = midiService.getRandomFileName(fileName);
		
		//append image 3x3, 4x4, 5x5 
		String resImg = imageService.mergeImage(bar, imgNames, enc);
		
		String url = "redirect:/res/"+enc;
		
		rediAttr.addFlashAttribute("midiFile", mp3Name);
		rediAttr.addFlashAttribute("seconds", seconds);
		rediAttr.addFlashAttribute("bar", bar);
		rediAttr.addFlashAttribute("bpm", bpm);
		rediAttr.addFlashAttribute("resImg", resImg);
		logger.info("midi output :", mp3Name);
		return url;
		
	}
	
}
