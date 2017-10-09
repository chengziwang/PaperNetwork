import java.io.File;

import static Config.PathConfig.filePath;

/**
 * Created by lizhaofu on 2017/6/19.
 */
public class Main {
    public static void main(String[] args) {
        File files = new File(filePath);
        if (!files.exists())
            files.mkdirs();
        for (File file : files.listFiles())
            file.delete();
        //czwddataprocess
        DataProcess dataProcess = new DataProcess();
        dataProcess.readRuleFile();
        //czwdataprocess
        Papers papers = new Papers();

        papers.run();
    }
}
