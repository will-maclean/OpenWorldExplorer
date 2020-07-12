package wmaclean.time;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// todo - set up tinting
public enum TimeOfDay {

    Sunny{
        @Override
        public BufferedImage tintImage(BufferedImage input) {
            return input;
        }
    },
    Cloudy{
        @Override
        public BufferedImage tintImage(BufferedImage input) {
            return TimeOfDay.tint(input, 0.9f, 0.9f, 1f, 1f);
        }
    },
    DuskDawn{
        @Override
        public BufferedImage tintImage(BufferedImage image) {
            return TimeOfDay.tint(image, 1f, 0.8f, 0.95f, 1f);
        }
    },
    Moonlight{
        @Override
        public BufferedImage tintImage(BufferedImage image) {
            return TimeOfDay.tint(image, 0.5f, 0.6f, 1f, 1f);
        }
    },
    Dark{
        @Override
        public BufferedImage tintImage(BufferedImage input) {
            return TimeOfDay.tint(input, 0.5f, 0.6f, 0.7f, 1f);
        }
    };

    public abstract BufferedImage tintImage(BufferedImage input);

    private static BufferedImage tint(BufferedImage image, float r, float g, float b, float a)
    {
        BufferedImage tintedSprite = new BufferedImage(image.getWidth(), image.
                getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = tintedSprite.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();

        for (int i = 0; i < tintedSprite.getWidth(); i++)
        {
            for (int j = 0; j < tintedSprite.getHeight(); j++)
            {
                int ax = tintedSprite.getColorModel().getAlpha(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int rx = tintedSprite.getColorModel().getRed(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int gx = tintedSprite.getColorModel().getGreen(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int bx = tintedSprite.getColorModel().getBlue(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                ax *= a;
                tintedSprite.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
            }
        }
        return tintedSprite;
    }
}
