package mk.ukim.finki.washingmachineapp.models.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends BaseAuditableEntity {

    private String name;

    private String roomNumber;

    @Column(unique = true, nullable = false)
    private String email;


    public Student(String name, String roomNumber,String email){
        this.name=name;
        this.roomNumber=roomNumber;
        this.email=email;
    }
}