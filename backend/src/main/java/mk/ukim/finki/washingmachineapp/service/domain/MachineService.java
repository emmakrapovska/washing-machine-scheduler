package mk.ukim.finki.washingmachineapp.service.domain;

import mk.ukim.finki.washingmachineapp.models.domain.Machine;

import java.util.List;
import java.util.Optional;

public interface MachineService {

    Optional<Machine> findById(Long id);

    List<Machine> findAll();

    Machine create(Machine machine);

    Machine update(Long id, Machine machine);

    Optional<Machine> deleteById(Long id);
}