package ca.christopher.applicationtache.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.christopher.applicationtache.DTO.TaskDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.modeles.Evenement;
import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Tache;
import ca.christopher.applicationtache.modeles.TaskStatus;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.GroupeRepository;
import ca.christopher.applicationtache.repositories.TaskRepository;
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

@ContextConfiguration(classes = {TaskService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class TaskServiceTest {
    @MockBean
    private GroupeRepository groupeRepository;

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @MockBean
    private UtilisateurRepository utilisateurRepository;

    /**
     * Method under test: {@link TaskService#saveTask(TaskDTO)}
     */
    @Test
    void testSaveTask() {
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

        Tache tache = new Tache();
        tache.setCouleur("Couleur");
        tache.setDateDebut("2020-03-01");
        tache.setDateFin("2020-03-01");
        tache.setDescription("The characteristics of someone or something");
        tache.setEvenement(evenement);
        tache.setGroupe(groupe2);
        tache.setId(1L);
        tache.setNom("Nom");
        tache.setType(TaskStatus.TODO);
        tache.setUtilisateur(utilisateur2);
        when(taskRepository.save(Mockito.<Tache>any())).thenReturn(tache);

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
        TaskDTO actualSaveTaskResult = taskService.saveTask(new TaskDTO());

        // Assert
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(taskRepository).save(Mockito.<Tache>any());
        assertEquals("2020-03-01", actualSaveTaskResult.getDateDebut());
        assertEquals("2020-03-01", actualSaveTaskResult.getDateFin());
        assertEquals("Couleur", actualSaveTaskResult.getCouleur());
        assertEquals("Nom", actualSaveTaskResult.getNom());
        assertEquals("The characteristics of someone or something", actualSaveTaskResult.getDescription());
        assertEquals(1L, actualSaveTaskResult.getId());
        assertEquals(1L, actualSaveTaskResult.getEvenement().longValue());
        assertEquals(1L, actualSaveTaskResult.getGroupe().longValue());
        assertEquals(1L, actualSaveTaskResult.getUtilisateur().longValue());
        assertEquals(TaskStatus.TODO, actualSaveTaskResult.getType());
    }

    /**
     * Method under test: {@link TaskService#saveTask(TaskDTO)}
     */
    @Test
    void testSaveTask2() {
        // Arrange
        when(taskRepository.save(Mockito.<Tache>any())).thenThrow(new AppException());

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
        assertThrows(AppException.class, () -> taskService.saveTask(new TaskDTO()));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(taskRepository).save(Mockito.<Tache>any());
    }

    /**
     * Method under test: {@link TaskService#saveTask(TaskDTO)}
     */
    @Test
    void testSaveTask3() {
        // Arrange
        when(taskRepository.save(Mockito.<Tache>any())).thenThrow(new IllegalStateException("foo"));

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
        assertThrows(IllegalStateException.class, () -> taskService.saveTask(new TaskDTO()));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(taskRepository).save(Mockito.<Tache>any());
    }

    /**
     * Method under test: {@link TaskService#getAllByUser(Long)}
     */
    @Test
    void testGetAllByUser() {
        // Arrange
        when(taskRepository.findByUtilisateur(Mockito.<Utilisateur>any())).thenReturn(new ArrayList<>());

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
        assertThrows(AppException.class, () -> taskService.getAllByUser(1L));
        verify(taskRepository).findByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TaskService#getAllByUser(Long)}
     */
    @Test
    void testGetAllByUser2() {
        // Arrange
        when(taskRepository.findByUtilisateur(Mockito.<Utilisateur>any())).thenThrow(new AppException());

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
        assertThrows(AppException.class, () -> taskService.getAllByUser(1L));
        verify(taskRepository).findByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TaskService#getAllByUser(Long)}
     */
    @Test
    void testGetAllByUser3() {
        // Arrange
        when(taskRepository.findByUtilisateur(Mockito.<Utilisateur>any()))
                .thenThrow(new IllegalStateException("Aucune tâche trouvée"));

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
        assertThrows(IllegalStateException.class, () -> taskService.getAllByUser(1L));
        verify(taskRepository).findByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TaskService#getAllByUser(Long)}
     */
    @Test
    void testGetAllByUser4() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucune tâche trouvée");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucune tâche trouvée");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucune tâche trouvée");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucune tâche trouvée");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucune tâche trouvée");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucune tâche trouvée");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucune tâche trouvée");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("jane.doe@example.org");
        admin2.setEvenements(new ArrayList<>());
        admin2.setGroupe(new ArrayList<>());
        admin2.setId(1L);
        admin2.setNom("Aucune tâche trouvée");
        admin2.setPassword("iloveyou");
        admin2.setPhone("6625550144");
        admin2.setPrenom("Aucune tâche trouvée");
        admin2.setRole(Role.ADMIN);
        admin2.setTaches(new ArrayList<>());

        Groupe groupe2 = new Groupe();
        groupe2.setAdmin(admin2);
        groupe2.setCode("Aucune tâche trouvée");
        groupe2.setDescription("The characteristics of someone or something");
        groupe2.setId(1L);
        groupe2.setNom("Aucune tâche trouvée");
        groupe2.setTaches(new ArrayList<>());
        groupe2.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Aucune tâche trouvée");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Aucune tâche trouvée");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());

        Tache tache = new Tache();
        tache.setCouleur("Aucune tâche trouvée");
        tache.setDateDebut("2020-03-01");
        tache.setDateFin("2020-03-01");
        tache.setDescription("The characteristics of someone or something");
        tache.setEvenement(evenement);
        tache.setGroupe(groupe2);
        tache.setId(1L);
        tache.setNom("Aucune tâche trouvée");
        tache.setType(TaskStatus.TODO);
        tache.setUtilisateur(utilisateur2);

        ArrayList<Tache> tacheList = new ArrayList<>();
        tacheList.add(tache);
        when(taskRepository.findByUtilisateur(Mockito.<Utilisateur>any())).thenReturn(tacheList);

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
        List<TaskDTO> actualAllByUser = taskService.getAllByUser(1L);

        // Assert
        verify(taskRepository).findByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualAllByUser.size());
    }

    /**
     * Method under test: {@link TaskService#getAllByUser(Long)}
     */
    @Test
    void testGetAllByUser5() {
        // Arrange
        Utilisateur admin = new Utilisateur();
        admin.setEmail("jane.doe@example.org");
        admin.setEvenements(new ArrayList<>());
        admin.setGroupe(new ArrayList<>());
        admin.setId(1L);
        admin.setNom("Aucune tâche trouvée");
        admin.setPassword("iloveyou");
        admin.setPhone("6625550144");
        admin.setPrenom("Aucune tâche trouvée");
        admin.setRole(Role.ADMIN);
        admin.setTaches(new ArrayList<>());

        Groupe groupe = new Groupe();
        groupe.setAdmin(admin);
        groupe.setCode("Aucune tâche trouvée");
        groupe.setDescription("The characteristics of someone or something");
        groupe.setId(1L);
        groupe.setNom("Aucune tâche trouvée");
        groupe.setTaches(new ArrayList<>());
        groupe.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setEvenements(new ArrayList<>());
        utilisateur.setGroupe(new ArrayList<>());
        utilisateur.setId(1L);
        utilisateur.setNom("Aucune tâche trouvée");
        utilisateur.setPassword("iloveyou");
        utilisateur.setPhone("6625550144");
        utilisateur.setPrenom("Aucune tâche trouvée");
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setTaches(new ArrayList<>());

        Evenement evenement = new Evenement();
        evenement.setDateDebut("2020-03-01");
        evenement.setDateFin("2020-03-01");
        evenement.setDescription("The characteristics of someone or something");
        evenement.setGroupe(groupe);
        evenement.setId(1L);
        evenement.setNom("Aucune tâche trouvée");
        evenement.setTaches(new ArrayList<>());
        evenement.setUtilisateur(utilisateur);

        Utilisateur admin2 = new Utilisateur();
        admin2.setEmail("jane.doe@example.org");
        admin2.setEvenements(new ArrayList<>());
        admin2.setGroupe(new ArrayList<>());
        admin2.setId(1L);
        admin2.setNom("Aucune tâche trouvée");
        admin2.setPassword("iloveyou");
        admin2.setPhone("6625550144");
        admin2.setPrenom("Aucune tâche trouvée");
        admin2.setRole(Role.ADMIN);
        admin2.setTaches(new ArrayList<>());

        Groupe groupe2 = new Groupe();
        groupe2.setAdmin(admin2);
        groupe2.setCode("Aucune tâche trouvée");
        groupe2.setDescription("The characteristics of someone or something");
        groupe2.setId(1L);
        groupe2.setNom("Aucune tâche trouvée");
        groupe2.setTaches(new ArrayList<>());
        groupe2.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setEmail("jane.doe@example.org");
        utilisateur2.setEvenements(new ArrayList<>());
        utilisateur2.setGroupe(new ArrayList<>());
        utilisateur2.setId(1L);
        utilisateur2.setNom("Aucune tâche trouvée");
        utilisateur2.setPassword("iloveyou");
        utilisateur2.setPhone("6625550144");
        utilisateur2.setPrenom("Aucune tâche trouvée");
        utilisateur2.setRole(Role.ADMIN);
        utilisateur2.setTaches(new ArrayList<>());

        Tache tache = new Tache();
        tache.setCouleur("Aucune tâche trouvée");
        tache.setDateDebut("2020-03-01");
        tache.setDateFin("2020-03-01");
        tache.setDescription("The characteristics of someone or something");
        tache.setEvenement(evenement);
        tache.setGroupe(groupe2);
        tache.setId(1L);
        tache.setNom("Aucune tâche trouvée");
        tache.setType(TaskStatus.TODO);
        tache.setUtilisateur(utilisateur2);

        Utilisateur admin3 = new Utilisateur();
        admin3.setEmail("john.smith@example.org");
        admin3.setEvenements(new ArrayList<>());
        admin3.setGroupe(new ArrayList<>());
        admin3.setId(2L);
        admin3.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin3.setPassword("Password");
        admin3.setPhone("8605550118");
        admin3.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin3.setRole(Role.USER);
        admin3.setTaches(new ArrayList<>());

        Groupe groupe3 = new Groupe();
        groupe3.setAdmin(admin3);
        groupe3.setCode("ca.christopher.applicationtache.modeles.Groupe");
        groupe3.setDescription("Description");
        groupe3.setId(2L);
        groupe3.setNom("ca.christopher.applicationtache.modeles.Groupe");
        groupe3.setTaches(new ArrayList<>());
        groupe3.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur3 = new Utilisateur();
        utilisateur3.setEmail("john.smith@example.org");
        utilisateur3.setEvenements(new ArrayList<>());
        utilisateur3.setGroupe(new ArrayList<>());
        utilisateur3.setId(2L);
        utilisateur3.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur3.setPassword("Password");
        utilisateur3.setPhone("8605550118");
        utilisateur3.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur3.setRole(Role.USER);
        utilisateur3.setTaches(new ArrayList<>());

        Evenement evenement2 = new Evenement();
        evenement2.setDateDebut("2020/03/01");
        evenement2.setDateFin("2020/03/01");
        evenement2.setDescription("Description");
        evenement2.setGroupe(groupe3);
        evenement2.setId(2L);
        evenement2.setNom("ca.christopher.applicationtache.modeles.Evenement");
        evenement2.setTaches(new ArrayList<>());
        evenement2.setUtilisateur(utilisateur3);

        Utilisateur admin4 = new Utilisateur();
        admin4.setEmail("john.smith@example.org");
        admin4.setEvenements(new ArrayList<>());
        admin4.setGroupe(new ArrayList<>());
        admin4.setId(2L);
        admin4.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin4.setPassword("Password");
        admin4.setPhone("8605550118");
        admin4.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        admin4.setRole(Role.USER);
        admin4.setTaches(new ArrayList<>());

        Groupe groupe4 = new Groupe();
        groupe4.setAdmin(admin4);
        groupe4.setCode("ca.christopher.applicationtache.modeles.Groupe");
        groupe4.setDescription("Description");
        groupe4.setId(2L);
        groupe4.setNom("ca.christopher.applicationtache.modeles.Groupe");
        groupe4.setTaches(new ArrayList<>());
        groupe4.setUtilisateurs(new ArrayList<>());

        Utilisateur utilisateur4 = new Utilisateur();
        utilisateur4.setEmail("john.smith@example.org");
        utilisateur4.setEvenements(new ArrayList<>());
        utilisateur4.setGroupe(new ArrayList<>());
        utilisateur4.setId(2L);
        utilisateur4.setNom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur4.setPassword("Password");
        utilisateur4.setPhone("8605550118");
        utilisateur4.setPrenom("ca.christopher.applicationtache.modeles.Utilisateur");
        utilisateur4.setRole(Role.USER);
        utilisateur4.setTaches(new ArrayList<>());

        Tache tache2 = new Tache();
        tache2.setCouleur("ca.christopher.applicationtache.modeles.Tache");
        tache2.setDateDebut("2020/03/01");
        tache2.setDateFin("2020/03/01");
        tache2.setDescription("Description");
        tache2.setEvenement(evenement2);
        tache2.setGroupe(groupe4);
        tache2.setId(2L);
        tache2.setNom("ca.christopher.applicationtache.modeles.Tache");
        tache2.setType(TaskStatus.IN_PROGRESS);
        tache2.setUtilisateur(utilisateur4);

        ArrayList<Tache> tacheList = new ArrayList<>();
        tacheList.add(tache2);
        tacheList.add(tache);
        when(taskRepository.findByUtilisateur(Mockito.<Utilisateur>any())).thenReturn(tacheList);

        Utilisateur utilisateur5 = new Utilisateur();
        utilisateur5.setEmail("jane.doe@example.org");
        utilisateur5.setEvenements(new ArrayList<>());
        utilisateur5.setGroupe(new ArrayList<>());
        utilisateur5.setId(1L);
        utilisateur5.setNom("Nom");
        utilisateur5.setPassword("iloveyou");
        utilisateur5.setPhone("6625550144");
        utilisateur5.setPrenom("Prenom");
        utilisateur5.setRole(Role.ADMIN);
        utilisateur5.setTaches(new ArrayList<>());
        Optional<Utilisateur> ofResult = Optional.of(utilisateur5);
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        List<TaskDTO> actualAllByUser = taskService.getAllByUser(1L);

        // Assert
        verify(taskRepository).findByUtilisateur(Mockito.<Utilisateur>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        assertEquals(2, actualAllByUser.size());
    }

    /**
     * Method under test: {@link TaskService#getAllByUser(Long)}
     */
    @Test
    void testGetAllByUser6() {
        // Arrange
        Optional<Utilisateur> emptyResult = Optional.empty();
        when(utilisateurRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(AppException.class, () -> taskService.getAllByUser(1L));
        verify(utilisateurRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TaskService#saveTaskWithGroup(TaskDTO, Long)}
     */
    @Test
    void testSaveTaskWithGroup() {
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

        Tache tache = new Tache();
        tache.setCouleur("Couleur");
        tache.setDateDebut("2020-03-01");
        tache.setDateFin("2020-03-01");
        tache.setDescription("The characteristics of someone or something");
        tache.setEvenement(evenement);
        tache.setGroupe(groupe2);
        tache.setId(1L);
        tache.setNom("Nom");
        tache.setType(TaskStatus.TODO);
        tache.setUtilisateur(utilisateur2);
        when(taskRepository.save(Mockito.<Tache>any())).thenReturn(tache);

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
        Optional<Groupe> ofResult2 = Optional.of(groupe3);
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        TaskDTO actualSaveTaskWithGroupResult = taskService.saveTaskWithGroup(new TaskDTO(), 1L);

        // Assert
        verify(groupeRepository).findById(Mockito.<Long>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(taskRepository).save(Mockito.<Tache>any());
        assertEquals("2020-03-01", actualSaveTaskWithGroupResult.getDateDebut());
        assertEquals("2020-03-01", actualSaveTaskWithGroupResult.getDateFin());
        assertEquals("Couleur", actualSaveTaskWithGroupResult.getCouleur());
        assertEquals("Nom", actualSaveTaskWithGroupResult.getNom());
        assertEquals("The characteristics of someone or something", actualSaveTaskWithGroupResult.getDescription());
        assertEquals(1L, actualSaveTaskWithGroupResult.getId());
        assertEquals(1L, actualSaveTaskWithGroupResult.getEvenement().longValue());
        assertEquals(1L, actualSaveTaskWithGroupResult.getGroupe().longValue());
        assertEquals(1L, actualSaveTaskWithGroupResult.getUtilisateur().longValue());
        assertEquals(TaskStatus.TODO, actualSaveTaskWithGroupResult.getType());
    }

    /**
     * Method under test: {@link TaskService#saveTaskWithGroup(TaskDTO, Long)}
     */
    @Test
    void testSaveTaskWithGroup2() {
        // Arrange
        when(taskRepository.save(Mockito.<Tache>any())).thenThrow(new AppException());

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
        assertThrows(AppException.class, () -> taskService.saveTaskWithGroup(new TaskDTO(), 1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(taskRepository).save(Mockito.<Tache>any());
    }

    /**
     * Method under test: {@link TaskService#saveTaskWithGroup(TaskDTO, Long)}
     */
    @Test
    void testSaveTaskWithGroup3() {
        // Arrange
        when(taskRepository.save(Mockito.<Tache>any())).thenThrow(new IllegalStateException("foo"));

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
        assertThrows(IllegalStateException.class, () -> taskService.saveTaskWithGroup(new TaskDTO(), 1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
        verify(utilisateurRepository).findById(Mockito.<Long>any());
        verify(taskRepository).save(Mockito.<Tache>any());
    }

    /**
     * Method under test: {@link TaskService#getAllByGroup(Long)}
     */
    @Test
    void testGetAllByGroup() {
        // Arrange
        when(taskRepository.findByGroupe(Mockito.<Groupe>any())).thenReturn(new ArrayList<>());

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
        assertThrows(AppException.class, () -> taskService.getAllByGroup(1L));
        verify(taskRepository).findByGroupe(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TaskService#getAllByGroup(Long)}
     */
    @Test
    void testGetAllByGroup2() {
        // Arrange
        when(taskRepository.findByGroupe(Mockito.<Groupe>any())).thenThrow(new AppException());

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
        assertThrows(AppException.class, () -> taskService.getAllByGroup(1L));
        verify(taskRepository).findByGroupe(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TaskService#getAllByGroup(Long)}
     */
    @Test
    void testGetAllByGroup3() {
        // Arrange
        when(taskRepository.findByGroupe(Mockito.<Groupe>any()))
                .thenThrow(new IllegalStateException("Aucune tâche trouvée"));

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
        assertThrows(IllegalStateException.class, () -> taskService.getAllByGroup(1L));
        verify(taskRepository).findByGroupe(Mockito.<Groupe>any());
        verify(groupeRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TaskService#getAllByGroup(Long)}
     */
    @Test
    void testGetAllByGroup6() {
        // Arrange
        Optional<Groupe> emptyResult = Optional.empty();
        when(groupeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(AppException.class, () -> taskService.getAllByGroup(1L));
        verify(groupeRepository).findById(Mockito.<Long>any());
    }
}
