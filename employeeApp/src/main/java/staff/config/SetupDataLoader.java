package staff.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import staff.repository.EmployeeRepository;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private EmployeeRepository empRep;

    boolean alreadySetup = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

    }
}
