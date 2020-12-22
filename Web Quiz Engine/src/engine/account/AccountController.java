package engine.account;

import engine.security.H2UserDetailsService;
import engine.security.UserNameExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
public class AccountController {

    @Autowired
    private H2UserDetailsService userDetailsService;

    @PostMapping("/api/register")
    public void register(@RequestBody @Valid Account account) {
        try {
            System.out.println(account.getEmail());
            userDetailsService.addNewUser(account);
        } catch (UserNameExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
