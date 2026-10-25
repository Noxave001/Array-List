/**
 * A record of annual water data from various countries
 *
 * @author Ace Breaux
 * @version 4-29-2025
 *
 * @param isoYear year data was taken
 * @param basicPlusPct basic water data
 * @param limitedPct limited water data
 * @param unimprovedPct unimproved water data
 * @param surfacePct surface water data
 */
public record YearlyWaterRecord(String isoYear,
                                double basicPlusPct,
                                double limitedPct,
                                double unimprovedPct,
                                double surfacePct) implements Comparable<YearlyWaterRecord>{

    /**
     * Compares the years of data sampled from two records
     *
     * @param other the record to be compared
     * @return 0 if their isoYears are equal;
     * 1 if the other isoYear is greater; -1 if the other isoYear is less
     */
    @Override
    public int compareTo(YearlyWaterRecord other) {
        return this.isoYear().compareTo(other.isoYear());
    }

    /**
     * Checks if two records are equal based on the year their data was taken
     *
     * @param other the record to be compared
     * @return true, if their isoYears are the same; false, if not
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof YearlyWaterRecord)) {
            return false;
        }

        return compareTo((YearlyWaterRecord)other) == 0;
    }
}
