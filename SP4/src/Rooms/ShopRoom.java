package Rooms;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import Inventory.Item;
import Entitys.Player;

import static processing.core.PConstants.SHAPE;

public class ShopRoom extends Room{

    private Item[] items = new Item[3];

    public ShopRoom(PApplet pApplet) {
        super(pApplet);
        populateShop();
        super.setRoomName("ShopRoom");
    }

    @Override
    public void display()
    {
        super.display();
        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text(getRoomName(), super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
        displayItems();
    }

    private void populateShop(){
        items[0] = new Item(super.getpApplet(), new PVector(9*getScale(), 10*getScale()), "Attack", 20, 1, super.getpApplet().loadImage("Sprites/ItemSprites/Item001.png"));
        items[1] = new Item(super.getpApplet(), new PVector((10*getScale()+10) + 2 * getScale(), 10*getScale()), "Shield", 30, 1, super.getpApplet().loadImage("Sprites/ItemSprites/Item002.png"));
        items[2] = new Item(super.getpApplet(), new PVector((11*getScale()+20) + 4 * getScale(), 10*getScale()), "Hp", 1, 1, super.getpApplet().loadImage("Sprites/ItemSprites/Item003.png"));
    }

    public Item buyItem(Player player, boolean interaction){
        Item itemToGive = null;
        for (int i = 0; i < items.length; i++){
            if (player.getCurrentPvector().y == items[i].getpVector().y && player.getCurrentPvector().x == items[i].getpVector().x && interaction && player.getInventory().getCoins() >= items[i].getCost()){
                itemToGive = items[i];
            }
            else{
                continue;
            }
        }
        return itemToGive;
    }

    public void exchangeItems(Item item, Player player)
    {
        if(item == null)
        {
            return;
        }

        else if(item.getName().equals("Attack"))
        {
            player.setAttack(player.getAttack() + item.getToGive());
            player.getInventory().RemoveCoins(item.getCost());
        }
        else if(item.getName().equals("Shield"))
        {
            player.setDefense(player.getDefense() + item.getToGive());
            player.getInventory().RemoveCoins(item.getCost());

        }
        else if(item.getName().equals("Hp"))
        {
            player.setHP(player.getHP() + item.getToGive());
            player.getInventory().RemoveCoins(item.getCost());
        }
    }

    public void displayItems(){
        for (int i = 0; i < items.length; i++) {
            items[i].display();
        }
    }
}
