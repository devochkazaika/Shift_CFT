package ru.cft.template.сontroller.Session;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.model.Session;
import ru.cft.template.model.User;
import ru.cft.template.service.SessionService;
import ru.cft.template.service.UserService;
import ru.cft.template.сontroller.Session.Autorisation.Autorisation;
import ru.cft.template.сontroller.Session.Autorisation.PhoneAndPassword;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class SessionController {
    @Autowired
    private final SessionService sessionsRepo;
    private final UserService userRepo;

    @GetMapping("/users/{id}/sessions")
    public SessionDTO getSessionById(@PathVariable Long id) {
        return new SessionDTO(sessionsRepo.getUserSession(id).get(0));
    }
    @GetMapping("/users/sessions")
    public List<SessionDTO> getSessions() {
        List<SessionDTO> sessionRequest = new ArrayList<SessionDTO>();
        for (Session session : sessionsRepo.findAll()){
            sessionRequest.add(new SessionDTO(session));
        }
        return sessionRequest;
    }
    @PostMapping("/sessions")
    public void save(@RequestBody Session session) {
        sessionsRepo.save(session);
    }
    @PostMapping("/users/sessions")
    public Autorisation insert(@RequestBody PhoneAndPassword token) {
        Autorisation autorisation = new Autorisation();
        User u = userRepo.findByPhone(token.getPhone());
        if (u.getPassword() == null || u.getPassword().equals(token.getPassword())){
            Session k = new Session();
            k.setExpirationTime(LocalDate.now().plusDays(3));
            k.setActive(1);
            k.setUserId(u.getId());
            sessionsRepo.save(k);
            autorisation.setId(k.getId());
            autorisation.setExpirationTime(k.getExpirationTime());
            autorisation.setToken("ASAS");
            autorisation.setUserId(k.getUserId());
        }
        return autorisation;
    }
    @DeleteMapping("/users/sessions/{id}")
    public void delete(@PathVariable Long id){
        sessionsRepo.delete(id);
    }


}