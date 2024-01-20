package fr.tln.univ.controller;

import fr.tln.univ.config.RestCaller;
import fr.tln.univ.model.CommandDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@ViewScoped
@Named("loginBean")
@Setter
@Getter
public class CommandBean implements Serializable {
    private Integer id;
    private int count;
    private Date date;

    public void add() {
        CommandDto commandDto = new CommandDto();
        commandDto.setId(id);
        commandDto.setCount(count);
        commandDto.setDate(date);
        RestCaller restCaller = new RestCaller();
        restCaller.call("api/commands/v1", HttpMethod.POST, commandDto, CommandDto.class);
    }
}
