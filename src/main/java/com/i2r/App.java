package com.i2r;

import com.i2r.object.Dialogue;
import com.i2r.utils.ConversationGenerator;
import com.i2r.utils.XmlParser;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class App 
{
    public static void main( String[] args )
    {
        // convert video to audio
        if(args.length == 1) {
            if(args[0].endsWith("xml")) {
                String inputPathName = args[0];
                List<Dialogue> dialogueList =  XmlParser.XmlToDialogue(inputPathName);
                ConversationGenerator.writeConversationToFile(dialogueList, inputPathName);
            }
        }
        else {
            log.error("Please pass in input file path as arguments");
        }
    }
}
