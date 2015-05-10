package co.naughtyspirit.spaceshipcommander.entities;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public interface ShipListener {
    void onFallingInBlackHole();

    void onLandingOnPlanet();

    void onOutsideOfGalaxy();

    void onPlanetNotReachedAfterExecutingCommands();

    void onCommandExecuted();
}
