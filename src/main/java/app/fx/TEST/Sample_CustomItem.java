package app.fx.TEST;

public class Sample_CustomItem {
    private String name;
    private int value;

    public Sample_CustomItem(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + " (" + value + ")";
    }
}
