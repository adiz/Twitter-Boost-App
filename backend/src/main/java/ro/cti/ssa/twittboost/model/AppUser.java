package ro.cti.ssa.twittboost.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Entity
@Table(name = "APPUSER")
public class AppUser extends BaseEntity {

    private String username;
    private String password;
    @JsonIgnore
    private Set<FilterGroupPreference> savedPreferences = new HashSet<FilterGroupPreference>();

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Set<FilterGroupPreference> getSavedPreferences() {
        return savedPreferences;
    }

    public void setSavedPreferences(Set<FilterGroupPreference> savedPreferences) {
        this.savedPreferences = savedPreferences;
    }
}
