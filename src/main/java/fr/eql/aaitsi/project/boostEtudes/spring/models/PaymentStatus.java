package fr.eql.aaitsi.project.boostEtudes.spring.models;

public enum PaymentStatus {
    PENDING("En attente"),
    PAID("Payé"),
    CANCELED("Annulé"),
    ;

    private final String displayName;

    PaymentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

