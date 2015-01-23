package ro.cti.ssa.twittboost.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
@Entity
@Table(name = "APPUSER")
public class AppUser extends BaseEntity{

    private String username;
    private String password;
    private String role;

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

    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
