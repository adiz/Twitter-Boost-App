package ro.cti.ssa.twittboost.dto;

import java.util.List;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
public class SearchForm {

    private String username;
    private List<String> filters;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }
}
