package mk.ukim.finki.washingmachineapp.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.washingmachineapp.models.enums.MachineStatus;
import mk.ukim.finki.washingmachineapp.models.enums.MachineType;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Machine extends BaseAuditableEntity {

    @Enumerated(EnumType.STRING)
    private MachineType machineType;
    private String location;
    @Enumerated(EnumType.STRING)
    private MachineStatus status;

    public Machine(MachineType machineType, String location, MachineStatus status){
        this.machineType=machineType;
        this.location=location;
        this.status=status;
    }

}
