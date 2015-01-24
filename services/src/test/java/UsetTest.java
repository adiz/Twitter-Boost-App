//import junit.framework.TestCase;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//import ro.cti.ssa.twittboost.config.DaoConfig;
//import ro.cti.ssa.twittboost.config.MvcConfig4Test;
//import ro.cti.ssa.twittboost.config.ServiceConfig;
//import ro.cti.ssa.twittboost.config.TwitterBoostWebAppInitializer;
//import ro.cti.ssa.twittboost.dao.AppUserRepository;
//import ro.cti.ssa.twittboost.model.AppUser;
//
///**
// * Created by livia on 1/22/2015.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {MvcConfig4Test.class, DaoConfig.class, ServiceConfig.class, TwitterBoostWebAppInitializer.class})
//public class UsetTest {
//
//    @Autowired
//    private AppUserRepository appUserRepository;
//
//    @Test
//    public void testGetTestUserWithPrefs() {
//        AppUser testUser = appUserRepository.findOne(1);
//    }
//
//}
