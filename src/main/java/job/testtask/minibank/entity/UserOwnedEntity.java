package job.testtask.minibank.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import job.testtask.minibank.exception.MinibankException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
@Data
@NoArgsConstructor
public class UserOwnedEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name ="user_id")
    @JsonBackReference
    UserEntity user;


    @PreRemove
    @PreUpdate
    private void checkPermission () throws Exception {
        if (! SecurityContextHolder.getContext().getAuthentication().getName().equals(user.getName())){
            throw new MinibankException("User not authorized for this operation");
        }
    }


}
