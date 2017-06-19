package main.java.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// file_moduler, Global_s_idx, WSD_position, WSD_text, WSD_type, WSD_scode

/**
 * Created by Yoosung on 2017-06-15.
 */
public class Text_Parser {

    public void getText(String filename, String filename2) throws Exception {

        korean_analyzer ka = new korean_analyzer();
        JSONParser parser = new JSONParser();

        //String[] file_mod = new String[]{"_mod1", "_mod2", "_mod3", "_mod4", "_mod5", "_mod6", "_mod7", "_mod8", "_mod9", "_mod0"};

        BufferedReader br = null;
        BufferedReader br2 = null;
        BufferedReader br3 = null;
        BufferedWriter bw;
        //BufferedWriter bw1, bw2, bw3, bw4, bw5, bw6, bw7, bw8, bw9, bw0;

        String input = null;
        String wikipage_id = null;
        String global_s_idx = null;
        int moduler = 0;


        String WSD_text = new String();
        String WSD_type = new String();
        String WSD_scode = new String();
        long WSD_position = 0;
        Object WSD_weight;
        String WSD_weight2 = null;
        long count = 0;

        try {
            br = Files.newBufferedReader(Paths.get(filename + ".txt"));
            br2 = Files.newBufferedReader(Paths.get("/Users/Sung9/Documents/Korean_Wordnet/wiki_page_id.txt"));
            br3 = Files.newBufferedReader(Paths.get("/Users/Sung9/Documents/Korean_Wordnet/global_s_idx.txt"));
            bw = new BufferedWriter(new FileWriter(filename+"_WSD.txt"));
            /*
            bw1 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[0] + ".txt"));
            bw2 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[1] + ".txt"));
            bw3 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[2] + ".txt"));
            bw4 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[3] + ".txt"));
            bw5 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[4] + ".txt"));
            bw6 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[5] + ".txt"));
            bw7 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[6] + ".txt"));
            bw8 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[7] + ".txt"));
            bw9 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[8] + ".txt"));
            bw0 = new BufferedWriter(new FileWriter(filename2+"_WSD" + file_mod[9] + ".txt"));
            */

            while ((input = br.readLine()) != null) {

                String output = ka.getResult(input);

                Object obj = parser.parse(output);

                JSONObject sObj = (JSONObject) obj;

                JSONArray sArr = (JSONArray)sObj.get("sentence");

                //System.out.println(sArr);
                wikipage_id = br2.readLine();
                global_s_idx = br3.readLine();
                //moduler = Integer.parseInt(wikipage_id) % 10;

                for (int i=0; i< sArr.size(); i++) {
                    //count ++;
                    //wikipage_id = br2.readLine();
                    //global_s_idx = br3.readLine();
                    JSONObject senObj = (JSONObject) sArr.get(i);
                    JSONArray WSDArr = (JSONArray) senObj.get("WSD");
                    //System.out.println(WSDArr);
                    //System.out.println(WSDArr.size());
                    //System.out.println(i);

                    for (int k = 0 ; k < WSDArr.size()  ; k++){
                        JSONObject wsdOBJ = (JSONObject) WSDArr.get(k);
                        //System.out.println(wsdOBJ);

                        WSD_text = (String) wsdOBJ.get("text");
                        WSD_type = (String) wsdOBJ.get("type");
                        WSD_scode = (String) wsdOBJ.get("scode");
                        WSD_position = (long) wsdOBJ.get("position");
                        WSD_weight = wsdOBJ.get("weight");

                        WSD_weight2 = WSD_weight.toString();

                        //System.out.println(wikipage_id + "\t" + global_s_idx + "\t" +WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_position + "\t" + WSD_weight2);
                        bw.write(wikipage_id + "\t" + global_s_idx + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                        //bw.write(WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                        /*
                        switch (moduler){
                            case(1):
                                bw1.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                            case(2):
                                bw2.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                            case(3):
                                bw3.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                            case(4):
                                bw4.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                            case(5):
                                bw5.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                            case(6):
                                bw6.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                            case(7):
                                bw7.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                            case(8):
                                bw8.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                            case(9):
                                bw9.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                            case(0):
                                bw0.write(wikipage_id + "\t" + count + "\t" + WSD_position + "\t" + WSD_text + "\t" + WSD_type + "\t" + WSD_scode + "\t" + WSD_weight2 + "\n");
                                break;
                        }
                        */
                    }

                }
            }
            /*
            bw1.flush();
            bw2.flush();
            bw3.flush();
            bw4.flush();
            bw5.flush();
            bw6.flush();
            bw7.flush();
            bw8.flush();
            bw9.flush();
            bw0.flush();
            bw1.close();
            bw2.close();
            bw3.close();
            bw4.close();
            bw5.close();
            bw6.close();
            bw7.close();
            bw8.close();
            bw9.close();
            bw0.close();
            */
            bw.flush();
            bw.close();
            br.close();
            System.out.print("done");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}