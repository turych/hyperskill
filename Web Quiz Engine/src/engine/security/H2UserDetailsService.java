package engine.security;

import engine.account.Account;
import engine.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class H2UserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);

        if(account == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("USER"));

        return new QuizUser(account, authorities);
    }

    public Account addNewUser(Account account) throws UserNameExistException {
        Account isExist = accountRepository.findByEmail(account.getEmail());

        if (isExist != null) {
            throw new UserNameExistException("User with this email is already exist.");
        }

        account.setPassword(bcryptEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }
}
