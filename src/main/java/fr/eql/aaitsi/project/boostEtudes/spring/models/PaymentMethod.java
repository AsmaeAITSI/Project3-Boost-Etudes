package fr.eql.aaitsi.project.boostEtudes.spring.models;



public enum PaymentMethod {
    CHEQUES("Chèques"),
    CASH("Espèces"),
    TRANSFER("Virement"),
    ;

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

