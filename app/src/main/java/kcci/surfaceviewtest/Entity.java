package kcci.surfaceviewtest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

class Entity
{
    private Point position  = new Point(0, 0);
    private Point speed     = new Point(0, 0);
    private Point size      = new Point(0, 0);
    private Bitmap image  = null;

    private Rect rectSrc    = new Rect();
    private Rect rectDst    = new Rect();
    private Paint paint     = new Paint();

    public Entity(int alpha)
    {
        // 그림 투명도 50%
        paint.setAlpha(alpha);
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public Point getSpeed()
    {
        return speed;
    }

    public void setSpeed(Point speed)
    {
        this.speed = speed;
    }

    public Point getSize()
    {
        return size;
    }

    public void setSize(Point size)
    {
        this.size = size;
    }

    public Bitmap getImage()
    {
        return image;
    }

    public void setImage(Bitmap image)
    {
        this.image = image;
    }

    public void setPosition(int x, int y)
    {
        this.position.set(x, y);
    }

    public void setSize(int width, int height)
    {
        this.size.set(width, height);
    }

    public void draw(Canvas canvas)
    {
        // 그림 원본에서 전체 가져오기
        rectSrc.set(0, 0, image.getWidth(), image.getHeight());

        // 그림을 그릴 영역
        rectDst.set(position.x, position.y,
                position.x+size.x, position.y+size.y);

        if(canvas!=null)
            canvas.drawBitmap(image, rectSrc, rectDst, paint);
    }

    public void setSpeed(int x, int y)
    {
        this.speed.set(x,y);
    }

    public void move(Rect rectFrame)
    {
//        if (!rectFrame.contains(getRect()))
//            return;
        position.x += speed.x;
        position.y += speed.y;

        if(position.x <0 || position.x>rectFrame.right-size.x)
            speed.x*=-1;
        if(position.y <0 || position.y>rectFrame.bottom-size.y)
            speed.y*=-1;
    }

    public void setImage(Resources res, int bg)
    {
        Bitmap bitmap = BitmapFactory.
                decodeResource(res, bg);
        setImage(bitmap);
    }

    public Rect getRect()
    {
        rectDst.set(position.x, position.y,
                position.x + size.x, position.y + size.y);

        return rectDst;
    }
}













