package job.testtask.minibank.entity;//package job.testtask.minibank.entity;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;


@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mb_generator")
    @SequenceGenerator(name = "mb_generator", sequenceName = "public.mbsequence", allocationSize = 1)
    Long id;


}