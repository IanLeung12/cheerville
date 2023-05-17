import java.awt.*;

public class Nuke extends Tile{

    private final int maxAge;

    Nuke(int x, int y, int maxAge) {
        super(x, y);
        this.maxAge = maxAge;
    }


    public int getMaxAge() {
        return maxAge;
    }

    public Color getColor() {
        float h = 0.03f + 0.15f * ((float) this.getAge() / maxAge);
        return Color.getHSBColor(h, 0.75f, 0.75f);
    }
}
