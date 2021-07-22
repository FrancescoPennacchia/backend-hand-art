package it.handart.backend;
import it.handart.backend.business.repositories.*;
import it.handart.backend.domain.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UtenteRepository utenteRepository) {
        return (args) -> {
            Utente francesco = new Utente();
            francesco.setEmail("francescopennacchia@libero.it");
            francesco.setPassword(passwordEncoder.encode("ciao1234"));
            francesco.setUsername("Francesco");
            francesco.setNome("Francesco");
            francesco.setCognome("Pennacchia");
            francesco = utenteRepository.save(francesco);
        };
    }
}
