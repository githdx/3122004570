import com.hankcs.hanlp.HanLP;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Calculation {
    //获取文本的SimHash值
    public static int[] getSimHash(String txt){
        // 文本长度过短时，结果准确度过低，文本过短错误提示
        if(txt.length()<500) {
            System.out.println("文本过短，请重新选择！");
            return null;
        }
        //1.分词
        List<String> featureWordList= participle(txt);
        //2.hash
        List<String> hashList= Hash(featureWordList);
        //3.加权、合并
        int []weightVectorList=weightedAndMerged(hashList);
        //4.降维
        dimensionalityReduction(weightVectorList);
        return weightVectorList;
    }

    //计算海明距离
    public static int calculateHammingDistance(int[] list1,int[] list2){
        int distance=0;
        for(int i=0;i<list1.length;i++){
            if(list1[i]!=list2[i])distance++;
        }
        return distance;
    }

    //计算论文相似度
    public static double calculateHammingSimilarity(int distance){
        //保留两位小数
        return Math.round( (100-100.0*distance/256)* 100) / 100.0;
    }

    //分词
    private static List<String> participle(String txt){
        return HanLP.extractKeyword(txt, txt.length());
    }

    //hash
    private static List<String> Hash(List<String> list){
        List<String> hashList = new ArrayList<>();
        //将字符串数组的每个字符串转成256位的hash值
        for(String str:list){
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] bytes=messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
                String integer =new BigInteger(1,bytes).toString(2);
                StringBuilder wordHash = new StringBuilder(integer);
                // hash值少于256位的，尾部补全
                if (wordHash.length() < 256) wordHash.append("0".repeat(256 - wordHash.length()));
                hashList.add(wordHash.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashList;
    }

    //降维
    private static void dimensionalityReduction(int[] weightVectorList){
        //权重值大于0则为1，反之为0
        for(int i=0;i<weightVectorList.length;i++){
            weightVectorList[i]=weightVectorList[i]>0?1:0;
        }
    }

    //加权、合并
    private static int[] weightedAndMerged(List<String> hashList){
        int size= hashList.size();
        //256位的加权向量
        int[] weightVectorList=new int[256];
        for(int i=0;i<size;i++) {
            String str = hashList.get(i);
            for (int j = 0; j < str.length(); j++) {
                //权重范围0-10，特征词在数组中位置越靠前权重越大
                int weight = 10 - 10 * i / size;
                //加权并合并
                if (str.charAt(j) == '1') weightVectorList[j] += weight;
                else weightVectorList[j] -= weight;
            }
        }
        return weightVectorList;
    }
}
