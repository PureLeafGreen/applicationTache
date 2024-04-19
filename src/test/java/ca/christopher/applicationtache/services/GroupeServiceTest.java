package ca.christopher.applicationtache.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.christopher.applicationtache.DTO.GroupeDTO;
import ca.christopher.applicationtache.DTO.GroupeWithUserAndAdminDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Tache;
import ca.christopher.applicationtache.modeles.Utilisateur;
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

@ContextConfiguration(classes = {GroupeService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GroupeServiceTest {
    @MockBean
    private GroupeRepository groupeRepository;

    @Autowired
    private GroupeService groupeService;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link GroupeService#saveGroupe(GroupeDTO)}
     */
    @Test
    void testSaveGroupe() {
        // Arrange
        GroupeDTO groupe = new GroupeDTO();
        groupe.setUtilisateurs(new ArrayList<>());

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.saveGroupe(groupe));
    }

    /**
     * Method under test: {@link GroupeService#saveGroupe(GroupeDTO)}
     */
    @Test
    void testSaveGroupe2() {
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
        GroupeDTO groupe2 = mock(GroupeDTO.class);
        when(groupe2.fromDto()).thenReturn(groupe);
        when(groupe2.getUtilisateurs()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.saveGroupe(groupe2));
        verify(groupe2).fromDto();
        verify(groupe2).getUtilisateurs();
    }

    /**
     * Method under test: {@link GroupeService#getGroupe(Long)}
     */
    @Test
    void testGetGroupe() {
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
        Optional<Groupe> ofResult = Optional.of(groupe);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        GroupeDTO actualGroupe = groupeService.getGroupe(1L);

        // Assert
        verify(groupeRepository, atLeast(1)).findById(Mockito.<Long>any());
        assertEquals("Code", actualGroupe.getCode());
        assertEquals("Nom", actualGroupe.getNom());
        assertEquals("The characteristics of someone or something", actualGroupe.getDescription());
        assertNull(actualGroupe.getAdmin());
        assertNull(actualGroupe.getTaches());
        assertEquals(1L, actualGroupe.getId());
        assertTrue(actualGroupe.getUtilisateurs().isEmpty());
    }

    /**
     * Method under test: {@link GroupeService#getGroupe(Long)}
     */
    @Test
    void testGetGroupe2() {
        // Arrange
        Optional<Groupe> emptyResult = Optional.empty();
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupe(1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupe(Long)}
     */
    @Test
    void testGetGroupe3() {
        // Arrange
        when(groupeRepository.findById(Mockito.<Long>any())).thenThrow(new AppException());

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupe(1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupe(Long)}
     */
    @Test
    void testGetGroupe4() {
        // Arrange
        when(groupeRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalStateException("Groupe non trouvé"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> groupeService.getGroupe(1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link GroupeService#joinGroupe(String, Long)}
     */
    @Test
    void testJoinGroupe() {
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
        Optional<Groupe> ofResult = Optional.of(groupe);

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
        when(groupeRepository.save(Mockito.<Groupe>any())).thenReturn(groupe2);
        when(groupeRepository.findByCode(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Utilisateur> ofResult2 = Optional.of(utilisateur);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        GroupeDTO actualJoinGroupeResult = groupeService.joinGroupe("Code", 1L);

        // Assert
        verify(groupeRepository).findByCode(eq("Code"));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(groupeRepository).save(Mockito.<Groupe>any());
        assertEquals("Code", actualJoinGroupeResult.getCode());
        assertEquals("Nom", actualJoinGroupeResult.getNom());
        assertEquals("The characteristics of someone or something", actualJoinGroupeResult.getDescription());
        assertNull(actualJoinGroupeResult.getAdmin());
        assertNull(actualJoinGroupeResult.getTaches());
        assertEquals(1L, actualJoinGroupeResult.getId());
        assertTrue(actualJoinGroupeResult.getUtilisateurs().isEmpty());
    }

    /**
     * Method under test: {@link GroupeService#joinGroupe(String, Long)}
     */
    @Test
    void testJoinGroupe2() {
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
        Optional<Groupe> ofResult = Optional.of(groupe);
        when(groupeRepository.save(Mockito.<Groupe>any())).thenThrow(new AppException());
        when(groupeRepository.findByCode(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Utilisateur> ofResult2 = Optional.of(utilisateur);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.joinGroupe("Code", 1L));
        verify(groupeRepository).findByCode(eq("Code"));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(groupeRepository).save(Mockito.<Groupe>any());
    }

    /**
     * Method under test: {@link GroupeService#joinGroupe(String, Long)}
     */
    @Test
    void testJoinGroupe3() {
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
        Optional<Groupe> ofResult = Optional.of(groupe);
        when(groupeRepository.save(Mockito.<Groupe>any())).thenThrow(new IllegalStateException("foo"));
        when(groupeRepository.findByCode(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Utilisateur> ofResult2 = Optional.of(utilisateur);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> groupeService.joinGroupe("Code", 1L));
        verify(groupeRepository).findByCode(eq("Code"));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(groupeRepository).save(Mockito.<Groupe>any());
    }

    /**
     * Method under test: {@link GroupeService#joinGroupe(String, Long)}
     */
    @Test
    void testJoinGroupe4() {
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

        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(utilisateur);

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(utilisateurs);
        Optional<Groupe> ofResult = Optional.of(groupe);
        when(groupeRepository.findByCode(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Utilisateur> ofResult2 = Optional.of(utilisateur2);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.joinGroupe("Code", 1L));
        verify(groupeRepository).findByCode(eq("Code"));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link GroupeService#joinGroupe(String, Long)}
     */
    @Test
    void testJoinGroupe5() {
        // Arrange
        Optional<Groupe> emptyResult = Optional.empty();
        when(groupeRepository.findByCode(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.joinGroupe("Code", 1L));
        verify(groupeRepository).findByCode(eq("Code"));
    }

    /**
     * Method under test: {@link GroupeService#getGroupes(List)}
     */
    @Test
    void testGetGroupes() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupes(new ArrayList<>()));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupes(List)}
     */
    @Test
    void testGetGroupes2() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun groupe trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun groupe trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun groupe trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun groupe trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        ArrayList<Groupe> groupeList = new ArrayList<>();
        groupeList.add(groupe);
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(groupeList);

        // Act
        List<GroupeDTO> actualGroupes = groupeService.getGroupes(new ArrayList<>());

        // Assert
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
        assertEquals(1, actualGroupes.size());
    }

    /**
     * Method under test: {@link GroupeService#getGroupes(List)}
     */
    @Test
    void testGetGroupes3() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun groupe trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun groupe trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun groupe trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun groupe trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

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

        ArrayList<Groupe> groupeList = new ArrayList<>();
        groupeList.add(groupe2);
        groupeList.add(groupe);
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(groupeList);

        // Act
        List<GroupeDTO> actualGroupes = groupeService.getGroupes(new ArrayList<>());

        // Assert
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
        assertEquals(2, actualGroupes.size());
    }

    /**
     * Method under test: {@link GroupeService#getGroupes(List)}
     */
    @Test
    void testGetGroupes4() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(new ArrayList<>());

        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1L);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupes(ids));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupes(List)}
     */
    @Test
    void testGetGroupes5() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(new ArrayList<>());

        ArrayList<Long> ids = new ArrayList<>();
        ids.add(404L);
        ids.add(1L);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupes(ids));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupes(List)}
     */
    @Test
    void testGetGroupes6() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenThrow(new AppException());

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupes(new ArrayList<>()));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupes(List)}
     */
    @Test
    void testGetGroupes7() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any()))
                .thenThrow(new IllegalStateException("Aucun groupe trouvé"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> groupeService.getGroupes(new ArrayList<>()));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupesWithUser(List)}
     */
    @Test
    void testGetGroupesWithUser() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupesWithUser(new ArrayList<>()));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupesWithUser(List)}
     */
    @Test
    void testGetGroupesWithUser2() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucun groupe trouvé");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucun groupe trouvé");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucun groupe trouvé");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucun groupe trouvé");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        ArrayList<Groupe> groupeList = new ArrayList<>();
        groupeList.add(groupe);
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(groupeList);

        // Act
        List<GroupeWithUserAndAdminDTO> actualGroupesWithUser = groupeService.getGroupesWithUser(new ArrayList<>());

        // Assert
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
        assertEquals(1, actualGroupesWithUser.size());
    }

    /**
     * Method under test: {@link GroupeService#getGroupesWithUser(List)}
     */
    @Test
    void testGetGroupesWithUser3() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(new ArrayList<>());

        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1L);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupesWithUser(ids));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupesWithUser(List)}
     */
    @Test
    void testGetGroupesWithUser4() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(new ArrayList<>());

        ArrayList<Long> ids = new ArrayList<>();
        ids.add(404L);
        ids.add(1L);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupesWithUser(ids));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupesWithUser(List)}
     */
    @Test
    void testGetGroupesWithUser5() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any())).thenThrow(new AppException());

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.getGroupesWithUser(new ArrayList<>()));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#getGroupesWithUser(List)}
     */
    @Test
    void testGetGroupesWithUser6() {
        // Arrange
        when(groupeRepository.findAllById(Mockito.<Iterable<Long>>any()))
                .thenThrow(new IllegalStateException("Aucun groupe trouvé"));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> groupeService.getGroupesWithUser(new ArrayList<>()));
        verify(groupeRepository).findAllById(Mockito.<Iterable<Long>>any());
    }

    /**
     * Method under test: {@link GroupeService#deleteGroupe(Long)}
     */
    @Test
    void testDeleteGroupe() {
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
        Optional<Groupe> ofResult = Optional.of(groupe);
        doNothing().when(groupeRepository).delete(Mockito.<Groupe>any());
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        GroupeDTO actualDeleteGroupeResult = groupeService.deleteGroupe(1L);

        // Assert
        verify(groupeRepository).delete(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
        assertEquals("Code", actualDeleteGroupeResult.getCode());
        assertEquals("Nom", actualDeleteGroupeResult.getNom());
        assertEquals("The characteristics of someone or something", actualDeleteGroupeResult.getDescription());
        assertNull(actualDeleteGroupeResult.getAdmin());
        assertNull(actualDeleteGroupeResult.getTaches());
        assertNull(actualDeleteGroupeResult.getUtilisateurs());
        assertEquals(1L, actualDeleteGroupeResult.getId());
    }

    /**
     * Method under test: {@link GroupeService#deleteGroupe(Long)}
     */
    @Test
    void testDeleteGroupe2() {
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
        Optional<Groupe> ofResult = Optional.of(groupe);
        doThrow(new AppException()).when(groupeRepository).delete(Mockito.<Groupe>any());
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.deleteGroupe(1L));
        verify(groupeRepository).delete(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link GroupeService#deleteGroupe(Long)}
     */
    @Test
    void testDeleteGroupe3() {
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
        Optional<Groupe> ofResult = Optional.of(groupe);
        doThrow(new IllegalStateException("foo")).when(groupeRepository).delete(Mockito.<Groupe>any());
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> groupeService.deleteGroupe(1L));
        verify(groupeRepository).delete(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link GroupeService#deleteGroupe(Long)}
     */
    @Test
    void testDeleteGroupe4() {
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
        Groupe groupe = mock(Groupe.class);
        when(groupe.getCode()).thenReturn("Code");
        when(groupe.getDescription()).thenReturn("The characteristics of someone or something");
        when(groupe.getNom()).thenReturn("Nom");
        when(groupe.getUtilisateurs()).thenReturn(new ArrayList<>());
        when(groupe.getId()).thenReturn(1L);
        doNothing().when(groupe).setAdmin(Mockito.<Utilisateur>any());
        doNothing().when(groupe).setCode(Mockito.<String>any());
        doNothing().when(groupe).setDescription(Mockito.<String>any());
        doNothing().when(groupe).setId(anyLong());
        doNothing().when(groupe).setNom(Mockito.<String>any());
        doNothing().when(groupe).setTaches(Mockito.<List<Tache>>any());
        doNothing().when(groupe).setUtilisateurs(Mockito.<List<Utilisateur>>any());
        groupe.setAdmin(admin);
        groupe.setCode("Code");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Nom");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());
        Optional<Groupe> ofResult = Optional.of(groupe);
        doNothing().when(groupeRepository).delete(Mockito.<Groupe>any());
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        GroupeDTO actualDeleteGroupeResult = groupeService.deleteGroupe(1L);

        // Assert
        verify(groupe).getCode();
        verify(groupe).getDescription();
        verify(groupe).getId();
        verify(groupe).getNom();
        verify(groupe, atLeast(1)).getUtilisateurs();
        verify(groupe).setAdmin(Mockito.<Utilisateur>any());
        verify(groupe).setCode(eq("Code"));
        verify(groupe).setDescription(eq("The characteristics of someone or something"));
        verify(groupe).setId(eq(1L));
        verify(groupe).setNom(eq("Nom"));
        verify(groupe).setTaches(Mockito.<List<Tache>>any());
        verify(groupe, atLeast(1)).setUtilisateurs(Mockito.<List<Utilisateur>>any());
        verify(groupeRepository).delete(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
        assertEquals("Code", actualDeleteGroupeResult.getCode());
        assertEquals("Nom", actualDeleteGroupeResult.getNom());
        assertEquals("The characteristics of someone or something", actualDeleteGroupeResult.getDescription());
        assertNull(actualDeleteGroupeResult.getAdmin());
        assertNull(actualDeleteGroupeResult.getTaches());
        assertEquals(1L, actualDeleteGroupeResult.getId());
        assertTrue(actualDeleteGroupeResult.getUtilisateurs().isEmpty());
    }

    /**
     * Method under test: {@link GroupeService#deleteGroupe(Long)}
     */
    @Test
    void testDeleteGroupe5() {
        // Arrange
        Optional<Groupe> emptyResult = Optional.empty();
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(AppException.class, () -> groupeService.deleteGroupe(1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
    }
}
