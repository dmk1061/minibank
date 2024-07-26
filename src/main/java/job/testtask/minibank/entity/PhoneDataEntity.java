package job.testtask.minibank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.PreRemove;
import job.testtask.minibank.exception.MinibankException;
import lombok.Data;


@Entity
@Table(name = "phone_data")
@Data
public class PhoneDataEntity extends UserOwnedEntity {


    @Column(name= "phone")
    String phone;

    @PreRemove
    private  void preRemove() throws  Exception{
        if(user.getEmail().size()==1){
            throw new MinibankException("You cant delete your last phone");
        }
    }
}
