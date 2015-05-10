package co.naughtyspirit.spaceshipcommander.entities;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public interface ShipListener {
    void onCollisionWithObstacle();

    void onLandingOnPlanet();

    void onCollisionWithBoard();

    void onPlanetNotReachedAfterExecutingCommands();

    void onCommandExecuted();
}
