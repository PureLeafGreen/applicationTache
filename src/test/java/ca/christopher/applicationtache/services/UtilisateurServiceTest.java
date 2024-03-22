package ca.christopher.applicationtache.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.christopher.applicationtache.DTO.CredentialsDTO;
import ca.christopher.applicationtache.DTO.LoginUserDTO;
import ca.christopher.applicationtache.DTO.RegisterUserDTO;
import ca.christopher.applicationtache.DTO.UtilisateurDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.modeles.Admin;
import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Tache;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.UtilisateurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UtilisateurService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UtilisateurServiceTest {
    @MockBean
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Method under test: {@link UtilisateurService#saveUser(RegisterUserDTO)}
     */
    @Test
    void testSaveUser() {
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

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setGroupe(groupe2);
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur);
        when(utilisateurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(AppException.class, () -> utilisateurService
                .saveUser(new RegisterUserDTO("Nom", "Prenom", "6625550144", "jane.doe@example.org", "iloveyou")));
        verify(utilisateurRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test:
     * {@link UtilisateurService#saveUser(String, String, String, String, String)}
     */
    @Test
    void testSaveUser2() {
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

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setGroupe(groupe2);
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());
        when(utilisateurRepository.save(Mockito.<Utilisateur>any())).thenReturn(utilisateur);

        // Act
        Utilisateur actualSaveUserResult = utilisateurService.saveUser("Nom", "Prenom", "6625550144",
                "jane.doe@example.org", "Mot De Passe");

        // Assert
        verify(utilisateurRepository).save(Mockito.<Utilisateur>any());
        assertSame(utilisateur, actualSaveUserResult);
    }

    /**
     * Method under test: {@link UtilisateurService#getUser(Long)}
     */
    @Test
    void testGetUser() {
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

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setGroupe(groupe2);
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Optional<UtilisateurDTO> actualUser = utilisateurService.getUser(1L);

        // Assert
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        UtilisateurDTO getResult = actualUser.get();
        assertEquals("6625550144", getResult.getPhone());
        assertEquals("Nom", getResult.getNom());
        assertEquals("Prenom", getResult.getPrenom());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals(1L, getResult.getId());
        assertTrue(actualUser.isPresent());
    }

    /**
     * Method under test: {@link UtilisateurService#getUser(Long)}
     */
    @Test
    void testGetUser2() {
        // Arrange
        Optional<Utilisateur> emptyResult = Optional.empty();
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act
        Optional<UtilisateurDTO> actualUser = utilisateurService.getUser(1L);

        // Assert
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        assertFalse(actualUser.isPresent());
        assertSame(emptyResult, actualUser);
    }

    /**
     * Method under test: {@link UtilisateurService#getUser(Long)}
     */
    @Test
    void testGetUser3() {
        // Arrange
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalStateException("foo"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> utilisateurService.getUser(1L));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UtilisateurService#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail() {
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

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setGroupe(groupe2);
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur);
        when(utilisateurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        UtilisateurDTO actualUserByEmail = utilisateurService.getUserByEmail("jane.doe@example.org");

        // Assert
        verify(utilisateurRepository).findByEmail(eq("jane.doe@example.org"));
        assertEquals("6625550144", actualUserByEmail.getPhone());
        assertEquals("Nom", actualUserByEmail.getNom());
        assertEquals("Prenom", actualUserByEmail.getPrenom());
        assertEquals("jane.doe@example.org", actualUserByEmail.getEmail());
        assertEquals(1L, actualUserByEmail.getId());
    }

    /**
     * Method under test: {@link UtilisateurService#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail2() {
        // Arrange
        Optional<Utilisateur> emptyResult = Optional.empty();
        when(utilisateurRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(AppException.class, () -> utilisateurService.getUserByEmail("jane.doe@example.org"));
        verify(utilisateurRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UtilisateurService#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail3() {
        // Arrange
        when(utilisateurRepository.findByEmail(Mockito.<String>any()))
                .thenThrow(new IllegalStateException("Utilisateur non trouvé"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> utilisateurService.getUserByEmail("jane.doe@example.org"));
        verify(utilisateurRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UtilisateurService#login(CredentialsDTO)}
     */
    @Test
    void testLogin() {
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

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setGroupe(groupe2);
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur);
        when(utilisateurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        LoginUserDTO actualLoginResult = utilisateurService.login(new CredentialsDTO("jane.doe@example.org", "iloveyou"));

        // Assert
        verify(utilisateurRepository).findByEmail(eq("jane.doe@example.org"));
        assertEquals("6625550144", actualLoginResult.getPhone());
        assertEquals("Nom", actualLoginResult.getNom());
        assertEquals("Prenom", actualLoginResult.getPrenom());
        assertEquals("jane.doe@example.org", actualLoginResult.getEmail());
        assertEquals(1L, actualLoginResult.getId().longValue());
    }

    /**
     * Method under test: {@link UtilisateurService#login(CredentialsDTO)}
     */
    @Test
    void testLogin2() {
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
        Admin admin2 = mock(Admin.class);
        when(admin2.getPassword()).thenReturn("foo");
        doNothing().when(admin2).setEmail(Mockito.<String>any());
        doNothing().when(admin2).setGroupe(Mockito.<Groupe>any());
        doNothing().when(admin2).setId(anyLong());
        doNothing().when(admin2).setNom(Mockito.<String>any());
        doNothing().when(admin2).setPassword(Mockito.<String>any());
        doNothing().when(admin2).setPhone(Mockito.<String>any());
        doNothing().when(admin2).setPrenom(Mockito.<String>any());
        doNothing().when(admin2).setRole(Mockito.<Role>any());
        doNothing().when(admin2).setTaches(Mockito.<List<Tache>>any());
        admin2.setEmail("jane.doe@example.org");
        admin2.setGroupe(groupe2);
        admin2.setId(1L);
        admin2.setNom("Nom");
        admin2.setPassword("iloveyou");
        admin2.setPhone("6625550144");
        admin2.setPrenom("Prenom");
        admin2.setRole(Role.ADMIN);
        admin2.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(admin2);
        when(utilisateurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(AppException.class,
                () -> utilisateurService.login(new CredentialsDTO("jane.doe@example.org", "iloveyou")));
        verify(admin2).getPassword();
        verify(admin2).setEmail(eq("jane.doe@example.org"));
        verify(admin2).setGroupe(Mockito.<Groupe>any());
        verify(admin2).setId(eq(1L));
        verify(admin2).setNom(eq("Nom"));
        verify(admin2).setPassword(eq("iloveyou"));
        verify(admin2).setPhone(eq("6625550144"));
        verify(admin2).setPrenom(eq("Prenom"));
        verify(admin2).setRole(eq(Role.ADMIN));
        verify(admin2).setTaches(Mockito.<List<Tache>>any());
        verify(utilisateurRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UtilisateurService#getAllUtilisateurs()}
     */
    @Test
    void testGetAllUtilisateurs() {
        // Arrange
        when(utilisateurRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        Optional<List<Utilisateur>> actualAllUtilisateurs = utilisateurService.getAllUtilisateurs();

        // Assert
        verify(utilisateurRepository).findAll();
        assertTrue(actualAllUtilisateurs.isPresent());
    }

    /**
     * Method under test: {@link UtilisateurService#getAllUtilisateurs()}
     */
    @Test
    void testGetAllUtilisateurs2() {
        // Arrange
        when(utilisateurRepository.findAll()).thenThrow(new IllegalStateException("foo"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> utilisateurService.getAllUtilisateurs());
        verify(utilisateurRepository).findAll();
    }
}
