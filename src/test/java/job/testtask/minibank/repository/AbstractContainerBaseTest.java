package job.testtask.minibank.repository;


import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class AbstractContainerBaseTest {
    static final PostgreSQLContainer POSTGRES_CONTAINER;

    static {
        PostgreSQLContainer POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:latest")
                .withUsername("postgres")
                .withPassword("postgres")
                .withDatabaseName("postgress");
        POSTGRES_CONTAINER.start();
    }
    static {
        POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:latest")
                .withUsername("postgres")
                .withPassword("postgres")
                .withDatabaseName("postgres");
        POSTGRES_CONTAINER.start();
    }


    @DynamicPropertySource
    public static void dynamicPropertySource(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", ()->POSTGRES_CONTAINER.getJdbcUrl());
        dynamicPropertyRegistry.add("spring.datasource.username",()-> POSTGRES_CONTAINER.getUsername());
        dynamicPropertyRegistry.add("spring.datasource.password", ()->POSTGRES_CONTAINER.getPassword());
    }

}
