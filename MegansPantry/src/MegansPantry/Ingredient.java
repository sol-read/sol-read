package MegansPantry;

public class Ingredient {

    private String name;
    private int amount;
    private String type;

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setAmount(int amount) { this.amount = amount; }
    public int getAmount() { return amount; }

    public void setType(String type) { this.type = type; }
    public String getType() { return type; }

    public Ingredient(String name, int amount, String type) {
        this.name = name;
        this.amount = amount;
        this.type = type;
    }
}
