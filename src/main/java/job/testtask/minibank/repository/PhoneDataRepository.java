package job.testtask.minibank.repository;

import job.testtask.minibank.entity.PhoneDataEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneDataRepository extends JpaRepository<PhoneDataEntity, Long> {


  //  @Cacheable(value = "phones", key = "#phone.toString()")
    PhoneDataEntity getPhoneDataEntityByPhone(String phone);






}
