package Source.websiteTags;


import java.io.*;


class SaveTagsToFile {

    private String textTags;

//====================================================

    SaveTagsToFile(String textTags) {
        this.textTags = textTags;
    }


    void save() {
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
