import java.util.HashMap;
import java.io.*;


/*
*
* @author qingyuan
*
* @return Conditional Probability
* */
public class ConditionalProbability {


    //              <类,<词,Pro>>
    private HashMap<String, HashMap<String, Double>> conPro = null;

    //              <类名,<词,Pro>>
    private HashMap<String, HashMap<String, Double>> getConditionalProbability(File trainFile) throws IOException {


        HashMap<String,Double> wordPro = new HashMap<>();
        HashMap<String,Integer> wordCount = new HashMap<>();

        //判断父文件夹是否存在
        if (trainFile.exists()) {
            File classDirs[] = trainFile.listFiles();


            //遍历类文件夹
            for (File tmpclassdir : classDirs) {
                File[] docs = tmpclassdir.listFiles();

                HashMap<String,Integer> classWordCount = new HashMap<>();

                //遍历类内文件
                for (File tmpdoc : docs) {

                    String fileContent = FileUtils.File2String(tmpdoc);
                    String [] file2word = fileContent.split(" ");

                    HashMap<String,Integer> fileWordCount = FileUtils.fileString2WordCountMap(fileContent);


                    System.out.println(tmpclassdir.getName() + "---" + fileContent);
                }
            }
        }

        return conPro;
    }
}