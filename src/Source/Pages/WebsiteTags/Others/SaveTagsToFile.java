package Source.Pages.WebsiteTags.Others;

import java.io.*;


public class SaveTagsToFile {

    private String textTags;

//====================================================

    public SaveTagsToFile(String textTags) {
        this.textTags = textTags;
    }


    public void save() {
        try {
            File file = new File("src/Assets/TextFiles/tags.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(this.textTags);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
