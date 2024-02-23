package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.modeles.Admin;
import ca.christopher.applicationtache.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin saveAdmin(String nom, String prenom, String telephone, String email, String motDePasse) {
        try {
            return adminRepository.save(new Admin(nom, prenom, telephone, email, motDePasse));
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de créer un admin");
        }
    }
   public Optional<Admin> saveAdmin(Admin admin) {
        try {
            return Optional.of(adminRepository.save(admin));
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de créer un admin");
        }
    }

    public Optional<Admin> getAdmin(Long id) {
        return adminRepository.findById(id);
    }
}
