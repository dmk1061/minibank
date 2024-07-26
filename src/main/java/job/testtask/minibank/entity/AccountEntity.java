package job.testtask.minibank.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Table(name = "account")
@Data
public class AccountEntity extends BaseEntity{

    @JsonIgnore
    @Transient
    final ReentrantLock lock = new ReentrantLock();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name ="user_id")
    @JsonBackReference
    UserEntity user;

    @JsonIgnore
    @Column(name = "balance")
    BigDecimal balance;

    @JsonIgnore
    @Column(name = "initial_balance")
    BigDecimal initialBalance;
}
