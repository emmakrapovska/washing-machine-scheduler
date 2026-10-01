package mk.ukim.finki.washingmachineapp.service.application;

import mk.ukim.finki.washingmachineapp.models.dto.CreateMachineRequest;
import mk.ukim.finki.washingmachineapp.models.dto.MachineResponse;

import java.util.List;
import java.util.Optional;

public interface MachineApplicationService {

    Optional<MachineResponse> findById(Long id);

    List<MachineResponse> findAll();

    MachineResponse create(CreateMachineRequest request);

    MachineResponse update(Long id, CreateMachineRequest request);

    Optional<MachineResponse> deleteById(Long id);
}