package gtu.cse.se.altefdirt.aymoose.shared.domain;

public record Price(
    int amount
) {

    public Price(int amount) {
        this.amount = amount;
    }
}