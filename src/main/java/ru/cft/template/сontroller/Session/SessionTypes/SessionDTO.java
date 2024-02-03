package ru.cft.template.сontroller.Session.SessionTypes;

import lombok.Data;
import ru.cft.template.model.Session;

import java.time.LocalDate;

@Data
public class SessionDTO {
    private Long id;
    private Long userId;
    private LocalDate expirationTime;
    private Boolean active;
    public SessionDTO(Session session){
        id = session.getId();
        userId=session.getUserId();
        expirationTime=session.getExpirationTime();
        active= (session.getActive() != 0);
    }
}
