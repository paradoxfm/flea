package ru.megazlo.flea.repository;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.megazlo.flea.entity.RoleEnum;
import ru.megazlo.flea.entity.User;
import ru.megazlo.flea.repositories.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author paradoxfm - 17.02.2016
 */
@Transactional
public class UserRepositoryTests extends AbstractRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
	@Rollback
    public void registrationMinimalFieldsTest() {
        User mUs = createMinimalUser();
        userRepository.save(mUs);
        userRepository.flush();
        User ivUsr = userRepository.findUserByLogin("ivan");
        assertNotNull(ivUsr);
        assertNull(ivUsr.getRoles());

        assertNotNull(ivUsr.getRegisterDate());
        assertNotNull(ivUsr.getModificationTime());
        assertEquals(mUs.getId(), ivUsr.getId());
    }

    @Test
	@Rollback
    public void fullFieldsTest() {
        User mUs = createMinimalUser();
        userRepository.save(mUs);
        userRepository.flush();
        User u = userRepository.findUserByLogin("ivan");
        assertEquals("ivan", u.getLogin());
    }

    @Test
	@Rollback
    public void failLazyTest() {
        User mUs = createMinimalUser();
        userRepository.save(mUs);
        userRepository.flush();
        User ivUsr = userRepository.findUserByLogin("ivan");
        assertNull(ivUsr.getRoles());
    }

    @Test
	@Rollback
    public void findWithRoleTest() {
        User mUs = createMinimalUser();
        Set<RoleEnum> roles = new HashSet<>(Arrays.asList(RoleEnum.EXTEND_USER, RoleEnum.ORGANISATION));
        mUs.setRoles(roles);
        userRepository.save(mUs);
        userRepository.flush();
        User ivUsr = userRepository.findUserByLoginWithRole("ivan");
        assertNotNull(ivUsr);
        assertEquals(2, ivUsr.getRoles().size());
    }

    @Test//(expected = TransactionSystemException.class)
	@Rollback
    public void failInsertTest() {
        User mUs = createMinimalUser();
        mUs.setEmail("df");
        userRepository.save(mUs);
    }

    private User createMinimalUser() {
        User u = new User();
        u.setLogin("ivan");
        u.setPasswordHash("asdas");
        u.setEmail("qwe@qwe.qwe");
        return u;
    }
}
