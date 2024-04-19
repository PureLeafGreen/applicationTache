package ca.christopher.applicationtache.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.christopher.applicationtache.DTO.EvenementDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.modeles.Evenement;
import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Tache;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.EvenementRepository;
import ca.christopher.applicationtache.repositories.GroupeRepository;
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

@ContextConfiguration(classes = {EvenementService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EvenementServiceTest {
    @MockBean
    private EvenementRepository evenementRepository;

    @Autowired
    private EvenementService evenementService;

    @MockBean
    private GroupeRepository groupeRepository;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link EvenementService#saveEvenement(EvenementDTO)}
     */
    @Test
    void testSaveEvenement() {
        // Arrange
        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

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

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Nom");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);
        when(evenementRepository.save(Mockito.<Evenement>any())).thenReturn(evenement);

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Nom");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Prenom");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur2);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        EvenementDTO actualSaveEvenementResult = evenementService.saveEvenement(new EvenementDTO());

        // Assert
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(evenementRepository).save(Mockito.<Evenement>any());
        assertEquals("2020-03-01", actualSaveEvenementResult.getDateDebut());
        assertEquals("2020-03-01", actualSaveEvenementResult.getDateFin());
        assertEquals("Nom", actualSaveEvenementResult.getNom());
        assertEquals("The characteristics of someone or something", actualSaveEvenementResult.getDescription());
        assertNull(actualSaveEvenementResult.getGroupe());
        assertNull(actualSaveEvenementResult.getTaches());
        assertEquals(1L, actualSaveEvenementResult.getId());
        assertEquals(1L, actualSaveEvenementResult.getUtilisateur().longValue());
    }

    /**
     * Method under test: {@link EvenementService#saveEvenement(EvenementDTO)}
     */
    @Test
    void testSaveEvenement2() {
        // Arrange
        when(evenementRepository.save(Mockito.<Evenement>any())).thenThrow(new AppException());

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

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.saveEvenement(new EvenementDTO()));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(evenementRepository).save(Mockito.<Evenement>any());
    }

    /**
     * Method under test: {@link EvenementService#saveEvenement(EvenementDTO)}
     */
    @Test
    void testSaveEvenement3() {
        // Arrange
        when(evenementRepository.save(Mockito.<Evenement>any()))
                .thenThrow(new IllegalStateException("Impossible de créer un événement"));

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

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> evenementService.saveEvenement(new EvenementDTO()));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(evenementRepository).save(Mockito.<Evenement>any());
    }

    /**
     * Method under test: {@link EvenementService#saveEvenement(EvenementDTO)}
     */
    @Test
    void testSaveEvenement4() {
        // Arrange
        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

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

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Nom");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Prenom");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());
        Evenement evenement = mock(Evenement.class);
        when(evenement.getUtilisateur()).thenReturn(utilisateur2);
        when(evenement.getDateDebut()).thenReturn("2020-03-01");
        when(evenement.getDateFin()).thenReturn("2020-03-01");
        when(evenement.getDescription()).thenReturn("The characteristics of someone or something");
        when(evenement.getNom()).thenReturn("Nom");
        when(evenement.getId()).thenReturn(1L);
        doNothing().when(evenement).setDateDebut(Mockito.<String>any());
        doNothing().when(evenement).setDateFin(Mockito.<String>any());
        doNothing().when(evenement).setDescription(Mockito.<String>any());
        doNothing().when(evenement).setGroupe(Mockito.<Groupe>any());
        doNothing().when(evenement).setId(anyLong());
        doNothing().when(evenement).setNom(Mockito.<String>any());
        doNothing().when(evenement).setTaches(Mockito.<List<Tache>>any());
        doNothing().when(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Nom");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);
        when(evenementRepository.save(Mockito.<Evenement>any())).thenReturn(evenement);

        Utilisateur utilisateur3 = new Utilisateur();
        utilisateur3.setEmail("jane.doe@example.org");
        utilisateur3.setEvenements(new ArrayList<>());
        utilisateur3.setGroupe(new ArrayList<>());
        utilisateur3.setId(1L);
        utilisateur3.setNom("Nom");
        utilisateur3.setPassword("iloveyou");
        utilisateur3.setPhone("6625550144");
        utilisateur3.setPrenom("Prenom");
        utilisateur3.setRole(Role.ADMIN);
        utilisateur3.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur3);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        EvenementDTO actualSaveEvenementResult = evenementService.saveEvenement(new EvenementDTO());

        // Assert
        verify(evenement).getDateDebut();
        verify(evenement).getDateFin();
        verify(evenement).getDescription();
        verify(evenement).getId();
        verify(evenement).getNom();
        verify(evenement).getUtilisateur();
        verify(evenement).setDateDebut(eq("2020-03-01"));
        verify(evenement).setDateFin(eq("2020-03-01"));
        verify(evenement).setDescription(eq("The characteristics of someone or something"));
        verify(evenement).setGroupe(Mockito.<Groupe>any());
        verify(evenement).setId(eq(1L));
        verify(evenement).setNom(eq("Nom"));
        verify(evenement).setTaches(Mockito.<List<Tache>>any());
        verify(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(evenementRepository).save(Mockito.<Evenement>any());
        assertEquals("2020-03-01", actualSaveEvenementResult.getDateDebut());
        assertEquals("2020-03-01", actualSaveEvenementResult.getDateFin());
        assertEquals("Nom", actualSaveEvenementResult.getNom());
        assertEquals("The characteristics of someone or something", actualSaveEvenementResult.getDescription());
        assertNull(actualSaveEvenementResult.getGroupe());
        assertNull(actualSaveEvenementResult.getTaches());
        assertEquals(1L, actualSaveEvenementResult.getId());
        assertEquals(1L, actualSaveEvenementResult.getUtilisateur().longValue());
    }

    /**
     * Method under test: {@link EvenementService#getEvenement(Long)}
     */
    @Test
    void testGetEvenement() {
        // Arrange
        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

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

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Nom");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);
        Optional<Evenement> ofResult = Optional.of(evenement);
        when(evenementRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        EvenementDTO actualEvenement = evenementService.getEvenement(1L);

        // Assert
        verify(evenementRepository).findById(Mockito.<Long>any());
        assertEquals("2020-03-01", actualEvenement.getDateDebut());
        assertEquals("2020-03-01", actualEvenement.getDateFin());
        assertEquals("Nom", actualEvenement.getNom());
        assertEquals("The characteristics of someone or something", actualEvenement.getDescription());
        assertNull(actualEvenement.getGroupe());
        assertNull(actualEvenement.getTaches());
        assertEquals(1L, actualEvenement.getId());
        assertEquals(1L, actualEvenement.getUtilisateur().longValue());
    }

    /**
     * Method under test: {@link EvenementService#getEvenement(Long)}
     */
    @Test
    void testGetEvenement2() {
        // Arrange
        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

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

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Nom");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Prenom");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());
        Evenement evenement = mock(Evenement.class);
        when(evenement.getUtilisateur()).thenReturn(utilisateur2);
        when(evenement.getDateDebut()).thenReturn("2020-03-01");
        when(evenement.getDateFin()).thenReturn("2020-03-01");
        when(evenement.getDescription()).thenReturn("The characteristics of someone or something");
        when(evenement.getNom()).thenReturn("Nom");
        when(evenement.getId()).thenReturn(1L);
        doNothing().when(evenement).setDateDebut(Mockito.<String>any());
        doNothing().when(evenement).setDateFin(Mockito.<String>any());
        doNothing().when(evenement).setDescription(Mockito.<String>any());
        doNothing().when(evenement).setGroupe(Mockito.<Groupe>any());
        doNothing().when(evenement).setId(anyLong());
        doNothing().when(evenement).setNom(Mockito.<String>any());
        doNothing().when(evenement).setTaches(Mockito.<List<Tache>>any());
        doNothing().when(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Nom");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);
        Optional<Evenement> ofResult = Optional.of(evenement);
        when(evenementRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        EvenementDTO actualEvenement = evenementService.getEvenement(1L);

        // Assert
        verify(evenement).getDateDebut();
        verify(evenement).getDateFin();
        verify(evenement).getDescription();
        verify(evenement).getId();
        verify(evenement).getNom();
        verify(evenement).getUtilisateur();
        verify(evenement).setDateDebut(eq("2020-03-01"));
        verify(evenement).setDateFin(eq("2020-03-01"));
        verify(evenement).setDescription(eq("The characteristics of someone or something"));
        verify(evenement).setGroupe(Mockito.<Groupe>any());
        verify(evenement).setId(eq(1L));
        verify(evenement).setNom(eq("Nom"));
        verify(evenement).setTaches(Mockito.<List<Tache>>any());
        verify(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        verify(evenementRepository).findById(Mockito.<Long>any());
        assertEquals("2020-03-01", actualEvenement.getDateDebut());
        assertEquals("2020-03-01", actualEvenement.getDateFin());
        assertEquals("Nom", actualEvenement.getNom());
        assertEquals("The characteristics of someone or something", actualEvenement.getDescription());
        assertNull(actualEvenement.getGroupe());
        assertNull(actualEvenement.getTaches());
        assertEquals(1L, actualEvenement.getId());
        assertEquals(1L, actualEvenement.getUtilisateur().longValue());
    }

    /**
     * Method under test: {@link EvenementService#getEvenementByDate(String)}
     */
    @Test
    void testGetEvenementByDate() {
        // Arrange
        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

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

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Nom");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);
        Optional<Evenement> ofResult = Optional.of(evenement);
        when(evenementRepository.findByDateDebut(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        EvenementDTO actualEvenementByDate = evenementService.getEvenementByDate("2020-03-01");

        // Assert
        verify(evenementRepository).findByDateDebut(eq("2020-03-01"));
        assertEquals("2020-03-01", actualEvenementByDate.getDateDebut());
        assertEquals("2020-03-01", actualEvenementByDate.getDateFin());
        assertEquals("Nom", actualEvenementByDate.getNom());
        assertEquals("The characteristics of someone or something", actualEvenementByDate.getDescription());
        assertNull(actualEvenementByDate.getGroupe());
        assertNull(actualEvenementByDate.getTaches());
        assertEquals(1L, actualEvenementByDate.getId());
        assertEquals(1L, actualEvenementByDate.getUtilisateur().longValue());
    }

    /**
     * Method under test: {@link EvenementService#getEvenementByDate(String)}
     */
    @Test
    void testGetEvenementByDate2() {
        // Arrange
        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

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

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Nom");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Prenom");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());
        Evenement evenement = mock(Evenement.class);
        when(evenement.getUtilisateur()).thenReturn(utilisateur2);
        when(evenement.getDateDebut()).thenReturn("2020-03-01");
        when(evenement.getDateFin()).thenReturn("2020-03-01");
        when(evenement.getDescription()).thenReturn("The characteristics of someone or something");
        when(evenement.getNom()).thenReturn("Nom");
        when(evenement.getId()).thenReturn(1L);
        doNothing().when(evenement).setDateDebut(Mockito.<String>any());
        doNothing().when(evenement).setDateFin(Mockito.<String>any());
        doNothing().when(evenement).setDescription(Mockito.<String>any());
        doNothing().when(evenement).setGroupe(Mockito.<Groupe>any());
        doNothing().when(evenement).setId(anyLong());
        doNothing().when(evenement).setNom(Mockito.<String>any());
        doNothing().when(evenement).setTaches(Mockito.<List<Tache>>any());
        doNothing().when(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Nom");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);
        Optional<Evenement> ofResult = Optional.of(evenement);
        when(evenementRepository.findByDateDebut(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        EvenementDTO actualEvenementByDate = evenementService.getEvenementByDate("2020-03-01");

        // Assert
        verify(evenement).getDateDebut();
        verify(evenement).getDateFin();
        verify(evenement).getDescription();
        verify(evenement).getId();
        verify(evenement).getNom();
        verify(evenement).getUtilisateur();
        verify(evenement).setDateDebut(eq("2020-03-01"));
        verify(evenement).setDateFin(eq("2020-03-01"));
        verify(evenement).setDescription(eq("The characteristics of someone or something"));
        verify(evenement).setGroupe(Mockito.<Groupe>any());
        verify(evenement).setId(eq(1L));
        verify(evenement).setNom(eq("Nom"));
        verify(evenement).setTaches(Mockito.<List<Tache>>any());
        verify(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        verify(evenementRepository).findByDateDebut(eq("2020-03-01"));
        assertEquals("2020-03-01", actualEvenementByDate.getDateDebut());
        assertEquals("2020-03-01", actualEvenementByDate.getDateFin());
        assertEquals("Nom", actualEvenementByDate.getNom());
        assertEquals("The characteristics of someone or something", actualEvenementByDate.getDescription());
        assertNull(actualEvenementByDate.getGroupe());
        assertNull(actualEvenementByDate.getTaches());
        assertEquals(1L, actualEvenementByDate.getId());
        assertEquals(1L, actualEvenementByDate.getUtilisateur().longValue());
    }

    /**
     * Method under test: {@link EvenementService#getEvenementsByDate(String)}
     */
    @Test
    void testGetEvenementsByDate() {
        // Arrange
        when(evenementRepository.findAllByDateDebutSameDay(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getEvenementsByDate("2020-03-01"));
        verify(evenementRepository).findAllByDateDebutSameDay(eq("2020-03-01"));
    }

    /**
     * Method under test: {@link EvenementService#getEvenementsByDate(String)}
     */
    @Test
    void testGetEvenementsByDate2() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement);
        when(evenementRepository.findAllByDateDebutSameDay(Mockito.<String>any())).thenReturn(evenementList);

        // Act
        List<EvenementDTO> actualEvenementsByDate = evenementService.getEvenementsByDate("2020-03-01");

        // Assert
        verify(evenementRepository).findAllByDateDebutSameDay(eq("2020-03-01"));
        assertEquals(1, actualEvenementsByDate.size());
    }

    /**
     * Method under test: {@link EvenementService#getEvenementsByDate(String)}
     */
    @Test
    void testGetEvenementsByDate3() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("john.smith@example.org");
        admin2.setEvenements(new ArrayList<>());
        admin2.setGroupe(new ArrayList<>());
        admin2.setId(2L);
        admin2.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin2.setPassword("Password");
        admin2.setPhone("8605550118");
        admin2.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin2.setRole(Role.USER);
        admin2.setTaches(new ArrayList<>());

        Groupe groupe2 = new Groupe();
        groupe2.setAdmin(admin2);
        groupe2.setCode("ca.christopher.applicationtache.modeles.Groupe");
        groupe2.setDescription("Description");
        groupe2.setId(2L);
        groupe2.setNom("ca.christopher.applicationtache.modeles.Groupe");
        groupe2.setTaches(new ArrayList<>());
        groupe2.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("john.smith@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(2L);
        utilisateur2.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur2.setPassword("Password");
        utilisateur2.setPhone("8605550118");
        utilisateur2.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur2.setRole(Role.USER);
        utilisateur2.setTaches(new ArrayList<>());

        Evenement evenement2 = new Evenement();
        evenement2.setDateDebut("2020/03/01");
        evenement2.setDateFin("2020/03/01");
        evenement2.setDescription("Description");
        evenement2.setGroupe(groupe2);
        evenement2.setId(2L);
        evenement2.setNom("ca.christopher.applicationtache.modeles.Evenement");
        evenement2.setTaches(new ArrayList<>());
        evenement2.setUtilisateur(utilisateur2);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement2);
        evenementList.add(evenement);
        when(evenementRepository.findAllByDateDebutSameDay(Mockito.<String>any())).thenReturn(evenementList);

        // Act
        List<EvenementDTO> actualEvenementsByDate = evenementService.getEvenementsByDate("2020-03-01");

        // Assert
        verify(evenementRepository).findAllByDateDebutSameDay(eq("2020-03-01"));
        assertEquals(2, actualEvenementsByDate.size());
    }

    /**
     * Method under test: {@link EvenementService#getEvenementsByDate(String)}
     */
    @Test
    void testGetEvenementsByDate4() {
        // Arrange
        when(evenementRepository.findAllByDateDebutSameDay(Mockito.<String>any())).thenThrow(new AppException());

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getEvenementsByDate("2020-03-01"));
        verify(evenementRepository).findAllByDateDebutSameDay(eq("2020-03-01"));
    }

    /**
     * Method under test: {@link EvenementService#getEvenementsByDate(String)}
     */
    @Test
    void testGetEvenementsByDate5() {
        // Arrange
        when(evenementRepository.findAllByDateDebutSameDay(Mockito.<String>any()))
                .thenThrow(new IllegalStateException("Aucun événement trouvé"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> evenementService.getEvenementsByDate("2020-03-01"));
        verify(evenementRepository).findAllByDateDebutSameDay(eq("2020-03-01"));
    }

    /**
     * Method under test: {@link EvenementService#getEvenementsByDate(String)}
     */
    @Test
    void testGetEvenementsByDate6() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Nom");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Prenom");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());
        Evenement evenement = mock(Evenement.class);
        when(evenement.getUtilisateur()).thenReturn(utilisateur2);
        when(evenement.getDateDebut()).thenReturn("2020-03-01");
        when(evenement.getDateFin()).thenReturn("2020-03-01");
        when(evenement.getDescription()).thenReturn("The characteristics of someone or something");
        when(evenement.getNom()).thenReturn("Nom");
        when(evenement.getId()).thenReturn(1L);
        doNothing().when(evenement).setDateDebut(Mockito.<String>any());
        doNothing().when(evenement).setDateFin(Mockito.<String>any());
        doNothing().when(evenement).setDescription(Mockito.<String>any());
        doNothing().when(evenement).setGroupe(Mockito.<Groupe>any());
        doNothing().when(evenement).setId(anyLong());
        doNothing().when(evenement).setNom(Mockito.<String>any());
        doNothing().when(evenement).setTaches(Mockito.<List<Tache>>any());
        doNothing().when(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement);
        when(evenementRepository.findAllByDateDebutSameDay(Mockito.<String>any())).thenReturn(evenementList);

        // Act
        List<EvenementDTO> actualEvenementsByDate = evenementService.getEvenementsByDate("2020-03-01");

        // Assert
        verify(evenement).getDateDebut();
        verify(evenement).getDateFin();
        verify(evenement).getDescription();
        verify(evenement).getId();
        verify(evenement).getNom();
        verify(evenement).getUtilisateur();
        verify(evenement).setDateDebut(eq("2020-03-01"));
        verify(evenement).setDateFin(eq("2020-03-01"));
        verify(evenement).setDescription(eq("The characteristics of someone or something"));
        verify(evenement).setGroupe(Mockito.<Groupe>any());
        verify(evenement).setId(eq(1L));
        verify(evenement).setNom(eq("Aucun événement trouvé"));
        verify(evenement).setTaches(Mockito.<List<Tache>>any());
        verify(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        verify(evenementRepository).findAllByDateDebutSameDay(eq("2020-03-01"));
        assertEquals(1, actualEvenementsByDate.size());
    }

    /**
     * Method under test: {@link EvenementService#deleteEvenement(Long)}
     */
    @Test
    void testDeleteEvenement() {
        // Arrange
        doNothing().when(evenementRepository).deleteById(Mockito.<Long>any());
        when(evenementRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act
        evenementService.deleteEvenement(1L);

        // Assert that nothing has changed
        verify(evenementRepository).deleteById(Mockito.<Long>any());
        verify(evenementRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#deleteEvenement(Long)}
     */
    @Test
    void testDeleteEvenement2() {
        // Arrange
        doThrow(new AppException()).when(evenementRepository).deleteById(Mockito.<Long>any());
        when(evenementRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.deleteEvenement(1L));
        verify(evenementRepository).deleteById(Mockito.<Long>any());
        verify(evenementRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#deleteEvenement(Long)}
     */
    @Test
    void testDeleteEvenement3() {
        // Arrange
        doThrow(new IllegalStateException("foo")).when(evenementRepository).deleteById(Mockito.<Long>any());
        when(evenementRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> evenementService.deleteEvenement(1L));
        verify(evenementRepository).deleteById(Mockito.<Long>any());
        verify(evenementRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#deleteEvenement(Long)}
     */
    @Test
    void testDeleteEvenement4() {
        // Arrange
        when(evenementRepository.existsById(Mockito.<Long>any())).thenReturn(false);

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.deleteEvenement(1L));
        verify(evenementRepository).existsById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenements()}
     */
    @Test
    void testGetAllEvenements() {
        // Arrange
        when(evenementRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getAllEvenements());
        verify(evenementRepository).findAll();
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenements()}
     */
    @Test
    void testGetAllEvenements2() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement);
        when(evenementRepository.findAll()).thenReturn(evenementList);

        // Act
        List<EvenementDTO> actualAllEvenements = evenementService.getAllEvenements();

        // Assert
        verify(evenementRepository).findAll();
        assertEquals(1, actualAllEvenements.size());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenements()}
     */
    @Test
    void testGetAllEvenements3() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("john.smith@example.org");
        admin2.setEvenements(new ArrayList<>());
        admin2.setGroupe(new ArrayList<>());
        admin2.setId(2L);
        admin2.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin2.setPassword("Password");
        admin2.setPhone("8605550118");
        admin2.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin2.setRole(Role.USER);
        admin2.setTaches(new ArrayList<>());

        Groupe groupe2 = new Groupe();
        groupe2.setAdmin(admin2);
        groupe2.setCode("ca.christopher.applicationtache.modeles.Groupe");
        groupe2.setDescription("Description");
        groupe2.setId(2L);
        groupe2.setNom("ca.christopher.applicationtache.modeles.Groupe");
        groupe2.setTaches(new ArrayList<>());
        groupe2.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("john.smith@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(2L);
        utilisateur2.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur2.setPassword("Password");
        utilisateur2.setPhone("8605550118");
        utilisateur2.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur2.setRole(Role.USER);
        utilisateur2.setTaches(new ArrayList<>());

        Evenement evenement2 = new Evenement();
        evenement2.setDateDebut("2020/03/01");
        evenement2.setDateFin("2020/03/01");
        evenement2.setDescription("Description");
        evenement2.setGroupe(groupe2);
        evenement2.setId(2L);
        evenement2.setNom("ca.christopher.applicationtache.modeles.Evenement");
        evenement2.setTaches(new ArrayList<>());
        evenement2.setUtilisateur(utilisateur2);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement2);
        evenementList.add(evenement);
        when(evenementRepository.findAll()).thenReturn(evenementList);

        // Act
        List<EvenementDTO> actualAllEvenements = evenementService.getAllEvenements();

        // Assert
        verify(evenementRepository).findAll();
        assertEquals(2, actualAllEvenements.size());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenements()}
     */
    @Test
    void testGetAllEvenements4() {
        // Arrange
        when(evenementRepository.findAll()).thenThrow(new AppException());

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getAllEvenements());
        verify(evenementRepository).findAll();
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenements()}
     */
    @Test
    void testGetAllEvenements5() {
        // Arrange
        when(evenementRepository.findAll()).thenThrow(new IllegalStateException("Aucun événement trouvé"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> evenementService.getAllEvenements());
        verify(evenementRepository).findAll();
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenements()}
     */
    @Test
    void testGetAllEvenements6() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Nom");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Prenom");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());
        Evenement evenement = mock(Evenement.class);
        when(evenement.getUtilisateur()).thenReturn(utilisateur2);
        when(evenement.getDateDebut()).thenReturn("2020-03-01");
        when(evenement.getDateFin()).thenReturn("2020-03-01");
        when(evenement.getDescription()).thenReturn("The characteristics of someone or something");
        when(evenement.getNom()).thenReturn("Nom");
        when(evenement.getId()).thenReturn(1L);
        doNothing().when(evenement).setDateDebut(Mockito.<String>any());
        doNothing().when(evenement).setDateFin(Mockito.<String>any());
        doNothing().when(evenement).setDescription(Mockito.<String>any());
        doNothing().when(evenement).setGroupe(Mockito.<Groupe>any());
        doNothing().when(evenement).setId(anyLong());
        doNothing().when(evenement).setNom(Mockito.<String>any());
        doNothing().when(evenement).setTaches(Mockito.<List<Tache>>any());
        doNothing().when(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement);
        when(evenementRepository.findAll()).thenReturn(evenementList);

        // Act
        List<EvenementDTO> actualAllEvenements = evenementService.getAllEvenements();

        // Assert
        verify(evenement).getDateDebut();
        verify(evenement).getDateFin();
        verify(evenement).getDescription();
        verify(evenement).getId();
        verify(evenement).getNom();
        verify(evenement).getUtilisateur();
        verify(evenement).setDateDebut(eq("2020-03-01"));
        verify(evenement).setDateFin(eq("2020-03-01"));
        verify(evenement).setDescription(eq("The characteristics of someone or something"));
        verify(evenement).setGroupe(Mockito.<Groupe>any());
        verify(evenement).setId(eq(1L));
        verify(evenement).setNom(eq("Aucun événement trouvé"));
        verify(evenement).setTaches(Mockito.<List<Tache>>any());
        verify(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        verify(evenementRepository).findAll();
        assertEquals(1, actualAllEvenements.size());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByUser(Long)}
     */
    @Test
    void testGetAllEvenementsByUser() {
        // Arrange
        when(evenementRepository.findAllByUtilisateur(Mockito.<Utilisateur>any())).thenReturn(new ArrayList<>());

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

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getAllEvenementsByUser(1L));
        verify(evenementRepository).findAllByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByUser(Long)}
     */
    @Test
    void testGetAllEvenementsByUser2() {
        // Arrange
        when(evenementRepository.findAllByUtilisateur(Mockito.<Utilisateur>any())).thenThrow(new AppException());

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

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getAllEvenementsByUser(1L));
        verify(evenementRepository).findAllByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByUser(Long)}
     */
    @Test
    void testGetAllEvenementsByUser3() {
        // Arrange
        when(evenementRepository.findAllByUtilisateur(Mockito.<Utilisateur>any()))
                .thenThrow(new IllegalStateException("Aucun événement trouvé"));

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

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> evenementService.getAllEvenementsByUser(1L));
        verify(evenementRepository).findAllByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByUser(Long)}
     */
    @Test
    void testGetAllEvenementsByUser4() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement);
        when(evenementRepository.findAllByUtilisateur(Mockito.<Utilisateur>any())).thenReturn(evenementList);

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Nom");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Prenom");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur2);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<EvenementDTO> actualAllEvenementsByUser = evenementService.getAllEvenementsByUser(1L);

        // Assert
        verify(evenementRepository).findAllByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualAllEvenementsByUser.size());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByUser(Long)}
     */
    @Test
    void testGetAllEvenementsByUser5() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("john.smith@example.org");
        admin2.setEvenements(new ArrayList<>());
        admin2.setGroupe(new ArrayList<>());
        admin2.setId(2L);
        admin2.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin2.setPassword("Password");
        admin2.setPhone("8605550118");
        admin2.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin2.setRole(Role.USER);
        admin2.setTaches(new ArrayList<>());

        Groupe groupe2 = new Groupe();
        groupe2.setAdmin(admin2);
        groupe2.setCode("ca.christopher.applicationtache.modeles.Groupe");
        groupe2.setDescription("Description");
        groupe2.setId(2L);
        groupe2.setNom("ca.christopher.applicationtache.modeles.Groupe");
        groupe2.setTaches(new ArrayList<>());
        groupe2.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("john.smith@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(2L);
        utilisateur2.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur2.setPassword("Password");
        utilisateur2.setPhone("8605550118");
        utilisateur2.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur2.setRole(Role.USER);
        utilisateur2.setTaches(new ArrayList<>());

        Evenement evenement2 = new Evenement();
        evenement2.setDateDebut("2020/03/01");
        evenement2.setDateFin("2020/03/01");
        evenement2.setDescription("Description");
        evenement2.setGroupe(groupe2);
        evenement2.setId(2L);
        evenement2.setNom("ca.christopher.applicationtache.modeles.Evenement");
        evenement2.setTaches(new ArrayList<>());
        evenement2.setUtilisateur(utilisateur2);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement2);
        evenementList.add(evenement);
        when(evenementRepository.findAllByUtilisateur(Mockito.<Utilisateur>any())).thenReturn(evenementList);

        Utilisateur utilisateur3 = new Utilisateur();
        utilisateur3.setEmail("jane.doe@example.org");
        utilisateur3.setEvenements(new ArrayList<>());
        utilisateur3.setGroupe(new ArrayList<>());
        utilisateur3.setId(1L);
        utilisateur3.setNom("Nom");
        utilisateur3.setPassword("iloveyou");
        utilisateur3.setPhone("6625550144");
        utilisateur3.setPrenom("Prenom");
        utilisateur3.setRole(Role.ADMIN);
        utilisateur3.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur3);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<EvenementDTO> actualAllEvenementsByUser = evenementService.getAllEvenementsByUser(1L);

        // Assert
        verify(evenementRepository).findAllByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        assertEquals(2, actualAllEvenementsByUser.size());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByUser(Long)}
     */
    @Test
    void testGetAllEvenementsByUser6() {
        // Arrange
        Optional<Utilisateur> emptyResult = Optional.empty();
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getAllEvenementsByUser(1L));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link EvenementService#saveEvenementWithGroup(EvenementDTO, Long)}
     */
    @Test
    void testSaveEvenementWithGroup() {
        // Arrange
        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

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

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Nom");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);
        when(evenementRepository.save(Mockito.<Evenement>any())).thenReturn(evenement);

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Nom");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Prenom");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur2);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("jane.doe@example.org");
        admin2.setEvenements(new ArrayList<>());
        admin2.setGroupe(new ArrayList<>());
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
        Optional<Groupe> ofResult2 = Optional.of(groupe2);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        EvenementDTO actualSaveEvenementWithGroupResult = evenementService.saveEvenementWithGroup(new EvenementDTO(), 1L);

        // Assert
        verify(groupeRepository).findById(Mockito.<Long>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(evenementRepository).save(Mockito.<Evenement>any());
        assertEquals("2020-03-01", actualSaveEvenementWithGroupResult.getDateDebut());
        assertEquals("2020-03-01", actualSaveEvenementWithGroupResult.getDateFin());
        assertEquals("Nom", actualSaveEvenementWithGroupResult.getNom());
        assertEquals("The characteristics of someone or something", actualSaveEvenementWithGroupResult.getDescription());
        assertNull(actualSaveEvenementWithGroupResult.getGroupe());
        assertNull(actualSaveEvenementWithGroupResult.getTaches());
        assertEquals(1L, actualSaveEvenementWithGroupResult.getId());
        assertEquals(1L, actualSaveEvenementWithGroupResult.getUtilisateur().longValue());
    }

    /**
     * Method under test:
     * {@link EvenementService#saveEvenementWithGroup(EvenementDTO, Long)}
     */
    @Test
    void testSaveEvenementWithGroup2() {
        // Arrange
        when(evenementRepository.save(Mockito.<Evenement>any())).thenThrow(new AppException());

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

        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());
        Optional<Groupe> ofResult2 = Optional.of(groupe);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.saveEvenementWithGroup(new EvenementDTO(), 1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(evenementRepository).save(Mockito.<Evenement>any());
    }

    /**
     * Method under test:
     * {@link EvenementService#saveEvenementWithGroup(EvenementDTO, Long)}
     */
    @Test
    void testSaveEvenementWithGroup3() {
        // Arrange
        when(evenementRepository.save(Mockito.<Evenement>any()))
                .thenThrow(new IllegalStateException("Impossible de créer un événement"));

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

        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());
        Optional<Groupe> ofResult2 = Optional.of(groupe);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> evenementService.saveEvenementWithGroup(new EvenementDTO(), 1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(evenementRepository).save(Mockito.<Evenement>any());
    }

    /**
     * Method under test:
     * {@link EvenementService#saveEvenementWithGroup(EvenementDTO, Long)}
     */
    @Test
    void testSaveEvenementWithGroup4() {
        // Arrange
        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

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

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Nom");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Prenom");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());
        Evenement evenement = mock(Evenement.class);
        when(evenement.getUtilisateur()).thenReturn(utilisateur2);
        when(evenement.getDateDebut()).thenReturn("2020-03-01");
        when(evenement.getDateFin()).thenReturn("2020-03-01");
        when(evenement.getDescription()).thenReturn("The characteristics of someone or something");
        when(evenement.getNom()).thenReturn("Nom");
        when(evenement.getId()).thenReturn(1L);
        doNothing().when(evenement).setDateDebut(Mockito.<String>any());
        doNothing().when(evenement).setDateFin(Mockito.<String>any());
        doNothing().when(evenement).setDescription(Mockito.<String>any());
        doNothing().when(evenement).setGroupe(Mockito.<Groupe>any());
        doNothing().when(evenement).setId(anyLong());
        doNothing().when(evenement).setNom(Mockito.<String>any());
        doNothing().when(evenement).setTaches(Mockito.<List<Tache>>any());
        doNothing().when(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Nom");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);
        when(evenementRepository.save(Mockito.<Evenement>any())).thenReturn(evenement);

        Utilisateur utilisateur3 = new Utilisateur();
        utilisateur3.setEmail("jane.doe@example.org");
        utilisateur3.setEvenements(new ArrayList<>());
        utilisateur3.setGroupe(new ArrayList<>());
        utilisateur3.setId(1L);
        utilisateur3.setNom("Nom");
        utilisateur3.setPassword("iloveyou");
        utilisateur3.setPhone("6625550144");
        utilisateur3.setPrenom("Prenom");
        utilisateur3.setRole(Role.ADMIN);
        utilisateur3.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur3);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("jane.doe@example.org");
        admin2.setEvenements(new ArrayList<>());
        admin2.setGroupe(new ArrayList<>());
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
        Optional<Groupe> ofResult2 = Optional.of(groupe2);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        EvenementDTO actualSaveEvenementWithGroupResult = evenementService.saveEvenementWithGroup(new EvenementDTO(), 1L);

        // Assert
        verify(evenement).getDateDebut();
        verify(evenement).getDateFin();
        verify(evenement).getDescription();
        verify(evenement).getId();
        verify(evenement).getNom();
        verify(evenement).getUtilisateur();
        verify(evenement).setDateDebut(eq("2020-03-01"));
        verify(evenement).setDateFin(eq("2020-03-01"));
        verify(evenement).setDescription(eq("The characteristics of someone or something"));
        verify(evenement).setGroupe(Mockito.<Groupe>any());
        verify(evenement).setId(eq(1L));
        verify(evenement).setNom(eq("Nom"));
        verify(evenement).setTaches(Mockito.<List<Tache>>any());
        verify(evenement).setUtilisateur(Mockito.<Utilisateur>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(evenementRepository).save(Mockito.<Evenement>any());
        assertEquals("2020-03-01", actualSaveEvenementWithGroupResult.getDateDebut());
        assertEquals("2020-03-01", actualSaveEvenementWithGroupResult.getDateFin());
        assertEquals("Nom", actualSaveEvenementWithGroupResult.getNom());
        assertEquals("The characteristics of someone or something", actualSaveEvenementWithGroupResult.getDescription());
        assertNull(actualSaveEvenementWithGroupResult.getGroupe());
        assertNull(actualSaveEvenementWithGroupResult.getTaches());
        assertEquals(1L, actualSaveEvenementWithGroupResult.getId());
        assertEquals(1L, actualSaveEvenementWithGroupResult.getUtilisateur().longValue());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByGroup(Long)}
     */
    @Test
    void testGetAllEvenementsByGroup() {
        // Arrange
        when(evenementRepository.findAllByGroupe(Mockito.<Groupe>any())).thenReturn(new ArrayList<>());

        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());
        Optional<Groupe> ofResult = Optional.of(groupe);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getAllEvenementsByGroup(1L));
        verify(evenementRepository).findAllByGroupe(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByGroup(Long)}
     */
    @Test
    void testGetAllEvenementsByGroup2() {
        // Arrange
        when(evenementRepository.findAllByGroupe(Mockito.<Groupe>any())).thenThrow(new AppException());

        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());
        Optional<Groupe> ofResult = Optional.of(groupe);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getAllEvenementsByGroup(1L));
        verify(evenementRepository).findAllByGroupe(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByGroup(Long)}
     */
    @Test
    void testGetAllEvenementsByGroup3() {
        // Arrange
        when(evenementRepository.findAllByGroupe(Mockito.<Groupe>any()))
                .thenThrow(new IllegalStateException("Aucun événement trouvé"));

        Utilisateur admin = new Utilisateur();
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

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());
        Optional<Groupe> ofResult = Optional.of(groupe);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> evenementService.getAllEvenementsByGroup(1L));
        verify(evenementRepository).findAllByGroupe(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByGroup(Long)}
     */
    @Test
    void testGetAllEvenementsByGroup4() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement);
        when(evenementRepository.findAllByGroupe(Mockito.<Groupe>any())).thenReturn(evenementList);

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("jane.doe@example.org");
        admin2.setEvenements(new ArrayList<>());
        admin2.setGroupe(new ArrayList<>());
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
        Optional<Groupe> ofResult = Optional.of(groupe2);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<EvenementDTO> actualAllEvenementsByGroup = evenementService.getAllEvenementsByGroup(1L);

        // Assert
        verify(evenementRepository).findAllByGroupe(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualAllEvenementsByGroup.size());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByGroup(Long)}
     */
    @Test
    void testGetAllEvenementsByGroup5() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun événement trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun événement trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun événement trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun événement trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucun événement trouvé");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucun événement trouvé");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucun événement trouvé");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("john.smith@example.org");
        admin2.setEvenements(new ArrayList<>());
        admin2.setGroupe(new ArrayList<>());
        admin2.setId(2L);
        admin2.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin2.setPassword("Password");
        admin2.setPhone("8605550118");
        admin2.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin2.setRole(Role.USER);
        admin2.setTaches(new ArrayList<>());

        Groupe groupe2 = new Groupe();
        groupe2.setAdmin(admin2);
        groupe2.setCode("ca.christopher.applicationtache.modeles.Groupe");
        groupe2.setDescription("Description");
        groupe2.setId(2L);
        groupe2.setNom("ca.christopher.applicationtache.modeles.Groupe");
        groupe2.setTaches(new ArrayList<>());
        groupe2.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("john.smith@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(2L);
        utilisateur2.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur2.setPassword("Password");
        utilisateur2.setPhone("8605550118");
        utilisateur2.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur2.setRole(Role.USER);
        utilisateur2.setTaches(new ArrayList<>());

        Evenement evenement2 = new Evenement();
        evenement2.setDateDebut("2020/03/01");
        evenement2.setDateFin("2020/03/01");
        evenement2.setDescription("Description");
        evenement2.setGroupe(groupe2);
        evenement2.setId(2L);
        evenement2.setNom("ca.christopher.applicationtache.modeles.Evenement");
        evenement2.setTaches(new ArrayList<>());
        evenement2.setUtilisateur(utilisateur2);

        ArrayList<Evenement> evenementList = new ArrayList<>();
        evenementList.add(evenement2);
        evenementList.add(evenement);
        when(evenementRepository.findAllByGroupe(Mockito.<Groupe>any())).thenReturn(evenementList);

        Utilisateur admin3 = new Utilisateur();
        admin3.setEmail("jane.doe@example.org");
        admin3.setEvenements(new ArrayList<>());
        admin3.setGroupe(new ArrayList<>());
        admin3.setId(1L);
        admin3.setNom("Nom");
        admin3.setPassword("iloveyou");
        admin3.setPhone("6625550144");
        admin3.setPrenom("Prenom");
        admin3.setRole(Role.ADMIN);
        admin3.setTaches(new ArrayList<>());

        Groupe groupe3 = new Groupe();
        groupe3.setAdmin(admin3);
        groupe3.setCode("Code");
        groupe3.setDescription("The characteristics of someone or something");
        groupe3.setId(1L);
        groupe3.setNom("Nom");
        groupe3.setTaches(new ArrayList<>());
        groupe3.setUtilisateurs(new ArrayList<>());
        Optional<Groupe> ofResult = Optional.of(groupe3);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<EvenementDTO> actualAllEvenementsByGroup = evenementService.getAllEvenementsByGroup(1L);

        // Assert
        verify(evenementRepository).findAllByGroupe(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
        assertEquals(2, actualAllEvenementsByGroup.size());
    }

    /**
     * Method under test: {@link EvenementService#getAllEvenementsByGroup(Long)}
     */
    @Test
    void testGetAllEvenementsByGroup6() {
        // Arrange
        Optional<Groupe> emptyResult = Optional.empty();
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(AppException.class, () -> evenementService.getAllEvenementsByGroup(1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
    }
}
