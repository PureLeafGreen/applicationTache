package ca.christopher.applicationtache;

import ca.christopher.applicationtache.modeles.Admin;
import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.services.AdminService;
import ca.christopher.applicationtache.services.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class ApplicationTacheApplication implements CommandLineRunner {

    private final UtilisateurService utilisateurService;
    private final AdminService adminService;

    public ApplicationTacheApplication(UtilisateurService utilisateurService, AdminService adminService) {
        this.utilisateurService = utilisateurService;
        this.adminService = adminService;
    }


    public static void main(String[] args) {
        SpringApplication.run(ApplicationTacheApplication.class, args);
    }

    public void run(String... args) throws Exception {
        Utilisateur utilisateur1 = utilisateurService.saveUser("Christopher", "William","514-623-2702","volteczx8@gmail.com","1234");
        Admin admin1 = adminService.saveAdmin("Max", "Gourdin","438-623-2722","max@gmail.com","1234");


        System.out.println(utilisateur1);
        System.out.println(admin1);
    }
}
