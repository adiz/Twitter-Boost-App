package ro.cti.ssa.twittboost.dto;

/**
 * Created by livia on 1/22/2015.
 */
public class SaveFilterGroupForm extends SearchForm {

    private String user;

    private String filterGroupDescription;

    public String getFilterGroupDescription() {
        return filterGroupDescription;
    }

    public void setFilterGroupDescription(String filterGroupDescription) {
        this.filterGroupDescription = filterGroupDescription;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
