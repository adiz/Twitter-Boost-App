package ro.cti.ssa.twittboost.dto;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
public enum Filters {

    HASHTAGS(0),
    REFERENCES(1),
    WORDS(2);

    private int filterIndex;

    Filters(int filterIndex){
        this.filterIndex = filterIndex;
    }

    public int getFilterIndex(){
        return filterIndex;
    }

}
