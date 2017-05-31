package kcci.surfaceviewtest;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameView extends SurfaceView
                            implements SurfaceHolder.Callback
{
    private GameThread gameThread = null;

    public GameView(Context context)
    {
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        gameThread = new GameThread(holder, getResources());
        gameThread.on();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        gameThread.onSizeChanged(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        gameThread.off();
    }
}
