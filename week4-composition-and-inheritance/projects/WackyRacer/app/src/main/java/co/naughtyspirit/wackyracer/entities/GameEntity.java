package co.naughtyspirit.wackyracer.entities;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import co.naughtyspirit.wackyracer.ui.CanvasDrawable;


/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public abstract class GameEntity implements CanvasDrawable {
    public Position getPosition() {
        return position;
    }

    protected Position position;
    private final Drawable image;

    public GameEntity(Position position, Drawable image) {
        this.position = position;
        this.image = image;
    }

    @Override
    public void onDraw(Canvas canvas, int tileWidth, int tileHeight) {
        int leftPos = (position.column - 1) * tileWidth;
        int topPos = (position.row - 1) * tileHeight;
        int tileX = leftPos + tileWidth / 2;
        int tileY = topPos + tileHeight / 2;
        image.setBounds(tileX - tileWidth / 2 + tileWidth / 6, tileY - tileHeight / 2 + tileHeight / 20, tileX + tileWidth / 2 - tileWidth / 6, tileY + tileHeight / 2 - tileHeight / 20);
        image.draw(canvas);
    }

    public void onTilesMoved() {
    }
}
