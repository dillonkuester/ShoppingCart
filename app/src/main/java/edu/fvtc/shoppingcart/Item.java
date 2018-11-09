package edu.fvtc.shoppingcart;

public class Item {
    String name;
    boolean isChecked = false;

    public Item(String name)
    {
        this.name = name;
    }

    public Item(String name, boolean ischecked)
    {
        this.name = name;
        this.isChecked = ischecked;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
