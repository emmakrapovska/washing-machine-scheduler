package mk.ukim.finki.washingmachineapp.service.application.impl;

import mk.ukim.finki.washingmachineapp.models.dto.CreateMachineRequest;
import mk.ukim.finki.washingmachineapp.models.dto.MachineResponse;
import mk.ukim.finki.washingmachineapp.service.application.MachineApplicationService;
import mk.ukim.finki.washingmachineapp.service.domain.MachineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineApplicationServiceImpl implements MachineApplicationService {

    private final MachineService machineService;

    public MachineApplicationServiceImpl(MachineService machineService) {
        this.machineService = machineService;
    }

    @Override
    public Optional<MachineResponse> findById(Long id) {
        return machineService.findById(id).map(MachineResponse::from);
    }

    @Override
    public List<MachineResponse> findAll() {
        return machineService.findAll().stream()
                .map(MachineResponse::from)
                .toList();
    }

    @Override
    public MachineResponse create(CreateMachineRequest request) {
        return MachineResponse.from(machineService.create(request.toMachine()));
    }

    @Override
    public MachineResponse update(Long id, CreateMachineRequest request) {
        return MachineResponse.from(machineService.update(id, request.toMachine()));
    }

    @Override
    public Optional<MachineResponse> deleteById(Long id) {
        return machineService.deleteById(id).map(MachineResponse::from);
    }
}