package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Command;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    public ClientDto mapClientToClientDto(Client client) {
        ClientDto clientDto = null;
        if (client != null) {
            clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setName(client.getName());
            clientDto.setFamily(client.getFamily());
            clientDto.setEmail(client.getEmail());
            clientDto.setPassword(client.getPassword());
            List<CommandDto> commandDtoList = new ArrayList<>();
            List<Command> commandList = client.getCommandList();

            for (Command co : commandList) {
                CommandDto commandDto = new CommandDto();
                commandDto.setId(co.getId());
                commandDto.setCount(co.getCount());
                commandDtoList.add(commandDto);
            }
            clientDto.setCommandDtoList(commandDtoList);
        }
        return clientDto;
    }
    public List<ClientDto> listClientToListClientDtoMapper(List<Client> clientList){
        List<ClientDto> clientDtoList = new ArrayList<>();

        for (Client client : clientList) {
            ClientDto clientDto = mapClientToClientDto(client);
            clientDtoList.add(clientDto);
        }
        return clientDtoList;
    }
}
