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
import ca.christopher.applicationtache.modeles.Evenement;
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
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
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
     * Method under test: {@link UtilisateurService#saveUser(RegisterUserDTO)}
     */
    @Test
    void testSaveUser2() {
        // Arrange
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur);
        Optional<Utilisateur> emptyResult = Optional.empty();
        when(utilisateurRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        when(utilisateurRepository.findByPhone(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(AppException.class, () -> utilisateurService
                .saveUser(new RegisterUserDTO("Nom", "Prenom", "6625550144", "jane.doe@example.org", "iloveyou")));
        verify(utilisateurRepository).findByEmail(eq("jane.doe@example.org"));
        verify(utilisateurRepository).findByPhone(eq("6625550144"));
    }

    /**
     * Method under test: {@link UtilisateurService#saveUser(RegisterUserDTO)}
     */
    @Test
    void testSaveUser3() {
        // Arrange, Act and Assert
        assertThrows(IllegalStateException.class, () -> utilisateurService.saveUser(null));
    }

    /**
     * Method under test: {@link UtilisateurService#saveUser(RegisterUserDTO)}
     */
    @Test
    void testSaveUser4() {
        // Arrange
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Nom");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Prenom");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur);
        when(utilisateurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        RegisterUserDTO utilisateur2 = mock(RegisterUserDTO.class);
        when(utilisateur2.getEmail()).thenReturn("jane.doe@example.org");

        // Act and Assert
        assertThrows(AppException.class, () -> utilisateurService.saveUser(utilisateur2));
        verify(utilisateur2).getEmail();
        verify(utilisateurRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UtilisateurService#getUser(Long)}
     */
    @Test
    void testGetUser() {
        // Arrange
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
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
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
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
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
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
        assertTrue(actualLoginResult.getGroupe().isEmpty());
    }

    /**
     * Method under test: {@link UtilisateurService#login(CredentialsDTO)}
     */
    @Test
    void testLogin2() {
        // Arrange
        Admin admin = mock(Admin.class);
        when(admin.getPassword()).thenReturn("foo");
        doNothing().when(admin).setEmail(Mockito.<String>any());
        doNothing().when(admin).setEvenements(Mockito.<List<Evenement>>any());
        doNothing().when(admin).setGroupe(Mockito.<List<Groupe>>any());
        doNothing().when(admin).setId(anyLong());
        doNothing().when(admin).setNom(Mockito.<String>any());
        doNothing().when(admin).setPassword(Mockito.<String>any());
        doNothing().when(admin).setPhone(Mockito.<String>any());
        doNothing().when(admin).setPrenom(Mockito.<String>any());
        doNothing().when(admin).setRole(Mockito.<Role>any());
        doNothing().when(admin).setTaches(Mockito.<List<Tache>>any());
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Nom");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Prenom");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(admin);
        when(utilisateurRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(AppException.class,
                () -> utilisateurService.login(new CredentialsDTO("jane.doe@example.org", "iloveyou")));
        verify(admin).getPassword();
        verify(admin).setEmail(eq("jane.doe@example.org"));
        verify(admin).setEvenements(Mockito.<List<Evenement>>any());
        verify(admin).setGroupe(Mockito.<List<Groupe>>any());
        verify(admin).setId(eq(1L));
        verify(admin).setNom(eq("Nom"));
        verify(admin).setPassword(eq("iloveyou"));
        verify(admin).setPhone(eq("6625550144"));
        verify(admin).setPrenom(eq("Prenom"));
        verify(admin).setRole(eq(Role.ADMIN));
        verify(admin).setTaches(Mockito.<List<Tache>>any());
        verify(utilisateurRepository).findByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UtilisateurService#login(CredentialsDTO)}
     */
    @Test
    void testLogin3() {
        // Arrange
        Optional<Utilisateur> emptyResult = Optional.empty();
        when(utilisateurRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        new RuntimeException("foo");
        new RuntimeException("foo");
        new RuntimeException("foo");
        new RuntimeException("foo");
        new RuntimeException("foo");
        new RuntimeException("foo");

        // Act and Assert
        assertThrows(AppException.class,
                () -> utilisateurService.login(new CredentialsDTO("jane.doe@example.org", "iloveyou")));
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
