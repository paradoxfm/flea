package com.platifon.mycards.repository;

import com.platifon.mycards.entity.RoleEnum;
import com.platifon.mycards.entity.User;
import com.platifon.mycards.repositories.UserRepository;
import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author paradoxfm - 17.02.2016
 */
public class UserRepositoryTests extends AbstractRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void registrationMinimalFieldsTest() {
        User mUs = createMinimalUser();
        userRepository.save(mUs);
        userRepository.flush();
        User ivUsr = userRepository.findUserByLogin("ivan");
        assertNotNull(ivUsr);
        assertNotNull(ivUsr.getRoles());

        assertNotNull(ivUsr.getCreateDate());
        assertNotNull(ivUsr.getModificationTime());
        assertEquals(mUs.getId(), ivUsr.getId());
    }

    @Test
    public void fullFieldsTest() {
        User mUs = createMinimalUser();
        userRepository.save(mUs);
        userRepository.flush();
        User u = userRepository.findUserByLogin("ivan");
        assertEquals("ivan", u.getLogin());
    }

    @Test(expected = LazyInitializationException.class)
    public void failLazyTest() {
        User mUs = createMinimalUser();
        userRepository.save(mUs);
        userRepository.flush();
        User ivUsr = userRepository.findUserByLogin("ivan");
        assertEquals(0, ivUsr.getRoles().size());
    }

    @Test
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

    @Test(expected = TransactionSystemException.class)
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
