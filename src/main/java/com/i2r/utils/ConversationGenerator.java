package com.i2r.utils;

import com.i2r.object.Dialogue;
import com.i2r.object.Turn;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * transcriptprocessing
 * com.i2r.utils
 * Created by Zhang Chen
 * 6/23/2018
 */

@Slf4j
public class ConversationGenerator {

    public static void writeConversationToFile(List<Dialogue> dialogueList, String path) {
        path = path.substring(0, path.lastIndexOf("."));
        File file = new File(path);
        if(!file.exists()) {
            file.mkdir();
        }
        log.info("\nWrite Files to folder: {} ", path);
        try {
            for (Dialogue dialogue : dialogueList) {
                log.info("\nGenerate File: {} \n", dialogue.getId());
                String dialoguePath = path + "/" + dialogue.getId() + ".txt";
                File dialogueFile = new File(dialoguePath);
                if (!dialogueFile.exists()) {
                    dialogueFile.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(dialogueFile);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                for(int i = 0; i < dialogue.getTurns().size(); i++) {
                    String line = null;
                    if(i != dialogue.getTurns().size() - 1) {
                        line = dialogue.getTurns().get(i).getSpeaker() + ":" + dialogue.getTurns().get(i).getUtterance() + "\n";
                    }
                    else {
                        line = dialogue.getTurns().get(i).getSpeaker() + ":" + dialogue.getTurns().get(i).getUtterance();
                    }
                    log.info("Writing Line: {} ", line);
                    bw.write(line);
                }
                bw.close();
                log.info("\nDone Generating File: {} ", dialogue.getId());
            }
        }
        catch(IOException e) {
            log.error("",e);
        }
    }



}
