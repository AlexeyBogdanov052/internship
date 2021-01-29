package staff.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import staff.domain.Employee;
import staff.domain.SchemeOperations;
import staff.repository.EmployeeRepository;
import staff.repository.SchemeOperationsReposiroty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private EmployeeRepository empRep;
    @Autowired
    private SchemeOperationsReposiroty soRep;

    @Override
    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Employee emp = empRep.findByLogin(name);
        String psswd = DigestUtils.sha256Hex(password);
        String hash = DigestUtils.sha256Hex(psswd + emp.getSalt());

        Long sr= emp.getId_scheme_rights();
        Iterable<SchemeOperations> storage = soRep.findAll();
        List<GrantedAuthority> listRole = new ArrayList<GrantedAuthority>();

        for (SchemeOperations so: storage) {
            if (so.getScheme_id() == sr)
                if (so.getOperation_id() == 4) {
                    listRole.add(new SimpleGrantedAuthority("CREATE_SCHEME"));
                } else if (so.getOperation_id() == 5) {
                    listRole.add(new SimpleGrantedAuthority("WATCH_SCHEME"));
                } else if (so.getOperation_id() == 6) {
                    listRole.add(new SimpleGrantedAuthority("UPDATE_SCHEME"));
                } else if (so.getOperation_id() == 7) {
                    listRole.add(new SimpleGrantedAuthority("DELETE_SCHEME"));
                } else if (so.getOperation_id() == 8) {
                    listRole.add(new SimpleGrantedAuthority("UPDATE_SCHEME"));;
                } else if (so.getOperation_id() == 9) {
                    listRole.add(new SimpleGrantedAuthority("UPDATE_SCHEME"));
                } else if (so.getOperation_id() == 10) {
                    listRole.add(new SimpleGrantedAuthority("CREATE_EMP"));
                } else if (so.getOperation_id() == 11) {
                    listRole.add(new SimpleGrantedAuthority("UPDATE_EMP"));
                } else if (so.getOperation_id() == 13) {
                    listRole.add(new SimpleGrantedAuthority("UPDATE_EMP"));
                }
        }

        if (!emp.getHash().equals(hash)) {
            throw new BadCredentialsException("Bad Credentials");
        }
        return new UsernamePasswordAuthenticationToken(
                name, hash, listRole);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
