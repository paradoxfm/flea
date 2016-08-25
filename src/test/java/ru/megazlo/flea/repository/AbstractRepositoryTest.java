package ru.megazlo.flea.repository;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author paradoxfm - 17.02.2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaContext.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractRepositoryTest {

}
