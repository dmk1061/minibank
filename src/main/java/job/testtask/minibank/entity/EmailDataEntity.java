package job.testtask.minibank.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Column;
import job.testtask.minibank.exception.MinibankException;
import lombok.Data;

@Entity
@Table(name="email_data")
@Data
public class EmailDataEntity extends UserOwnedEntity {

    @Column(name="email")
    String email;

    @PreRemove
    private  void preRemove() throws  Exception{
        if(user.getEmail().size()==1){
            throw new MinibankException("You cant delete your last email");
        }
    }



}
