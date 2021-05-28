package thelameres.todolist.core.data.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class PersonTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testPerson() {
    }
}