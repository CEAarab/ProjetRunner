import javafx.geometry.Rectangle2D;

public class Hero extends AnimatedThing {
    public Hero(double x, double y, double height, double width, int alltitude, int maxIndex ) {
        super(x, y, height, width, alltitude, "file:C:\\Users\\Dell_Latitude_3510\\Downloads\\Ressources audio et image pour le runner-20231107\\img\\heros.png", 0, 100,maxIndex , 85, 100, 22);
    }

    @Override
    public Rectangle2D createViewport(int index, double altitude, int maxIndex) {
        double startX;
        double startY ;
        if (altitude == 0)
        {
            startX = index * windowSizeX;
            startY = altitude ;
        }
        else if ( altitude == 1) {
            startX = index * windowSizeX;
            startY = (altitude+0.2) * (windowSizeY - 20) + 65;
        }
        else if (altitude == 2)
        {
            startX = index * windowSizeX - 7;
            startY = (altitude+0.3) * (windowSizeY - 20) + 145;
        }
        else
        {
            startX = index * windowSizeX - 7;
            startY = (altitude) * (windowSizeY - 25) + 100;
        }
        return new Rectangle2D(startX, startY, windowSizeX, windowSizeY);
    }


}