package job.testtask.minibank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table( name ="users")
@Getter
@Setter
public class UserEntity extends BaseEntity{

    @Column(name ="name")
    String name;

    @JsonIgnore
    @ColumnTransformer  //Todo move secret key to application.properties
            (forColumn = "password", read = "pgp_sym_decrypt(password, 'secret-key')", write ="pgp_sym_encrypt(?, secret-key)")
    @Column(name ="password")
    String password;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @JsonManagedReference
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    List<EmailDataEntity> email;

    @JsonManagedReference
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    List<PhoneDataEntity> phone;

    @OneToOne(mappedBy = "user")
    AccountEntity account;

}
