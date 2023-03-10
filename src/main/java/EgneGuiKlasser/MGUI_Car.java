package EgneGuiKlasser;
/**
 * Kopi af Maven:diplomitdtu:matadorgui:3.1.7.gui_fields.GUI_Car som er tilrettet
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import gui_codebehind.Observable;
import gui_codebehind.SwingComponentFactory;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_resources.Attrs;

/**
 *  Class representing the car images on the board. A car will automatically
 *  be created for a {@link GUI_Player#GUI_Player(String)} on construction.<br><br>
 *
 *  Cars won't be placed on the board automatically, but has to be placed using
 *  the {@link GUI_Field#setCar(GUI_Player, boolean)} method on a GUI_Field.
 *  You may move the car around using this method as well.
 *
 *  @author Ronnie
 */
public final class MGUI_Car extends Observable {

    // Enum representing different car types
    public enum Type {
        CAR(0, 15), TRACTOR(1, 11), RACECAR(2, 13), UFO(3, 10);
        private final int x, h;
        private final int width = EgneGuiKlasser.MGUI_Car.WIDTH + 1;
        public final static int size = EgneGuiKlasser.MGUI_Car.Type.values().length;
        Type(int no, int h) {
            this.x = no * this.width;
            this.h = h;
        }
        public int x() {
            return this.x;
        }
        public int h() {
            return this.h;
        }
        public static EgneGuiKlasser.MGUI_Car.Type getTypeFromString(String typeString){
            for(EgneGuiKlasser.MGUI_Car.Type type : EgneGuiKlasser.MGUI_Car.Type.values()){
                if (type.toString().equals(typeString)) return type;
            }
            System.err.println("No such Type - choosing default : CAR");
            return CAR;
        }

    }

    // Enum representing different patterns for the cars
    public enum Pattern {
        FILL, HORIZONTAL_GRADIANT, DIAGONAL_DUAL_COLOR,
        HORIZONTAL_DUAL_COLOR, HORIZONTAL_LINE, CHECKERED, DOTTED, ZEBRA;

        public static EgneGuiKlasser.MGUI_Car.Pattern getPatternFromString(String patternString) {
            for (EgneGuiKlasser.MGUI_Car.Pattern pattern : EgneGuiKlasser.MGUI_Car.Pattern.values()) {
                if (pattern.toString().equals(patternString)) return pattern;
            }
            System.err.println("No such Pattern - choosing default: FILL");
            return EgneGuiKlasser.MGUI_Car.Pattern.FILL;
        }
    }

    private Color primaryColor, secondaryColor;
    private EgneGuiKlasser.MGUI_Car.Type type;
    private EgneGuiKlasser.MGUI_Car.Pattern pattern;
    private BufferedImage image;


    /**
     * Default constructor for GUI_Car. Constructs a regular car, with no pattern
     * and a random color.
     */
    public MGUI_Car(){
        this(null, null, EgneGuiKlasser.MGUI_Car.Type.CAR, EgneGuiKlasser.MGUI_Car.Pattern.FILL);
    }


    /**
     * Constructs a car customized car, including custom colors, pattern and type.
     *
     * @param primaryColor  Primary color of the car
     * @param patternColor  Color of the pattern. If pattern is {@link gui_fields.GUI_Car.Pattern#FILL} then this color has no effect.
     * @param type          Type of car. Either CAR, TRACTOR, UFO or RACECAR.
     * @param pattern       Pattern of the car FILL, DOTTED, HORIZONTOL_GRADIENT etc.
     */
    public MGUI_Car(Color primaryColor, Color patternColor, EgneGuiKlasser.MGUI_Car.Type type, EgneGuiKlasser.MGUI_Car.Pattern pattern){
        this.primaryColor = primaryColor;
        this.secondaryColor = patternColor;
        this.type = type;
        this.pattern = pattern;
        repaint();
    }


    private void repaint(){
        final int X = this.type.x();
        final int Y = 0;

        if (primaryColor == null) {
            primaryColor = secondaryColor == null ? COLORS[(int) (Math.random() * 6)] : secondaryColor;
        }
        if (secondaryColor == null) { secondaryColor = primaryColor; }

        BufferedImage template = new SwingComponentFactory().createImage(PATH).getSubimage(X, Y, WIDTH, HEIGHT);
        switch(this.pattern) {
            case FILL: this.image = paintFill(template, primaryColor); break;
            case HORIZONTAL_GRADIANT: this.image = paintHorizontalGradiant(template, primaryColor, secondaryColor); break;
            case DIAGONAL_DUAL_COLOR: this.image = paintDiagonalDualColor(template, primaryColor, secondaryColor); break;
            case HORIZONTAL_DUAL_COLOR: this.image = paintHorizontalDualColor(template, primaryColor, secondaryColor, this.type); break;
            case HORIZONTAL_LINE: this.image = paintHorizontalLine(template, primaryColor, secondaryColor, this.type); break;
            case CHECKERED: this.image = paintCheckered(template, primaryColor, secondaryColor); break;
            case DOTTED: this.image = paintDotted(template, primaryColor, secondaryColor); break;
            case ZEBRA: this.image = paintZebra(template, primaryColor, secondaryColor); break;
            default: throw new RuntimeException(Attrs.getString("Error.BadArgument.Car.pattern"));
        }
    }

    public BufferedImage getImage() {
        return this.image;
    }
    public Color getPrimaryColor() {
        return this.primaryColor;
    }
    public Color getSecondaryColor() {
        return this.secondaryColor;
    }

    public void setPrimaryColor(Color color){
        this.primaryColor = color;
        repaint();
        notifyObservers();
    }
    public void setSecondaryColor(Color color){
        this.secondaryColor = color;
        repaint();
        notifyObservers();
    }

    // Constants
    private static final int WIDTH = 40;
    private static final int HEIGHT = 21;
    private static final String PATH = Attrs.getImagePath("GUI_Car.Image");
    private static final int PRIMARYCOLORSTANDIN = 0xffff0000;
    private static final Color[] COLORS = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK, Color.WHITE };
    private static final Map<EgneGuiKlasser.MGUI_Car.Pattern, String> patternImages = new HashMap<EgneGuiKlasser.MGUI_Car.Pattern, String>();
    static {
        patternImages.put(EgneGuiKlasser.MGUI_Car.Pattern.DIAGONAL_DUAL_COLOR, Attrs.getImagePath("GUI_Car.Image.Pattern.Diagonal_Dual_Color"));
        patternImages.put(EgneGuiKlasser.MGUI_Car.Pattern.DOTTED, Attrs.getImagePath("GUI_Car.Image.Pattern.Dotted"));
        patternImages.put(EgneGuiKlasser.MGUI_Car.Pattern.CHECKERED, Attrs.getImagePath("GUI_Car.Image.Pattern.Checkered"));
        patternImages.put(EgneGuiKlasser.MGUI_Car.Pattern.ZEBRA, Attrs.getImagePath("GUI_Car.Image.Pattern.Zebra"));
    }



    // Helper methods
    private BufferedImage paintFill(BufferedImage img, Color c1) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    img.setRGB(x, y, c1.getRGB());
                }
            }
        }
        return img;
    }
    private BufferedImage paintHorizontalGradiant(BufferedImage img,
                                                  Color c1, Color c2) {
        int r1 = c1.getRed();
        int r2 = c2.getRed();
        int g1 = c1.getGreen();
        int g2 = c2.getGreen();
        int b1 = c1.getBlue();
        int b2 = c2.getBlue();

        int min = img.getWidth();
        int max = 0;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    if (x < min) {
                        min = x;
                    }
                    if (x > max) {
                        max = x;
                    }
                }
            }
        }
        double width = max - min;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == 0xffff0000) {
                    double p = (x - min) / width;
                    int r = (int) ((p * (r2 - r1)) + r1);
                    int g = (int) ((p * (g2 - g1)) + g1);
                    int b = (int) ((p * (b2 - b1)) + b1);
                    int rgb = 0xFF000000 + (r << 16) + (g << 8) + b;
                    img.setRGB(x, y, rgb);
                }
            }
        }
        return img;
    }
    private BufferedImage paintDiagonalDualColor(BufferedImage img,
                                                 Color c1, Color c2) {
        String path = patternImages.get(EgneGuiKlasser.MGUI_Car.Pattern.DIAGONAL_DUAL_COLOR);
        BufferedImage patternImg =
                new SwingComponentFactory().createImage(path);
        return paintPattern(patternImg, img, c1, c2);
    }
    private BufferedImage paintHorizontalDualColor(BufferedImage img, Color c1, Color c2,
                                                   EgneGuiKlasser.MGUI_Car.Type t) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    int y1 = t.h();
                    if (y < y1) {
                        img.setRGB(x, y, c1.getRGB());
                    } else {
                        img.setRGB(x, y, c2.getRGB());
                    }
                }
            }
        }
        return img;
    }
    private BufferedImage paintHorizontalLine(BufferedImage img, Color c1,
                                              Color c2, EgneGuiKlasser.MGUI_Car.Type t) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    int y1 = t.h();
                    if (y == y1 || y == y1 + 1) {
                        img.setRGB(x, y, c2.getRGB());
                    } else {
                        img.setRGB(x, y, c1.getRGB());
                    }
                }
            }
        }
        return img;
    }
    private BufferedImage paintCheckered(BufferedImage img, Color c1, Color c2) {
        String path = patternImages.get(EgneGuiKlasser.MGUI_Car.Pattern.CHECKERED);
        BufferedImage patternImg =
                new SwingComponentFactory().createImage(path);
        return paintPattern(patternImg, img, c1, c2);
    }
    private BufferedImage paintDotted(BufferedImage img, Color c1, Color c2) {
        String path = patternImages.get(EgneGuiKlasser.MGUI_Car.Pattern.DOTTED);
        BufferedImage patternImg =
                new SwingComponentFactory().createImage(path);
        return paintPattern(patternImg, img, c1, c2);
    }
    private BufferedImage paintZebra(BufferedImage img, Color c1, Color c2) {
        String path = patternImages.get(EgneGuiKlasser.MGUI_Car.Pattern.ZEBRA);
        BufferedImage patternImg =
                new SwingComponentFactory().createImage(path);
        return paintPattern(patternImg, img, c1, c2);
    }
    private BufferedImage paintPattern(BufferedImage patternImg, BufferedImage img, Color c1, Color c2) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                    if (patternImg.getRGB(x, y) == PRIMARYCOLORSTANDIN) {
                        img.setRGB(x, y, c1.getRGB());
                    } else {
                        img.setRGB(x, y, c2.getRGB());
                    }
                }
            }
        }
        return img;
    }
    @Override
    public String toString() {
        return "GUI_Car [primaryColor=" + primaryColor + ", secondaryColor="
                + secondaryColor + ", type=" + type + ", pattern=" + pattern
                + ", image=" + image + "]";
    }


}

