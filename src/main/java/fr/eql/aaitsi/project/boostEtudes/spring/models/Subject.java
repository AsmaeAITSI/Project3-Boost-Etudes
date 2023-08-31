package fr.eql.aaitsi.project.boostEtudes.spring.models;





public enum Subject {
    MATH("Mathématiques"),
    PHYSICS("Physique"),
    CHEMISTRY("Chimie"),
    BIOLOGY_GEOLOGY("SVT"),
    FRENCH("Français");

    private final String displayName;

    Subject(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

