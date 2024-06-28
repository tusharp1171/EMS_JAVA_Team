package com.bezkoder.springjwt.controllers;

import java.net.http.HttpHeaders;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bezkoder.springjwt.dto.UserAddressDto;
import com.bezkoder.springjwt.dto.UserEducationDetails;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  RestTemplate restTemplate;
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()),
               signUpRequest.getMobile());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }
    user.setRoles(roles);
    userRepository.save(user);
    
    UserEducationDetails userEducationDetails=signUpRequest.getUserEducationDetails();
    userEducationDetails.setUserId(user.getId());
    UserAddressDto userAddressDto=signUpRequest.getUserAddressDto();
    userAddressDto.setUserId(user.getId());
    System.out.println(user.getId());
 
    

   if (signUpRequest.getUserAddressDto() != null) {
       restTemplate.postForEntity(
                "http://192.168.1.144:8081/userAdresses/add",
                userAddressDto,
                UserAddressDto.class);    
      
    }
    if (signUpRequest.getUserEducationDetails() != null) {
    restTemplate.postForEntity(
                "http://192.168.1.144:8081/userEducationDetails/add",
                userEducationDetails,
                UserEducationDetails.class);  
    }
   
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  
  @DeleteMapping("/deleteUser/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable long id) {
      if (userRepository.existsById(id)) {
          User user = userRepository.findById(id).orElse(null);
          
          Long userid=user.getId();

        
          restTemplate.delete(
                  "http://192.168.1.144:8081/userEducationDetails/usereducation/" + userid);
     restTemplate.delete(
                  "http://192.168.1.144:8081/userAdresses/user/" + userid);
              userRepository.deleteById(id);
              return ResponseEntity.ok("User and associated details deleted successfully.");
          
      } else {
          return ResponseEntity.notFound().build();
      }
  }
  
//  @PostMapping("/logout")
//  public ResponseEntity<?> logoutUser(HttpServletRequest request) {
//      SecurityContextHolder.clearContext();
//            return ResponseEntity.ok(new MessageResponse("Logout successful!"));
//  }
  
  
  @PostMapping("/logout")
  public ResponseEntity<?> logoutUser(HttpServletRequest request) {
      // Clear the security context
      SecurityContextHolder.clearContext();
      
      // Invalidate the HTTP session
      HttpSession session = request.getSession(false);
      if (session != null) {
          session.invalidate();
      }
      
      return ResponseEntity.ok(new MessageResponse("Logout successful!"));
  }

  
 
}
