package fr.tln.univ.controller;

import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Command;
import fr.tln.univ.model.mapper.CommandMapper;
import fr.tln.univ.service.CommandService;
import fr.tln.univ.service.CommandServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/command")
public class CommandController {

    private final CommandServiceImp commandServiceImp;
    private final CommandService commandService;
    private final CommandMapper commandMapper;

    @PostMapping("/save")
    public ResponseEntity<Command> addCommand(@Valid @RequestBody CommandDto commandDto
            , @RequestHeader("token") String token) {
        Command saveCommand = commandServiceImp.save(commandDto, token);
        return new ResponseEntity<>(saveCommand, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Command> getCommandById(@PathVariable Integer id) {
        Command command = this.commandService.getById(id);
        return ResponseEntity.ok(commandMapper.mapCommandToCommandDto(command));
    }

    @GetMapping("/list")
    public List<Command> getAllCommand() {
        return commandServiceImp.getAll();
    }

    @PutMapping("/{id}")
    public Command updateCommande(@PathVariable Integer id, @Valid @RequestBody CommandDto commandDto
            , @RequestHeader("token") String token) {
        return commandServiceImp.update(commandDto, id, token);
    }

    @GetMapping("/list/{date}")
    public List<Command> getAllCommandByDate(@PathVariable LocalDate date) {
        return commandServiceImp.getAllByDate(date);
    }
}