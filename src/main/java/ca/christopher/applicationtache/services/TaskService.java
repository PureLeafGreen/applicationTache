package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.DTO.TaskDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.modeles.Tache;
import ca.christopher.applicationtache.modeles.TaskStatus;
import ca.christopher.applicationtache.modeles.Utilisateur;
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

    public TaskService(TaskRepository taskRepository, UtilisateurRepository utilisateurRepository) {
        this.taskRepository = taskRepository;
        this.utilisateurRepository = utilisateurRepository;
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
}
