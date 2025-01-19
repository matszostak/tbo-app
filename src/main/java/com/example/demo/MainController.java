package com.example.demo;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zaxxer.nuprocess.NuProcessBuilder;

@Controller
public class MainController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/select-command")
	public String showFruitSelector(Model model) {
		List<String> commands = Arrays.asList("ECHO TEST", "PING", "COMMAND 3", "COMMAND 4");
		List<String> encodedCommands = Arrays.asList("ZWNobyBjb21tYW5kMQ==", "ZWNobyBjb21tYW5kMg==", "ZWNobyBjb21tYW5kMw==", "ZWNobyBjb21tYW5kNA==");
		model.addAttribute("commands", commands);
		model.addAttribute("encodedCommands", encodedCommands);
		return "command";
	}

	@PostMapping("/show-status")
	public String handleFruitSelection(@RequestParam("encodedCommand") String input, Model model) {
		System.out.println(input);
		String decoded_input = new String(Base64.getDecoder().decode(input));
		System.out.println(decoded_input);
		String command = String.format("%s", decoded_input);
		model.addAttribute("selectedCommand", command);
		try {
			NuProcessBuilder pb = new NuProcessBuilder(Arrays.asList("./script.sh", "STARTING COMMAND.", "CHECKS OK", command, "/bin/clear", "echo DONE."));
			ProcessHandler handler = new ProcessHandler();
			pb.setProcessListener(handler);
			pb.start();
			System.out.println("Gut.");
		} catch (Exception e) {
			System.err.println("Error.");
		}
		return "result";
	}
}
