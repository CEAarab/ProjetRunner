import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.sql.Time;


class GameScene extends Scene {
    private Camera camera;
    private StaticThing lb;
    private StaticThing rb;
    private int numberOfLives;
    private Hero hero;

    public GameScene(Camera camera) {
        super(new Group(), 800, 600);

        this.camera = camera;
        this.numberOfLives = 3;
        Image mapImage = new Image("file:C:\\Users\\Dell_Latitude_3510\\Downloads\\Ressources audio et image pour le runner-20231107\\img\\desert.png");

        ImageView mapView = new ImageView(mapImage);
        mapView.setFitWidth(800);
        mapView.setFitHeight(600);

        ((Group) this.getRoot()).getChildren().add(mapView);

        lb = new StaticThing(20, 20, "C:\\Users\\Dell_Latitude_3510\\Downloads\\Ressources audio et image pour le runner-20231107\\img\\score.png");
        rb = new StaticThing(20, 20, "C:\\Users\\Dell_Latitude_3510\\Downloads\\Ressources audio et image pour le runner-20231107\\img\\nbre.png");

        lb.getImageView().setTranslateX(0);
        rb.getImageView().setTranslateX(600);

        ((Group) this.getRoot()).getChildren().addAll(lb.getImageView(), rb.getImageView());

        double totalWidth = numberOfLives * 30;
        double startX = 240;

        for (int i = 0; i < numberOfLives; i++) {
            StaticThing heart = new StaticThing(20, 20, "C:\\Users\\Dell_Latitude_3510\\Downloads\\Ressources audio et image pour le runner-20231107\\img\\heart.jpg");
            heart.getImageView().setTranslateX(startX + i * 30);
            heart.getImageView().setTranslateY(10);
            ((Group) this.getRoot()).getChildren().add(heart.getImageView());
        }

        hero = new Hero(115, 415, "file:C:\\Users\\Dell_Latitude_3510\\Downloads\\Ressources audio et image pour le runner-20231107\\img\\heros.png", "run");

        ((Group) this.getRoot()).getChildren().add(hero.getSprite());

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time) {
                hero.update();
                GameScene.update(camera);
                //camera update needed !
            }
        };

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200),event -> render()));
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(200),event -> update(camera)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
        timer.start();
    }

    public static void update(Camera camera) {
        camera.setX(camera.getX() + 1);

    }

    public Camera getGameCamera() {
        return camera;
    }

    public void render() {
        double cameraX = camera.getX();
        double width = lb.getImageView().getFitWidth();
        double height = lb.getImageView().getFitHeight();
        double lbX = - cameraX % width;
        double rbX = width + lbX;
        lb.getImageView().setX(lbX);
        lb.getImageView().setY(0);
        rb.getImageView().setX(rbX);
        rb.getImageView().setY(0);

    }

}