package ro.cti.ssa.twittboost.dto;

/**
 * @author adrian.zamfirescu
 * @since 09/12/2014
 */
public enum Filters {

    HASHTAGS(0),
    REFERENCES(1),
    WORDS(2),
    START_DATE(3),
    END_DATE(4),
    USER_NAME(5);

    private int filterIndex;

    Filters(int filterIndex) {
        this.filterIndex = filterIndex;
    }

    public int getFilterIndex() {
        return filterIndex;
    }

    public static Filters getFilterByIndex(int index) {
        Filters[] filtersList = Filters.values();
        /* if (index > filtersList.length){
            throw
        }*/
        return filtersList[index];
    }
}
