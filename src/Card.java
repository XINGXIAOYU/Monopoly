/**
 * Created by xingxiaoyu on 16/6/2.
 */
public abstract class Card {
    private int quality;
    private String name;
    private boolean isUsed;
    private int id;

    public Card(int id, String name, int quality, boolean isUsed) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.isUsed = isUsed;

    }

    public Card() {

    }

    public void addQuality() {
        this.quality++;
    }

    public void minusQuality() {
        this.quality--;
    }

    public int getQuality() {
        return quality;
    }

    public boolean getUsed() {
        return isUsed;
    }

    public void setUsed(boolean x){
        isUsed = x;
    }
    public int getID() {
        return id;
    }

    public String getName(){
        return name;
    }

    abstract public void use();
}

