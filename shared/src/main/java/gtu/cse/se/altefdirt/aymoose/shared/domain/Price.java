package gtu.cse.se.altefdirt.aymoose.shared.domain;

public record Price(
    int amount
) implements SingleValueObject<Integer> {

    public Price(int amount) {
        this.amount = amount;
    }

    @Override
    public Integer value() {
        return amount;
    }
}