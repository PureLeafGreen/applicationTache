package ca.christopher.applicationtache.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.christopher.applicationtache.modeles.Admin;
import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.AdminRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AdminServiceTest {
    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    /**
     * Method under test: {@link AdminService#saveAdmin(Admin)}
     */
    @Test
    void testSaveAdmin() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setGroupe(new Groupe());
        admin.setId(1L);
        admin.setNom("Nom");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Prenom");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("jane.doe@example.org");
        admin2.setGroupe(groupe);
        admin2.setId(1L);
        admin2.setNom("Nom");
        admin2.setPassword("iloveyou");
        admin2.setPhone("6625550144");
        admin2.setPrenom("Prenom");
        admin2.setRole(Role.ADMIN);
        admin2.setTaches(new ArrayList<>());

        Groupe groupe2 = new Groupe();
        groupe2.setAdmin(admin2);
        groupe2.setCode("Code");
        groupe2.setDescription("The characteristics of someone or something");
        groupe2.setId(1L);
        groupe2.setNom("Nom");
        groupe2.setTaches(new ArrayList<>());
        groupe2.setUtilisateurs(new ArrayList<>());

        Admin admin3 = new Admin();
        admin3.setEmail("jane.doe@example.org");
        admin3.setGroupe(groupe2);
        admin3.setId(1L);
        admin3.setNom("Nom");
        admin3.setPassword("iloveyou");
        admin3.setPhone("6625550144");
        admin3.setPrenom("Prenom");
        admin3.setRole(Role.ADMIN);
        admin3.setTaches(new ArrayList<>());
        when(adminRepository.save(Mockito.<Admin>any())).thenReturn(admin3);

        Groupe groupe3 = new Groupe();
        groupe3.setAdmin(new Utilisateur());
        groupe3.setCode("Code");
        groupe3.setDescription("The characteristics of someone or something");
        groupe3.setId(1L);
        groupe3.setNom("Nom");
        groupe3.setTaches(new ArrayList<>());
        groupe3.setUtilisateurs(new ArrayList<>());

        Utilisateur admin4 = new Utilisateur();
        admin4.setEmail("jane.doe@example.org");
        admin4.setGroupe(groupe3);
        admin4.setId(1L);
        admin4.setNom("Nom");
        admin4.setPassword("iloveyou");
        admin4.setPhone("6625550144");
        admin4.setPrenom("Prenom");
        admin4.setRole(Role.ADMIN);
        admin4.setTaches(new ArrayList<>());

        Groupe groupe4 = new Groupe();
        groupe4.setAdmin(admin4);
        groupe4.setCode("Code");
        groupe4.setDescription("The characteristics of someone or something");
        groupe4.setId(1L);
        groupe4.setNom("Nom");
        groupe4.setTaches(new ArrayList<>());
        groupe4.setUtilisateurs(new ArrayList<>());

        Admin admin5 = new Admin();
        admin5.setEmail("jane.doe@example.org");
        admin5.setGroupe(groupe4);
        admin5.setId(1L);
        admin5.setNom("Nom");
        admin5.setPassword("iloveyou");
        admin5.setPhone("6625550144");
        admin5.setPrenom("Prenom");
        admin5.setRole(Role.ADMIN);
        admin5.setTaches(new ArrayList<>());

        // Act
        Optional<Admin> actualSaveAdminResult = adminService.saveAdmin(admin5);

        // Assert
        verify(adminRepository).save(Mockito.<Admin>any());
        assertTrue(actualSaveAdminResult.isPresent());
    }

    /**
     * Method under test:
     * {@link AdminService#saveAdmin(String, String, String, String, String)}
     */
    @Test
    void testSaveAdmin2() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setGroupe(new Groupe());
        admin.setId(1L);
        admin.setNom("Nom");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Prenom");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("jane.doe@example.org");
        admin2.setGroupe(groupe);
        admin2.setId(1L);
        admin2.setNom("Nom");
        admin2.setPassword("iloveyou");
        admin2.setPhone("6625550144");
        admin2.setPrenom("Prenom");
        admin2.setRole(Role.ADMIN);
        admin2.setTaches(new ArrayList<>());

        Groupe groupe2 = new Groupe();
        groupe2.setAdmin(admin2);
        groupe2.setCode("Code");
        groupe2.setDescription("The characteristics of someone or something");
        groupe2.setId(1L);
        groupe2.setNom("Nom");
        groupe2.setTaches(new ArrayList<>());
        groupe2.setUtilisateurs(new ArrayList<>());

        Admin admin3 = new Admin();
        admin3.setEmail("jane.doe@example.org");
        admin3.setGroupe(groupe2);
        admin3.setId(1L);
        admin3.setNom("Nom");
        admin3.setPassword("iloveyou");
        admin3.setPhone("6625550144");
        admin3.setPrenom("Prenom");
        admin3.setRole(Role.ADMIN);
        admin3.setTaches(new ArrayList<>());
        when(adminRepository.save(Mockito.<Admin>any())).thenReturn(admin3);

        // Act
        Admin actualSaveAdminResult = adminService.saveAdmin("Nom", "Prenom", "6625550144", "jane.doe@example.org",
                "Mot De Passe");

        // Assert
        verify(adminRepository).save(Mockito.<Admin>any());
        assertSame(admin3, actualSaveAdminResult);
    }

    /**
     * Method under test: {@link AdminService#getAdmin(Long)}
     */
    @Test
    void testGetAdmin() {
        // Arrange
        Groupe groupe = new Groupe();
        groupe.setAdmin(new Utilisateur());
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setGroupe(groupe);
        admin.setId(1L);
        admin.setNom("Nom");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Prenom");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe2 = new Groupe();
        groupe2.setAdmin(admin);
        groupe2.setCode("Code");
        groupe2.setDescription("The characteristics of someone or something");
        groupe2.setId(1L);
        groupe2.setNom("Nom");
        groupe2.setTaches(new ArrayList<>());
        groupe2.setUtilisateurs(new ArrayList<>());

        Admin admin2 = new Admin();
        admin2.setEmail("jane.doe@example.org");
        admin2.setGroupe(groupe2);
        admin2.setId(1L);
        admin2.setNom("Nom");
        admin2.setPassword("iloveyou");
        admin2.setPhone("6625550144");
        admin2.setPrenom("Prenom");
        admin2.setRole(Role.ADMIN);
        admin2.setTaches(new ArrayList<>());
        Optional<Admin> ofResult = Optional.of(admin2);
        when(adminRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Optional<Admin> actualAdmin = adminService.getAdmin(1L);

        // Assert
        verify(adminRepository).findById(Mockito.<Long>any());
        assertTrue(actualAdmin.isPresent());
        assertSame(ofResult, actualAdmin);
    }

    /**
     * Method under test: {@link AdminService#getAdmin(Long)}
     */
    @Test
    void testGetAdmin2() {
        // Arrange
        when(adminRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalStateException("foo"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> adminService.getAdmin(1L));
        verify(adminRepository).findById(Mockito.<Long>any());
    }
}
