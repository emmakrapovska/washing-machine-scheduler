package mk.ukim.finki.washingmachineapp.service.domain.impl;

import mk.ukim.finki.washingmachineapp.models.domain.Machine;
import mk.ukim.finki.washingmachineapp.models.exception.MachineNotFoundException;
import mk.ukim.finki.washingmachineapp.repository.MachineRepository;
import mk.ukim.finki.washingmachineapp.service.domain.MachineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public Optional<Machine> findById(Long id) {
        return machineRepository.findById(id);
    }

    @Override
    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    @Override
    public Machine create(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    @Transactional
    public Machine update(Long id, Machine machine) {
        Machine existingMachine = machineRepository.findById(id)
                .orElseThrow(() -> new MachineNotFoundException(id));

        existingMachine.setMachineType(machine.getMachineType());
        existingMachine.setLocation(machine.getLocation());
        existingMachine.setStatus(machine.getStatus());

        return machineRepository.save(existingMachine);
    }

    @Override
    public Optional<Machine> deleteById(Long id) {
        Optional<Machine> existingMachine = machineRepository.findById(id);
        existingMachine.ifPresent(machineRepository::delete);
        return existingMachine;
    }
}