package org.application.sbertask.service;

import lombok.RequiredArgsConstructor;
import org.application.sbertask.dto.NewClientRequest;
import org.application.sbertask.dto.UpdateClientRequest;
import org.application.sbertask.exception.IllegalClientStateException;
import org.application.sbertask.model.Client;
import org.application.sbertask.model.ClientStatus;
import org.application.sbertask.model.UserRole;
import org.application.sbertask.repository.ClientRepository;
import org.application.sbertask.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserRoleRepository userRoleRepository;

    public Client createClient(NewClientRequest request) {
        final long DEFAULT_ROLE_ID = 1;
        Client client = Client.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .patronymic(request.getPatronymic())
                .email(request.getEmail()).clientStatus(ClientStatus.ACTIVE).build();
        Client saved = clientRepository.save(client);

        userRoleRepository.save(UserRole.builder()
                .userId(saved.getId()).roleId(DEFAULT_ROLE_ID)
                .build());
        return saved;
    }

    public Client blockClient(long clientId) {
        Client client = getClientIfExists(clientId);
        if (client.getClientStatus().equals(ClientStatus.BLOCKED)) {
            throw new IllegalClientStateException("Client with ID " + clientId + " is already blocked.");
        } else {
            client.setClientStatus(ClientStatus.BLOCKED);
            client = clientRepository.save(client);
        }
        return client;
    }

    public Client unblockClient(long clientId) {
        Client client = getClientIfExists(clientId);
        if (client.getClientStatus().equals(ClientStatus.ACTIVE)) {
            throw new IllegalClientStateException("Client with ID " + clientId + " is already active.");
        } else {
            client.setClientStatus(ClientStatus.ACTIVE);
            client = clientRepository.save(client);
        }
        return client;
    }

    public Client updateClient(UpdateClientRequest request) {
        Client client = getClientIfExists(request.getId());
        if (request.getFirstName() != null) {
            client.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            client.setLastName(request.getLastName());
        }

        if (request.getPatronymic() != null) {
            client.setPatronymic(request.getPatronymic());
        }

        if (request.getEmail() != null) {
            client.setEmail(request.getEmail());
        }

        if (request.getClientStatus() != null) {
            client.setClientStatus(request.getClientStatus());
        }

        return clientRepository.save(client);
    }

    private Client getClientIfExists(long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new IllegalClientStateException("Client with ID" + clientId + " not found."));
    }
}
