package mk.ukim.finki.washingmachineapp.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.washingmachineapp.models.dto.CreateMachineRequest;
import mk.ukim.finki.washingmachineapp.models.dto.MachineResponse;
import mk.ukim.finki.washingmachineapp.models.exception.MachineNotFoundException;
import mk.ukim.finki.washingmachineapp.service.application.MachineApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machines")
public class MachineController {

    private final MachineApplicationService machineApplicationService;

    public MachineController(MachineApplicationService machineApplicationService) {
        this.machineApplicationService = machineApplicationService;
    }

    @GetMapping
    public List<MachineResponse> findAll() {
        return machineApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MachineResponse> findById(@PathVariable Long id) {
        return machineApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MachineNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MachineResponse create(@Valid @RequestBody CreateMachineRequest request) {
        return machineApplicationService.create(request);
    }

    @PutMapping("/{id}")
    public MachineResponse update(@PathVariable Long id, @Valid @RequestBody CreateMachineRequest request) {
        return machineApplicationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MachineResponse> deleteById(@PathVariable Long id) {
        return machineApplicationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MachineNotFoundException(id));
    }
}