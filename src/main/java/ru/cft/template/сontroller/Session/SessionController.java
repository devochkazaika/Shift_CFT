package ru.cft.template.сontroller.Session;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.model.Session;
import ru.cft.template.model.User;
import ru.cft.template.service.impl.SessionService;
import ru.cft.template.service.impl.UserService;
import ru.cft.template.сontroller.Session.Autorisation.Autorisation;
import ru.cft.template.сontroller.Session.Autorisation.PhoneAndPassword;
import ru.cft.template.сontroller.Session.SessionTypes.SessionDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class SessionController {
    @Autowired
    private final SessionService sessionsService;
    private final UserService userRepo;

    @GetMapping("/users/{id}/sessions")
    public SessionDTO getSessionById(@PathVariable Long id) {
        return new SessionDTO(sessionsService.getUserSession(id).get(0));
    }
    @GetMapping("/users/sessions")
    public List<SessionDTO> getSessions() {
        return sessionsService.getSessions();

    }
    @PostMapping("/sessions")
    public void save(@RequestBody Session session) {
        sessionsService.save(session);
    }
    @PostMapping("/users/sessions")
    public Autorisation insert(@RequestBody PhoneAndPassword token) {
        return sessionsService.save(token);
    }
    @DeleteMapping("/users/sessions/{id}")
    public void delete(@PathVariable Long id){
        sessionsService.delete(id);
    }


}