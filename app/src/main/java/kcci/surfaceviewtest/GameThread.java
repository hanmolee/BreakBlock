package kcci.surfaceviewtest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class GameThread extends Thread
{
    private boolean haveToStop = false;
    private SurfaceHolder surfaceHolder = null;

    private Entity ball = null;

    private Entity bg = null;

    private Rect rectFrame = new Rect();


    public GameThread(SurfaceHolder holder, Resources res) {
        this.surfaceHolder = holder;

        ball = new Entity(255);
        ball.setPosition(0,0);//화면 전환해도 공이 다시 시작하지 않기 위해

        Bitmap bitmap = BitmapFactory.
                decodeResource(res, R.drawable.ball);
        bitmap = removeColor(bitmap, Color.WHITE);

        for(int i=0; i<10; i++) {
            ball = new Entity(128);
            ball.setImage(bitmap);
            ball.setSpeed(Random.get(3,10), Random.get(5,15));
        }

        bg = new Entity(255);
        bg.setImage(res, R.drawable.space);
    }

    private Bitmap removeColor(Bitmap bitmap, int color)
    {
        int size = bitmap.getWidth()*bitmap.getHeight();
        int[] array = new int[size];
        bitmap.getPixels(array,0, bitmap.getWidth(),
                0, 0, bitmap.getWidth(), bitmap.getHeight());

        for(int i=0; i<array.length; i++) {
            if(array[i]==color)
                array[i] = Color.TRANSPARENT;
        }

        return Bitmap.createBitmap(array,0, bitmap.getWidth(),
                bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
    }

    public void onSizeChanged(int width, int height) {
        rectFrame.set(0,0, width, height);

        double widthBall = width/20.0;

        //ball.setPosition(0, 0);  화면 전환시 공위치가 다시시작하지 않기 위해
        ball.setSize((int)widthBall, (int)widthBall);

        bg.setPosition(0,0);
        bg.setSize(width, height);
    }

    public void on() {
        this.start();
    }

    public void off() {
        this.haveToStop = true;
    }

    @Override
    public void run()
    {
        super.run();

        while(!haveToStop) {
            // 화면 잠그기
            Canvas canvas = surfaceHolder.lockCanvas();

            // 화면 그리기
            draw(canvas);

            // 화면 풀기
            if(canvas!=null)
                surfaceHolder.unlockCanvasAndPost(canvas);

            // 게임 진행 시키기
            update();
        }

    }

    // 게임 진행 시키기
    private void update()
    {
        ball.move(rectFrame);
    }

    // 화면 그리기
    private void draw(Canvas canvas)
    {
        bg.draw(canvas);
        ball.draw(canvas);
    }
}











