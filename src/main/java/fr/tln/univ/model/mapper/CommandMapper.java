package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Command;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CommandMapper {
    public abstract CommandDto mapCommandToCommandDto(Command command);
    public abstract Command mapCommandDtoToCommand(CommandDto commandDto);
    public abstract List<CommandDto> listCommandToListCommandDto(List<Command> commandList);
}
