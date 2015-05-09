package co.naughtyspirit.spaceshipcommander.entities;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public interface ShipCollisionListener {
    void onCollisionWithObstacle();

    void onCollisionWithPlanet();

    void onCollisionWithBoard();
}
