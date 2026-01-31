package com.AuthService.Controller;

import java.util.List;
import com.AuthService.Services.AuthService;
import com.AuthService.Dto.RegisterDto;
import com.AuthService.Dto.LoginDto;
import com.AuthService.Entity.AppUser;
import com.AuthService.Repositorys.AuthRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final AuthRepo authRepo;

    public AuthController(AuthService authService, AuthRepo authRepo) {
        this.authService = authService;
        this.authRepo = authRepo;
    }

    @GetMapping("/test")
    public String testController() {
        return "Hello this is the test endpoint\n";
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto dto) { 
        System.out.println("Registering the user......");
        AppUser user = authService.registerUser(dto);
        if (user == null) {
            ResponseEntity.badRequest().body("Server Error: Unable to register the user");
        }
        return ResponseEntity.ok("User registered SuccessFully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto dto) {
        System.out.println(dto.getEmail());
        return ResponseEntity.ok(authService.loginUser(dto));
    }

    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return authRepo.findAll();
    }
}
