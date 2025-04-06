package yash.s.project.Airline.Reservation.System.Yash.Maincantrollers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import yash.s.project.Airline.Reservation.System.Yash.Entity.JwtRequest;
import yash.s.project.Airline.Reservation.System.Yash.Entity.User;
import yash.s.project.Airline.Reservation.System.Yash.Services.UserServices;
import yash.s.project.Airline.Reservation.System.Yash.Utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/yashairways")
public class LoginCantroller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServices userServices;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest loginRequest) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                )
        );


        User user = userServices.findUserByUserName(loginRequest.getUsername());
        String token = jwtUtil.generateToken(user.getUsername());



        Map<String, String> response = new HashMap<>();
        response.put("message -> ", " Login successful! ");
        response.put("username -> ", user.getUsername());
        response.put("token -> ", token);


        return ResponseEntity.ok(response);
    }


}
