package IntegratedProject.int222.controllers;

import java.util.List;
import java.util.Objects;

import IntegratedProject.int222.config.JwtTokenUtil;
import IntegratedProject.int222.config.JwtUserDetailsService;

import IntegratedProject.int222.exception.MessageException;
import IntegratedProject.int222.models.JwtRequest;
import IntegratedProject.int222.models.JwtResponse;
import IntegratedProject.int222.models.accounts;
import IntegratedProject.int222.repositories.AccountsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AccountsRepository accRepo;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public long idacc;
    public accounts a;
    @GetMapping("/alluser")
    public List<accounts> getUser(){
        return accRepo.findAll();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println("1");
        System.out.println(authenticationRequest.getUsername());
        System.out.println(authenticationRequest.getPassword());
        System.out.println(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        System.out.println("2");
         UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        System.out.println("3");
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(accRepo.findByEmail(authenticationRequest.getUsername()).getAccountId());
        return ResponseEntity.ok(new JwtResponse(token,accRepo.findByEmail(authenticationRequest.getUsername()).getAccountId()));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody accounts acc) throws RuntimeException {
        System.out.println(acc.getEmail());
//        this.a = accRepo.findByEmail(acc.getEmail());
//        System.out.println(this.a.getEmail());

        if(accRepo.findByEmail(acc.getEmail()) != null){
            throw  new MessageException("Is have already email exist");
        }else {

            this.idacc = accRepo.findAll().size()-1 == -1? 300001: accRepo.findAll().get(accRepo.findAll().size()-1).getAccountId()+1;
            accounts accnew = new accounts(this.idacc,acc.getFirstName(),acc.getLastName(),acc.getEmail(),bcryptEncoder.encode(acc.getPassword()),acc.getAccountRole());
            acc.setPassword(bcryptEncoder.encode(acc.getPassword()));

            return ResponseEntity.ok(accRepo.save(accnew));

        }

    }


}
