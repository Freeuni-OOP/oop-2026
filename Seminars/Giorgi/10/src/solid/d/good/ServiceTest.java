package solid.d.good;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceTest {

    private GoodDatabaseService service;

    @BeforeEach
    public void setUp() {
        // we can use fake database and not real!!!
        service = new GoodDatabaseService(new FakeDatabase());
    }

    @Test
    public void testSaveUser() {
        service.saveUser("user");
        // assert
    }

}
