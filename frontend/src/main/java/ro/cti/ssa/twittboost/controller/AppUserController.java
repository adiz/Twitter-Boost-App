package ro.cti.ssa.twittboost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.cti.ssa.twittboost.framework.IAppUserService;
import ro.cti.ssa.twittboost.model.AppUser;

/**
 * @author adrian.zamfirescu
 * @since 08/12/2014
 */
@Controller
@RequestMapping("users")
public class AppUserController {

    @Autowired
    private IAppUserService appUserService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public AppUser getTestAppUser(){

        return appUserService.getTestUser();

    }

}
