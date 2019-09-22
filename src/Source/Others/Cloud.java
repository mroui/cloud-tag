package Source.Others;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.apache.commons.io.IOUtils;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Cloud {

    private int tagsCount, minLengthTag, paddingTags, fontSizeFrom, fontSizeTo;
    private Color color1, color2, color3, color4, color5;
    private Image image;
    private String imageName;
    private java.awt.Color hexColor1, hexColor2, hexColor3, hexColor4, hexColor5;
    private String path;

//====================================================

    public Cloud() {}

    public Cloud(int tagsCount, int minLengthTag, int paddingTags, int fontSizeFrom, int fontSizeTo,
                 Color c1, Color c2, Color c3, Color c4, Color c5, Image image) {
        this.tagsCount = tagsCount;
        this.minLengthTag = minLengthTag;
        this.paddingTags = paddingTags;
        this.fontSizeFrom = fontSizeFrom;
        this.fontSizeTo = fontSizeTo;
        this.color1 = c1;
        this.color2 = c2;
        this.color3 = c3;
        this.color4 = c4;
        this.color5 = c5;
        this.image = image;
    }


    public void setImage (Image image) {
        this.image = image;
    }


    public void setImageName(String name) {
        this.imageName = name;
    }


    public String getImageName() {
        return this.imageName;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public void create() {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(this.tagsCount);
        frequencyAnalyzer.setMinWordLength(this.minLengthTag);
        frequencyAnalyzer.setStopWords(loadStopWords());

        final java.util.List<WordFrequency> wordFrequencies;
        try {
            wordFrequencies = frequencyAnalyzer.load(getInputStream("Assets/TextFiles/tags.txt"));
            final Dimension dimension = new Dimension((int)this.image.getWidth(), (int)this.image.getHeight());
            final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
            wordCloud.setPadding(this.paddingTags);
            setHexColors();
            wordCloud.setBackground(new PixelBoundryBackground(this.path));
            wordCloud.setColorPalette(new ColorPalette(this.hexColor1, this.hexColor2, this.hexColor3, this.hexColor4, this.hexColor5));
            wordCloud.setFontScalar(new LinearFontScalar(this.fontSizeFrom, this.fontSizeTo));
            wordCloud.build(wordFrequencies);
            wordCloud.writeToFile("src/Output/"+this.imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setHexColors() {
        java.awt.Color awtColor = new java.awt.Color((float) this.color1.getRed(), (float) this.color1.getGreen(),
                                                     (float) this.color1.getBlue(),(float) this.color1.getOpacity());
        this.hexColor1 = java.awt.Color.decode(String.format("#%02X%02X%02X", awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue()));

        awtColor = new java.awt.Color((float) this.color2.getRed(), (float) this.color2.getGreen(),
                (float) this.color2.getBlue(),(float) this.color2.getOpacity());
        this.hexColor2 = java.awt.Color.decode(String.format("#%02X%02X%02X", awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue()));

        awtColor = new java.awt.Color((float) this.color3.getRed(), (float) this.color3.getGreen(),
                (float) this.color3.getBlue(),(float) this.color3.getOpacity());
        this.hexColor3 = java.awt.Color.decode(String.format("#%02X%02X%02X", awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue()));

        awtColor = new java.awt.Color((float) this.color4.getRed(), (float) this.color4.getGreen(),
                (float) this.color4.getBlue(),(float) this.color4.getOpacity());
        this.hexColor4 = java.awt.Color.decode(String.format("#%02X%02X%02X", awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue()));

        awtColor = new java.awt.Color((float) this.color5.getRed(), (float) this.color5.getGreen(),
                (float) this.color5.getBlue(),(float) this.color5.getOpacity());
        this.hexColor5 = java.awt.Color.decode(String.format("#%02X%02X%02X", awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue()));
    }


    private static Set<String> loadStopWords() {
        try {
            final List<String> lines = IOUtils.readLines(getInputStream("src/Assets/stop_words.txt"));
            return new HashSet<>(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }


    private static InputStream getInputStream(final String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
}
