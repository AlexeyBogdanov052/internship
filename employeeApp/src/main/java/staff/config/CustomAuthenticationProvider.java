package staff.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import staff.domain.Employee;
import staff.repository.EmployeeRepository;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private EmployeeRepository empRep;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Employee emp = empRep.findByLogin(name);
        String psswd = DigestUtils.sha256Hex(password);
        String hash = DigestUtils.sha256Hex(psswd + emp.getSalt());

        if (!emp.getHash().equals(hash)) {
            throw new BadCredentialsException("Bad Credentials");
        }
        return new UsernamePasswordAuthenticationToken(
                name, hash, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
