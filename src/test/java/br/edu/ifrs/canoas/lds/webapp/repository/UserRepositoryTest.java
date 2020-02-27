package br.edu.ifrs.canoas.lds.webapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ifrs.canoas.lds.webapp.domain.User;
import br.edu.ifrs.canoas.lds.webapp.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repository;

    private final String TEST_STRING = "Test String";

    @Test
    public void when_FindByThemeContaining_then_ReturnTermPaper(){

        // given
        User user = new User();
        user.setUsername(TEST_STRING);
        user.setName(TEST_STRING);
        user.setExperience(TEST_STRING);
        user.setSkill(TEST_STRING);
        user.setActive(true);
        user.setEmail("email@email.com");
        entityManager.persist(user);
        entityManager.flush();

        // when
        Optional<User> found = repository.findByUsername(TEST_STRING);

        // then
        assertThat(found.get().getUsername()).isEqualTo(TEST_STRING);
    }

    @Test
    public void given_noData_when_FindByThemeContaining_then_ReturnEmptyList(){

        // given

        // when
        Optional<User> found = repository.findByUsername(TEST_STRING);

        // then
        assertThat(found).isEmpty();
    }

}