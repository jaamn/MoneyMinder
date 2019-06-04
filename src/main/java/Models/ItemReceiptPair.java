package Models;

public class ItemReceiptPair {

    Item item;
    Receipt receipt;

    public ItemReceiptPair(Item item, Receipt receipt)
    {
        this.item = item;
        this.receipt = receipt;
    }

    public Item getItem() {
        return item;
    }

    public Receipt getReceipt() {
        return receipt;
    }
}
