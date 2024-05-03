package ca.christopher.applicationtache.controller;

import static org.mockito.Mockito.when;

import ca.christopher.applicationtache.DTO.UtilisateurDTO;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.services.UtilisateurService;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UtilisateurController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UtilisateurControllerTest {
    @Autowired
    private UtilisateurController utilisateurController;

    @MockBean
    private UtilisateurService utilisateurService;

    /**
     * Method under test: {@link UtilisateurController#getAllUtilisateurs()}
     */
    @Test
    void testGetAllUtilisateurs() throws Exception {
        // Arrange
        Optional<List<Utilisateur>> ofResult = Optional.of(new ArrayList<>());
        when(utilisateurService.getAllUtilisateurs()).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/utilisateur/all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UtilisateurController#getUtilisateur(Long)}
     */
    @Test
    void testGetUtilisateur() throws Exception {
        // Arrange
        Optional<UtilisateurDTO> ofResult = Optional
                .of(new UtilisateurDTO("Nom", "Prenom", "6625550144", "jane.doe@example.org"));
        when(utilisateurService.getUser(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/utilisateur/get");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(utilisateurController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example.org\"}"));
    }
}
