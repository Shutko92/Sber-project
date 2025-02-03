package org.application.sbertask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.application.sbertask.dto.NewClientRequest;
import org.application.sbertask.dto.UpdateClientRequest;
import org.application.sbertask.model.Client;
import org.application.sbertask.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @Operation(summary = "Create a new client",
            description = "Creates a new client with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully"),
            @ApiResponse(responseCode = "500", description = "Invalid input data")
    })
    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@RequestBody NewClientRequest request) {
        return ResponseEntity.ok(clientService.createClient(request));
    }

    @Operation(summary = "Block a client",
            description = "Blocks an active client by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client blocked successfully"),
            @ApiResponse(responseCode = "500", description = "Invalid input data/ invalid Client state")
    })
    @PutMapping("/client/block/{clientId}")
    public ResponseEntity<Client> blockClient(@PathVariable long clientId) {
        return ResponseEntity.ok(clientService.blockClient(clientId));
    }

    @Operation(summary = "Unblock a client",
            description = "Unblocks a blocked client by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client unblocked successfully"),
            @ApiResponse(responseCode = "500", description = "Invalid input data/ invalid Client state")
    })
    @PutMapping("/client/unblock/{clientId}")
    public ResponseEntity<Client> unblockClient(@PathVariable long clientId) {
        return ResponseEntity.ok(clientService.unblockClient(clientId));
    }

    @Operation(summary = "Update client details", description = "Updates the details of an existing client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully"),
            @ApiResponse(responseCode = "500", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Client not found")
    })
    @PutMapping("/client/update")
    public ResponseEntity<Client> updateClient(@RequestBody UpdateClientRequest request) {
        return ResponseEntity.ok(clientService.updateClient(request));
    }
}
