package mk.ukim.finki.washingmachineapp.repository;

import mk.ukim.finki.washingmachineapp.models.domain.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
}
