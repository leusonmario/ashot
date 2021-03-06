package ru.yandex.qatools.ashot;

import ru.yandex.qatools.ashot.coordinates.Coords;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * @author <a href="pazone@yandex-team.ru">Pavel Zorin</a>
 * @author <a href="eoff@yandex-team.ru">Maksim Mukosey</a>
 */

//todo docs
public class Screenshot implements Serializable {
    private static final long serialVersionUID = 1241241256734156872L;

    private transient BufferedImage image;
    private Set<Coords> ignoredAreas = new HashSet<>();
    private Set<Coords> coordsToCompare;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Screenshot(BufferedImage image) {
        this.image = image;
        this.coordsToCompare = new HashSet<>(asList(Coords.ofImage(image)));
    }

    public Set<Coords> getCoordsToCompare() {
        return coordsToCompare;
    }

    public void setCoordsToCompare(Set<Coords> coordsToCompare) {
        this.coordsToCompare = coordsToCompare;
    }

    public Set<Coords> getIgnoredAreas() {
        return ignoredAreas;
    }

    public void setIgnoredAreas(Set<Coords> ignoredAreas) {
        this.ignoredAreas = ignoredAreas;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(image, "png", out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image = ImageIO.read(in);
    }
}
