package com.i2r.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.i2r.object.Dialogue;
import com.i2r.object.Turn;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * transcriptprocessing
 * com.i2r.utils
 * Created by Zhang Chen
 * 6/23/2018
 */

@Slf4j
public class XmlParser {

    public static List<Dialogue> XmlToDialogue(String path) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File file = new File(path);
        List<Dialogue> dialogueObjList = new ArrayList<>();
        try {
            if (file.exists()) {
                String xml = inputStreamToString(new FileInputStream(file));
                List<String> DialogueStringList = new ArrayList<>();
                int startIndex = 0;
                int endIndex = xml.indexOf("</dialogue>");
                while(endIndex != -1) {
                    String dialogueString = xml.substring(startIndex,  endIndex + 11);
                    DialogueStringList.add(dialogueString);
                    startIndex = endIndex + 11;
                    endIndex = xml.indexOf("</dialogue>", startIndex);
                }

                for(int i = 0; i < DialogueStringList.size(); i++) {
                    String temp = DialogueStringList.get(i);
                    startIndex = temp.indexOf("<turn");
                    endIndex = temp.indexOf("</turn>");
                    List<Turn> turnObjList = new ArrayList<>();
                    while(startIndex != -1) {
                        String turnString = temp.substring(startIndex,  endIndex + 7);
                        turnString = turnString.substring(0, turnString.indexOf("</utterance>") + 12) + "</turn>";
                        turnString = turnString.replace("<name-self>", " ");
                        Turn turn = xmlMapper.readValue(turnString, Turn.class);
                        turnObjList.add(turn);
                        startIndex = temp.indexOf("<turn", endIndex + 7);
                        endIndex = temp.indexOf("</turn>", startIndex);
                    }
                    temp = temp.substring(0, temp.indexOf("</timestamp>") + 12) + "</dialogue>";
                    Dialogue dialogue = xmlMapper.readValue(temp, Dialogue.class);
                    dialogue.setTurns(turnObjList);
                    dialogueObjList.add(dialogue);
                }
            } else {
                throw new NoSuchElementException();
            }
        }
        catch (NoSuchElementException e) {
            log.error("Specified file does not exist with specified file path: {}", path);
        }
        catch (Exception e) {
            log.error("Parsing error: ", e);
        }
        return dialogueObjList;
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
