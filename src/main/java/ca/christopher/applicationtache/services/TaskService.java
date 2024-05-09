package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.DTO.TaskDTO;
import ca.christopher.applicationtache.DTO.TaskWithUserDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Tache;
import ca.christopher.applicationtache.modeles.TaskStatus;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.GroupeRepository;
import ca.christopher.applicationtache.repositories.TaskRepository;
import ca.christopher.applicationtache.repositories.UtilisateurRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final GroupeRepository groupeRepository;

    public TaskService(TaskRepository taskRepository, UtilisateurRepository utilisateurRepository, GroupeRepository groupeRepository) {
        this.taskRepository = taskRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.groupeRepository = groupeRepository;
    }

    public TaskDTO saveTask(TaskDTO task) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findById(task.getUtilisateur()).orElseThrow(() -> new AppException("Utilisateur non trouvé", HttpStatusCode.valueOf(404)));
            Tache tache = task.fromDto();
            tache.setUtilisateur(utilisateur);
            tache.setType(TaskStatus.TODO);
            return new TaskDTO(taskRepository.save(tache));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(),e.getCode());
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de créer une tâche");
        }
    }

    public List<TaskDTO> getAllByUser(Long userId) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findById(userId).orElseThrow(() -> new AppException("Utilisateur non trouvé", HttpStatusCode.valueOf(404)));
            List<TaskDTO> taches = taskRepository.findByUtilisateur(utilisateur).stream().map(TaskDTO::new).toList();
            if (taches.isEmpty()) {
                throw new AppException("Aucune tâche trouvée", HttpStatusCode.valueOf(404));
            }
            return taches;
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de trouver les tâches de l'utilisateur");
        }
    }

    public TaskDTO saveTaskWithGroup(TaskDTO task, Long groupId) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findById(task.getUtilisateur()).orElseThrow(() -> new AppException("Utilisateur non trouvé", HttpStatusCode.valueOf(404)));
            Groupe groupe = groupeRepository.findById(groupId).orElseThrow(() -> new AppException("Groupe non trouvé", HttpStatusCode.valueOf(404)));
            Tache tache = task.fromDto();
            tache.setUtilisateur(utilisateur);
            tache.setType(TaskStatus.TODO);
            tache.setGroupe(groupe);
            return new TaskDTO(taskRepository.save(tache));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(),e.getCode());
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de créer une tâche");
        }
    }

    public List<TaskWithUserDTO> getAllByGroup(Long groupId) {
        try {
            Groupe groupe = groupeRepository.findById(groupId).orElseThrow(() -> new AppException("Groupe non trouvé", HttpStatusCode.valueOf(404)));
            List<TaskWithUserDTO> taches = taskRepository.findByGroupe(groupe).stream().map(TaskWithUserDTO::new).toList();
            if (taches.isEmpty()) {
                throw new AppException("Aucune tâche trouvée", HttpStatusCode.valueOf(404));
            }
            return taches;
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de trouver les tâches du groupe");
        }
    }

    public void deleteTask(Long taskId) {
        try {
            Tache tache = taskRepository.findById(taskId).orElseThrow(() -> new AppException("Tâche non trouvée", HttpStatusCode.valueOf(404)));
            taskRepository.delete(tache);
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de supprimer la tâche");
        }
    }

    public TaskDTO getTaskById(Long id) {
        try {
            Tache tache = taskRepository.findById(id).orElseThrow(() -> new AppException("Tâche non trouvée", HttpStatusCode.valueOf(404)));
            return new TaskDTO(tache);
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de récupérer la tâche");
        }
    }

    public TaskDTO updateTask(TaskDTO task) {
        try {
            Tache tache = taskRepository.findById(task.getId()).orElseThrow(() -> new AppException("Tâche non trouvée", HttpStatusCode.valueOf(404)));
            tache.setNom(task.getNom());
            tache.setDescription(task.getDescription());
            tache.setDateDebut(task.getDateDebut());
            tache.setDateFin(task.getDateFin());
            tache.setType(task.getType());
            return new TaskDTO(taskRepository.save(tache));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de mettre à jour la tâche");
        }
    }
}
