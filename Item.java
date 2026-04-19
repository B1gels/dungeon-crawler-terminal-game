public class Item {
    int value;
    ItemType type;
    String name;

    public enum ItemType {
        POTION,
        ELIXIR,
        SWORD
    }

    public Item(ItemType type, int value) {
        this.type = type;
        this.value = value;

        switch (type) {
            case POTION:
                this.name = "Potion";
                break;
            case ELIXIR:
                this.name = "Elixir";
                break;
            case SWORD:
                this.name = "Sword";
                break;
        }
    }

}
