package ru.l2.gameserver.model;

public class RecipeComponent {
    private final int _itemId;
    private final int _quantity;

    public RecipeComponent(final int itemId, final int quantity) {
        _itemId = itemId;
        _quantity = quantity;
    }

    public int getItemId() {
        return _itemId;
    }

    public int getQuantity() {
        return _quantity;
    }
}
