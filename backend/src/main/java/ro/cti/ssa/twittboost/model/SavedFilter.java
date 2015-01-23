package ro.cti.ssa.twittboost.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ro.cti.ssa.twittboost.dto.Filters;

import javax.persistence.*;

/**
 * Created by livia on 1/21/2015.
 */

@Entity
@Table(name = "SAVED_FILTERS")
public class SavedFilter extends BaseEntity {

    @JsonIgnore
    private FilterGroupPreference filterGroup;
    private String filterCategory;
    private String filterContent;

    public SavedFilter() {

    }

    public SavedFilter(String filterCategory, String filterContent) {
        this.filterCategory = filterCategory;
        this.filterContent = filterContent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILTER_GROUP_ID")
    public FilterGroupPreference getFilterGroup() {
        return filterGroup;
    }

    public void setFilterGroup(FilterGroupPreference filterGroup) {
        this.filterGroup = filterGroup;
    }

    @Column(name = "FILTER_CATEGORY")
    public String getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(String filterCategory) {
        this.filterCategory = filterCategory;
    }

    @Column(name = "FILTER_CONTENT")
    public String getFilterContent() {
        return filterContent;
    }

    public void setFilterContent(String filterContent) {
        this.filterContent = filterContent;
    }

    @Transient
    @JsonIgnore
    public boolean isWordsFilter() {
        return filterCategory.equalsIgnoreCase(Filters.WORDS.toString());
    }

    @Transient
    @JsonIgnore
    public boolean isTwitterUserFilter() {
        return filterCategory.equalsIgnoreCase(Filters.USER_NAME.toString());
    }

    @Transient
    @JsonIgnore
    public boolean isHashTagFilter() {
        return filterCategory.equalsIgnoreCase(Filters.HASHTAGS.toString());
    }

    @Transient
    @JsonIgnore
    public boolean isRefferenceFilter() {
        return filterCategory.equalsIgnoreCase(Filters.REFERENCES.toString());
    }
}
