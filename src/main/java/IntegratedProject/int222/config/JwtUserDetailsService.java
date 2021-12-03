package IntegratedProject.int222.config;


import IntegratedProject.int222.models.accounts;
import IntegratedProject.int222.repositories.AccountsRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    AccountsRepository acc ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                if ("khaitong".equals(username)){
//                    return new User("khaitong",
//                            "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                            new ArrayList<>());
//                } else {
//                            throw new UsernameNotFoundException("User not found with username: " );
//                }

//        AuthenticationUser user = userRepository.findById(username).orElseThrow(
//            () -> new UsernameNotFoundException("User not found with username: " + username));
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//               return  user;
        accounts user = acc.findByEmail(username);
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

}
