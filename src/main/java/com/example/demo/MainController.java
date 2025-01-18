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

	@GetMapping("/show-fruit")
	public String showFruitSelector(Model model) {
		List<String> fruits = Arrays.asList("echo 123", "command2", "command3", "command4");
		model.addAttribute("fruits", fruits);
		return "fruit-selector";
	}

	@PostMapping("/select-fruit")
	public String handleFruitSelection(@RequestParam("fruit") String input, Model model) {
		model.addAttribute("selectedFruit", input);
		System.out.println(input);
		try {
			String decoded_input = new String(Base64.getDecoder().decode(input));
			System.out.println(decoded_input);
			String command = String.format("%s", decoded_input);
			NuProcessBuilder pb = new NuProcessBuilder(Arrays.asList("./script.sh", "1", "2", command, "echo XD"));
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
