package job.testtask.minibank.repository;

import job.testtask.minibank.entity.EmailDataEntity;
import job.testtask.minibank.entity.PhoneDataEntity;
import job.testtask.minibank.entity.UserEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>, CrudRepository<UserEntity, Long> {

    UserEntity getUserEntityByPhone(PhoneDataEntity phoneDataEntity);

    UserEntity getUserEntityByEmail(EmailDataEntity emailDataEntity);

    Optional<UserEntity> findById(Long id);

   //r @Cacheable(value = "users", key = "#name.toString()")
    @Query ("Select u from UserEntity u where u.name =?1")
    UserEntity getUserEntityByNameCached(String name);

    UserEntity getUserEntityByName(String name);

  //  @Cacheable(value = "users", key = "#name.toString()")
    List<UserEntity> getUserEntitiesByNameLike(String name, Pageable pageable);


    List<UserEntity> getUserEntitiesByDateOfBirthAfter(LocalDate date, Pageable pageable);

}
