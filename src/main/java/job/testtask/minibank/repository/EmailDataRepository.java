package job.testtask.minibank.repository;

import job.testtask.minibank.entity.EmailDataEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDataRepository extends JpaRepository<EmailDataEntity, Long> {

   // @Cacheable(value = "emails", key = "#email.toString()")
    EmailDataEntity getEmailDataEntityByEmail(String email);








}
