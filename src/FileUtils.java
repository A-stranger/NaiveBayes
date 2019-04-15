import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

    private String fileContent = "";
    private static HashMap<String,Integer> wordCountMap = new HashMap<>();
    private static HashMap<String,Double> wordFreqMap = new HashMap<>();
    private static HashMap<String,HashMap<String,Integer>> classWordCountMap = new HashMap<>();
    private static HashMap<String,HashMap<String,Double>> classWordFreqMap = new HashMap<>();

    @Test
    public void mytest() throws IOException {

        File file = new File("E:\\NBCorpus\\NBCorpus\\Country");
        Dir2WordCountMap(file);
    }

    /*
    * @param Parent Directory
    * @return
    * */
    public HashMap<String,HashMap<String,Integer>>  Dir2WordCountMap(File file) throws  IOException{


        HashMap<String,HashMap<String,Integer>> classCountMap = new HashMap<>();
        //判断父文件夹是否存在
        if (file.exists()){
            File classDirs []= file.listFiles();

            String className ="" ;
            //遍历类文件夹
            for (File tmpclassdir: classDirs){
                className = tmpclassdir.getName();
                File [] docs = tmpclassdir.listFiles();


                HashMap<String,Integer> tmpClassWordCount = new HashMap<>();
                //遍历类内文件
                for (File tmpdoc: docs){

                    String fileContent = File2String(tmpdoc);
                    String [] tmpFile2Array = fileContent.split(" ");
                    for(String tmpArray: tmpFile2Array){

                       if(!tmpClassWordCount.containsKey(tmpArray)){
                           tmpClassWordCount.put(tmpArray,1);
                       } else {
                           int sum = tmpClassWordCount.get(tmpArray);
                           tmpClassWordCount.put(tmpArray,sum+1);
                       }

                    }

                    System.out.println(tmpclassdir.getName()+"---"+fileContent);
                }

                classCountMap.put(className,tmpClassWordCount);
            }

        }
        return  classCountMap;
    }

    /*
    * @param file 文件名
    * @return file content
    * */
    public static String File2String(File file) throws IOException {

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String outString="";
        String line="";

        while((line = br.readLine())!=null){
            //outString+=line+"\n";

            outString+=line+" ";
        }

        return outString;
    }

    /*
    *
    * 文本字符串String转为<word,count>
    * @param File Content String
    * @return HashMap<String,Integer>
    *
    * */
    public static HashMap<String,Integer> fileString2WordCountMap(String fileContent){

        String [] string2Array = fileContent.split(" ");
        for (String tmp : string2Array){

            if (!wordCountMap.containsKey(tmp)){
                wordCountMap.put(tmp,1);
            }else {
                int sum = wordCountMap.get(tmp)+1;
                wordCountMap.put(tmp,sum);
            }
        }

        return  wordCountMap;
    }

    /*
    * <word,count> 转为 <word,Freq>
    * @param HashMap<String,Integer> wordCountMap:每个词出现的次数
    * @return HashMap<String,Integer> wordFreqMap:每个词出现的频率
    * */
    public static HashMap<String,Double> wordCount2wordFreq(HashMap<String,Integer> wordCountMap){

        double sum =0;

        //总词数
        Collection<Integer> valueCollecton = wordCountMap.values();
        for(Integer tmpCount:valueCollecton){
            sum+=tmpCount;
        }

        //词的频率=词的个数/总词数
        for(Map.Entry<String,Integer> tmpEntry : wordCountMap.entrySet()){
           wordFreqMap.put(tmpEntry.getKey(),tmpEntry.getValue()/sum);
        }

        return wordFreqMap;
    }

    /*
    * dir2Map
    * HashMap<class,HashMap<word,count>>
    * */
    public static HashMap<String,HashMap<String,Integer>> dir2Map(File file) throws IOException{




        return null;
    }

}

