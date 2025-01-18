package com.example.demo;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.nuprocess.NuProcessBuilder;

@RestController
public class ExecuteController {
    @PostMapping("/execute")
    public String executeInput(@RequestBody String input) {
        try {
            String decoded_input = new String(Base64.getDecoder().decode(input));
            System.out.println(decoded_input);
            String command = String.format("%s", decoded_input);
            NuProcessBuilder pb = new NuProcessBuilder(Arrays.asList("/bin/bash", "-c", command, "-d"));
            ProcessHandler handler = new ProcessHandler();
            pb.setProcessListener(handler);
            pb.start();
            return "Server response: " + decoded_input;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
