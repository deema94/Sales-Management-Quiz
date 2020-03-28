package com.maids.springbootquiz.controller;

import com.maids.springbootquiz.exception.ResourceNotFoundException;
import com.maids.springbootquiz.model.Client;
import com.maids.springbootquiz.repository.ClientRepository;
import com.maids.springbootquiz.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.maids.springbootquiz.response.CustomResponseHelper.failureResponse;
import static com.maids.springbootquiz.response.CustomResponseHelper.successResponse;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("")
    public CustomResponse<List<Client>> getAllClients() {
        CustomResponse<List<Client>> listCustomResponse;
        try {
            listCustomResponse = successResponse(clientRepository.findAll());
        }
        catch (Exception e){
            listCustomResponse = failureResponse(e.getMessage());
        }
        return listCustomResponse;
    }

    @GetMapping("/{id}")
    public CustomResponse<Client> getClientById(@PathVariable(value = "id") Long clientId){
        CustomResponse<Client> customResponse;
        try {
            Client client =
                    clientRepository
                            .findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found on :: " + clientId));
            customResponse = successResponse(client);
        }
        catch (Exception e){
            customResponse = failureResponse(e.getMessage());
        }
        return customResponse;
    }

    @PostMapping("")
    public CustomResponse<Client> createClient(@Valid @RequestBody Client client) {
        CustomResponse<Client> customResponse;
        try {
            Client newClient =
                    clientRepository.save(client);
            customResponse = successResponse(newClient);
        }
        catch (Exception e){
            customResponse = failureResponse(e.getMessage());
        }
        return customResponse;
    }

    @PutMapping("/{id}")
    public CustomResponse<Client> updateClient(
            @PathVariable(value = "id") Long clientId, @Valid @RequestBody Client clientDetails){
        CustomResponse<Client> customResponse;
        try {
            Client newClient =
                    clientRepository
                            .findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found on :: " + clientId));
            newClient.setFirstName(clientDetails.getFirstName());
            newClient.setLastName(clientDetails.getLastName());
            newClient.setMobile(clientDetails.getMobile());
            final Client updatedClient = clientRepository.save(newClient);
            customResponse = successResponse(updatedClient);
        }
        catch (Exception e){
            customResponse = failureResponse(e.getMessage());
        }
        return customResponse;
    }
}
