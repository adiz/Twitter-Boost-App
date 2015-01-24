package ro.cti.ssa.twittboost.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by livia on 1/21/2015.
 */
@Entity
@Table(name = "FILTER_GROUP_PREFERENCES")
public class FilterGroupPreference extends BaseEntity {

    @JsonIgnore
    private AppUser user;
    private Set<SavedFilter> filters = new HashSet<SavedFilter>();
    private String filterGroupDescription;

    public FilterGroupPreference() {

    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "filterGroup", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<SavedFilter> getFilters() {
        return filters;
    }

    public void setFilters(Set<SavedFilter> filters) {
        this.filters = filters;
    }

    @Column(name = "FILTER_GROUP_DESCRIPTION")
    public String getFilterGroupDescription() {
        return filterGroupDescription;
    }

    public void setFilterGroupDescription(String filterGroupDescription) {
        this.filterGroupDescription = filterGroupDescription;
    }

    @JsonProperty
    @Transient
    public String getViewDescription() {
        StringBuilder viewDescription = new StringBuilder(filterGroupDescription + ":");
        for (SavedFilter filter : filters) {
            viewDescription.append(filter.getFilterCategory() + ": " + filter.getFilterContent()+"\n");
        }

        return viewDescription.toString();
    }
}
