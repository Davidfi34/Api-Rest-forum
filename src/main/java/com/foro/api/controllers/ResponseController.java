package com.foro.api.controllers;

import com.foro.api.models.response.DTO.DataResponse;
import com.foro.api.models.response.DTO.ResponseRegistration;
import com.foro.api.models.response.DTO.UpdateDataResponse;
import com.foro.api.models.response.Response;
import com.foro.api.repository.ResponseRepository;
import com.foro.api.services.SaveResponseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic/response")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {

    @Autowired
    private SaveResponseRepository saveResponseRepository;
    @Autowired
    private ResponseRepository responseRepository;


    @Operation(summary = "response registration method")
    @PostMapping
    @Transactional
    public ResponseEntity<DataResponse> createResponse (@RequestBody @Valid ResponseRegistration responseRegistration,
                                                        UriComponentsBuilder uriComponentsBuilder){

        DataResponse dataResponse = saveResponseRepository.saveResponse(responseRegistration);
        URI url = uriComponentsBuilder.path("/topic/response/{id}").buildAndExpand(dataResponse.id()).toUri();
        return ResponseEntity.created(url).body(dataResponse);
    }

    @Operation(summary = "method to obtain all the responses")
    @GetMapping
    public ResponseEntity<Page<DataResponse>> getAllResponses(@PageableDefault(size = 10) Pageable pages){
        return ResponseEntity.ok(responseRepository.findByActiveTrue(pages).map(DataResponse::new));
    }

    @Operation(summary = "method get by ID reply to topic")
    @GetMapping(path = "/{id}")
    public ResponseEntity<DataResponse> getByIdResponse(@PathVariable Long id){
        Response response = responseRepository.getReferenceById(id);
        DataResponse dataResponse = new DataResponse(response);
        return ResponseEntity.ok(dataResponse);
    }

    @Operation(summary = "response update method")
    @PutMapping
    @Transactional
    public ResponseEntity<DataResponse> updateResponse(@RequestBody @Valid UpdateDataResponse updateDataResponse) {
        Response response = responseRepository.getReferenceById(updateDataResponse.id());
        response.updateResponse(updateDataResponse);
        DataResponse dataResponse = new DataResponse(response);
        return ResponseEntity.ok(dataResponse);
    }

    @Operation(summary = "method of logical deletion of response")
    @DeleteMapping(path ="/{id}")
    @Transactional
    public ResponseEntity deleteResponse(@PathVariable Long id) {
        Response response = responseRepository.getReferenceById(id);
        response.deactivateResponse();
        return ResponseEntity.noContent().build();
    }

}

