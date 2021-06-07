package com.pluralsight.conference;

public class Version implements Comparable<Version> {

    private String version;

    public final String get() {
        return this.version;
    }

    Version(String version) {
        if(version == null)
            throw new IllegalArgumentException("'version' must not be null!");
        //if(!version.matches("[0-9]+(\\.[0-9]+)*"))
        if(!version.matches("\\d+(\\.\\d+){0,2}(-SNAPSHOT)?"))
            throw new IllegalArgumentException("'version' must match: 'major.minor.patch(-SNAPSHOT)'!");
        this.version = version;
    }

    boolean isSnapshot() {
        return this.version.endsWith("-SNAPSHOT");
    }

    @Override
    public int compareTo(Version that) {
        if(that == null)
            throw new IllegalArgumentException("'other' must not be null!");
        String[] thisParts = this.get().split("\\.");
        String[] thatParts = that.get().split("\\.");
        int length = Math.max(thisParts.length, thatParts.length);
        for(int i = 0; i < length; i++) {
            int thisPart = i < thisParts.length ?
                    Integer.parseInt(thisParts[i]) : 0;
            int thatPart = i < thatParts.length ?
                    Integer.parseInt(thatParts[i]) : 0;
            if(thisPart < thatPart)
                return -1;
            if(thisPart > thatPart)
                return 1;
        }
        return 0;
    }

    @Override public boolean equals(Object that) {
        if(this == that)
            return true;
        if(that == null)
            return false;
        if(this.getClass() != that.getClass())
            return false;
        return this.compareTo((Version) that) == 0;
    }
}
